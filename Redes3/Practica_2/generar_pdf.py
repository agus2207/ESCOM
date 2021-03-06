from reportlab.lib.pagesizes import letter
from getsnmp import consultaSNMP
from notify import send_alert_attached

def generar_reporte(comunidad, direccion):

    from reportlab.pdfgen import canvas
    canvas = canvas.Canvas("reporte.pdf", pagesize=letter)
    canvas.setLineWidth(.3)
    canvas.setFont('Helvetica', 10)

    so = consultaSNMP("agustingalindoreyes", "10.100.64.76", '1.3.6.1.2.1.1.1.0')
    if "Linux" in so:
        canvas.drawImage('linux.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, "Linux")
    else:
        canvas.drawImage('windows.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0'))
    canvas.drawString(150, 730, 'Tiempo:'+ consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.3.0')+" ms")
    canvas.drawString(150, 710, 'Ubicacion: ' + consultaSNMP("agustingalindoreyes", "10.100.64.76", '1.3.6.1.2.1.1.6.0'))
    canvas.drawString(150, 690, 'Comunidad: ' + "comunidad4cm3")
    canvas.drawString(150, 670, 'Direccion: ' + "10.100.71.116")

    canvas.drawImage('deteccion1.png', 5, 450, 300, 200)
    canvas.drawImage('deteccion2.png', 310, 450, 300, 200)
    canvas.drawImage('deteccion3.png', 5, 250, 300, 200)
    canvas.drawImage('deteccion4.png', 310, 250, 300, 200)
    canvas.drawImage('deteccion5.png', 5, 50, 300, 200)
    canvas.drawImage('deteccion6.png', 310, 50, 300, 200)

    canvas.showPage()

    canvas.drawImage('linux.png', 30, 680, 100, 100)
    canvas.drawString(150, 750, "Linux")
    canvas.drawString(150, 730, 'Tiempo:' + consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.3.0') + " ms")
    canvas.drawString(150, 710, 'Ubicacion: ' + consultaSNMP("agustingalindoreyes", "10.100.64.76", '1.3.6.1.2.1.1.6.0'))
    canvas.drawString(150, 690, 'Comunidad: ' + "comunidad4cm3")
    canvas.drawString(150, 670, 'Direccion: ' + "10.100.71.116")
    canvas.drawImage('deteccion7.png', 5, 450, 300, 200)
    canvas.drawImage('deteccion8.png', 300, 450, 300, 200)

    canvas.save()
    # send_alert_attached("Sobrepasa Umbral línea base")