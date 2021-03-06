import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
COMMASPACE = ', '
# Define params
mailsender = "mrhydej9@gmail.com"
mailreceip = "tanibet.escom@gmail.com"
mailserver = 'smtp.gmail.com: 587'
password = "mrhyde123"

def send_alert_attached(subject):
    """ Will send e-mail, attaching png
    files in the flist.
    """
    msg = MIMEMultipart()
    msg['Subject'] = subject
    msg['From'] = mailsender
    msg['To'] = mailreceip
    fp = open('deteccion1.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion2.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion3.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion4.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion5.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion6.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion7.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('deteccion8.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    mserver = smtplib.SMTP(mailserver)
    mserver.starttls()
    # Login Credentials for sending the mail
    mserver.login(mailsender, password)

    mserver.sendmail(mailsender, mailreceip, msg.as_string())
    mserver.quit()