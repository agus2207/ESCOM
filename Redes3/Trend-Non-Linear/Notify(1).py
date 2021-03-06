import os
import time
import rrdtool
import tempfile
import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
COMMASPACE = ', '
# Define params
mailsender = "mrhydej9@gmail.com"
mailreceip = "mrhydej9@gmail.com"
mailserver = 'smtp.gmail.com: 587'
password = "mrhyde123"
width = '500'
height = '200'

# Generate charts for last 48 hours
enddate = rrdtool.last("netP.rrd")#ultimo valor del XML
begdate = enddate - 3600

def send_alert_attached(subject, flist):
    """ Will send e-mail, attaching png
    files in the flist.
    """
    msg = MIMEMultipart()
    msg['Subject'] = subject
    msg['From'] = mailsender
    msg['To'] = mailreceip
    #message = "Thank you"
    msg.attach(MIMEText("Galindo Reyes Agustin", 'plain'))
    """for file in flist:
        png_file = file.split('.')[0] + '.png'
        print(png_file)
        fp = open(png_file, 'rb')
        img = MIMEImage(fp.read())
        fp.close()
        msg.attach(img)"""
    fp = open("pred.png", 'rb')
    img = MIMEImage(fp.read())
    fp.close()
    mserver = smtplib.SMTP(mailserver)
    mserver.starttls()
    # Login Credentials for sending the mail
    mserver.login(mailsender, password)

    mserver.sendmail(mailsender, mailreceip, msg.as_string())
    mserver.quit()


def check_aberration(rrdpath, fname):
    """ This will check for begin and end of aberration
        in file. Will return:
        0 if aberration not found.
        1 if aberration begins
        2 if aberration ends
    """
    ab_status = 0

    info = rrdtool.info("netP.rrd")
    rrdstep = int(info['step'])
    lastupdate = info['last_update']
    previosupdate = str(lastupdate - rrdstep - 1)
    graphtmpfile = tempfile.NamedTemporaryFile()
    # Ready to get FAILURES  from rrdfile
    # will process failures array values for time of 2 last updates
    values = rrdtool.graph(graphtmpfile.name+'F',
                           'DEF:f0=netP.rrd:inoctets:FAILURES:start=1575300180:end=1575301260',  #cambia datos
                           'PRINT:f0:MIN:%1.0lf',
                           'PRINT:f0:MAX:%1.0lf',
                           'PRINT:f0:LAST:%1.0lf')
    print(values)
    fmin = int(values[2][0])
    fmax = int(values[2][1])
    flast = int(values[2][2])
    print ("fmin="+str(fmin)+", fmax="+str(fmax)+",flast="+str(flast))
    # check if failure value had changed.
    if (fmin != fmax):
        if (flast == 1):
            ab_status = 1
        else:
            ab_status = 2
    return ab_status


def gen_image(width, height, begdate, enddate):
    """
    Generates png file from rrd database:
    rrdpath - the path where rrd is located
    pngpath - the path png file should be created in
    fname - rrd file name, png file will have the same name .png extention
    width - chart area width
    height - chart area height
    begdate - unixtime
    enddate - unixtime
    """
    # 24 hours before current time, will show on chart using SHIFT option
    ldaybeg = str(begdate - 3600)
    ldayend = str(enddate - 3600)
    # Will show some additional info on chart
    endd_str = time.strftime("%d/%m/%Y %H:%M:%S", (time.localtime(int(enddate)))).replace(':', '\:')
    begd_str = time.strftime("%d/%m/%Y %H:%M:%S", (time.localtime(int(begdate)))).replace(':', '\:')
    title = 'Chart for: ' + "netP.rrd".split('.')[0]
    info = rrdtool.info("netP.rrd")
    print(info)
    rrdtype = info['ds[inoctets].type']
    # Will use multip variable for calculation of totals,
    # should be usefull for internet traffic accounting,
    # or call/minutes count from CDR's.
    # Do not need logic for DERIVE and ABSOLUTE
    if rrdtype == 'COUNTER':
        multip = str(int(enddate) - int(begdate))
    else:
        # if value type is GAUGE should divide time to step value
        rrdstep = info['step']
        multip = str(round((int(enddate) - int(begdate)) / int(rrdstep)))
    # Make png image
    rrdtool.graph("netP.png",
                  '--width', width, '--height', height,
                  '--start', str(begdate), '--end', str(enddate), '--title=' + title,
                  '--lower-limit', '0',
                  '--slope-mode',
                  'COMMENT:From\: netP.rrd  To\:' + endd_str + '\\c',
                  'DEF:value=netP.rrd:inoctets:AVERAGE',
                  'DEF:pred=netP.rrd:inoctets:HWPREDICT',
                  'DEF:dev=netP.rrd:inoctets:DEVPREDICT',
                  'DEF:fail=netP.rrd:inoctets:FAILURES',
                  'DEF:yvalue=netP.rrd:inoctets:AVERAGE:start=' + ldaybeg + ':end=' + ldayend,
                  'SHIFT:yvalue:86400',
                  'CDEF:upper=pred,dev,2,*,+',
                  'CDEF:lower=pred,dev,2,*,-',
                  'CDEF:ndev=dev,-1,*',
                  'CDEF:tot=value,' + multip + ',*',
                  'CDEF:ytot=yvalue,' + multip + ',*',
                  'TICK:fail#FDD017:1.0:"Failures"\\n',
                  'AREA:yvalue#C0C0C0:"Yesterday\:"',
                  'GPRINT:ytot:AVERAGE:"Total\:%8.0lf"',
                  'GPRINT:yvalue:MAX:"Max\:%8.0lf"',
                  'GPRINT:yvalue:AVERAGE:"Average\:%8.0lf" \\n',
                  'LINE3:value#0000ff:"Value    \:"',
                  'GPRINT:tot:AVERAGE:"Total\:%8.0lf"',
                  'GPRINT:value:MAX:"Max\:%8.0lf"',
                  'GPRINT:value:AVERAGE:"Average\:%8.0lf" \\n',
                  'LINE1:upper#ff0000:"Upper Bound "',
                  'LINE1:pred#ff00FF:"Forecast "',
                  'LINE1:ndev#000000:"Deviation "',
                  'LINE1:lower#00FF00:"Lower Bound "')


# List of new aberrations
begin_ab = []
# List of gone aberrations
end_ab = []
# List files and generate charts
gen_image(width, height, begdate, enddate)
ab_status = check_aberration("\\", "netP.rrd")
print(ab_status)
if ab_status == 1:
     begin_ab.append("netP.rrd")
if ab_status == 2:
    end_ab.append("netP.rrd")
if len(begin_ab) > 0:
    send_alert_attached('New aberrations detected', begin_ab)
if len(end_ab) > 0:
    send_alert_attached('Abberations gone', end_ab)