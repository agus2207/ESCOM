from reportlab.lib.pagesizes import letter
from getsnmp import consultaSNMP
from getsnmp import getSNMP

def generar_reporte(comunidad, direccion):

    from reportlab.pdfgen import canvas
    canvas = canvas.Canvas("reporte.pdf", pagesize=letter)
    canvas.setLineWidth(.3)
    canvas.setFont('Helvetica', 10)

    so = getSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0')
    if "Linux" in so:
        canvas.drawImage('linux.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0'))
    else:
        canvas.drawImage('windows.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0'))
    canvas.drawString(150, 730, 'Tiempo:'+ consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.3.0')+" ms")
    canvas.drawString(150, 710, 'Ubicacion: ' + consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.6.0'))
    canvas.drawString(150, 690, 'Comunidad: ' + comunidad)
    canvas.drawString(150, 670, 'Direccion: ' + direccion)

    canvas.drawImage('multicast.png',30, 400, 200, 200)
    canvas.drawImage('ipv4.png', 250, 400, 200, 200)
    canvas.drawImage('icmp.png', 30, 200, 200, 200)
    canvas.drawImage('datagramas.png', 250, 200, 200, 200)
    canvas.drawImage('segmentos.png', 30, 0, 200, 200)

    canvas.save()