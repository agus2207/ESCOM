from reportlab.lib.pagesizes import letter
from GetSNMP import consultaSNMP

def generar_reporte(comunidad, direccion):

    from reportlab.pdfgen import canvas
    canvas = canvas.Canvas("reporte.pdf", pagesize=letter)
    canvas.setLineWidth(.3)
    canvas.setFont('Helvetica', 10)

    so = consultaSNMP("agustin", "localhost", '1.3.6.1.2.1.1.1.0')
    if "Linux" in so:
        canvas.drawImage('linux.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, "Linux")
    else:
        canvas.drawImage('windows.png', 30, 680, 100, 100)
        canvas.drawString(150, 750, consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0'))
    canvas.drawString(150, 730, 'Tiempo:'+ consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.3.0')+" ms")
    canvas.drawString(150, 710, 'Ubicacion: ' + "Laboratorio")
    canvas.drawString(150, 690, 'Comunidad: ' + "agustin")
    canvas.drawString(150, 670, 'Direccion: ' + "localhost")

    canvas.drawImage('Memoria.png', 5, 450, 300, 200)
    canvas.drawImage('Banda.png', 310, 450, 300, 200)
    canvas.drawImage('Procesos.png', 5, 250, 300, 200)
    canvas.drawImage('Tiempo.png', 310, 250, 300, 200)

    canvas.drawString(10, 200, "Su ubicacion es un Laboratorio, se cobrara una tarifa de $200")
    canvas.drawString(10, 185, "Se cobrara un cargo extra de $50 por sobrepasar los limites del servicio")

    canvas.showPage()

    canvas.save()