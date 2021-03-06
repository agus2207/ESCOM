import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
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
    #message = "Thank you"
    msg.attach(MIMEText("La comunidad agustingalindoreyes", 'plain'))
    msg.attach(MIMEText("Con direccion ip: 10.100.65.171", 'plain'))
    msg.attach(MIMEText("Con ubicacion en Laboratorio", 'plain'))
    msg.attach(MIMEText("Tendra una tarifa de $200", 'plain'))
    msg.attach(MIMEText("Mas un recargo de $50", 'plain'))
    fp = open('Memoria.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('Banda.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('Procesos.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    fp = open('Tiempo.png', 'rb')
    img1 = MIMEImage(fp.read())
    fp.close()
    msg.attach(img1)
    mserver = smtplib.SMTP(mailserver)
    mserver.starttls()
    # Login Credentials for sending the mail
    mserver.login(mailsender, password)

    mserver.sendmail(mailsender, mailreceip, msg.as_string())
    mserver.quit()