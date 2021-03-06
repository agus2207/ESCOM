import time
import rrdtool
from GetSNMP import consultaSNMP
from grafica import deteccion
from generar_pdf import generar_reporte
from notify import send_alert_attached
carga_CPU = 0

def actualizar(comunidad, direccion):
    timeout = time.time() + 60 * 3
    while 1:
        Memoria = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.2.3.1.6.36'))
        Memoria = Memoria * int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.2.3.1.4.36'))
        Memoria = Memoria / (1024 * 1024 * 1024)
        Memoria = round((round(Memoria,2)-58.7),2)
        #print(Memoria)
        octetosin1 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.2.2.1.10.3'))
        octetosout1 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.2.2.1.16.3'))
        time.sleep(3)
        octetosin2 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.2.2.1.10.3'))
        octetosout2 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.2.2.1.16.3'))
        delta1 = octetosin2 - octetosin1
        delta2 = octetosout2 - octetosout1
        Speed = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.2.2.1.5.1'))
        Banda = ((delta1+delta2) * 8 * 100) / (3*Speed)
        Banda = round(Banda,2)
        #print(Banda)
        Procesos = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.1.6.0'))
        #print(Procesos)
        Tiempo = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.1.1.0'))
        Tiempo = round(Tiempo/6000, 2)
        #print(Tiempo)
        valor = "N:" + str(Memoria) + ':' + str(Banda) + ':' + str(Procesos) + ':' + str(Tiempo)
        print(valor)
        ret = rrdtool.update("conteo.rrd", valor)
        rrdtool.dump("conteo.rrd", 'trend.xml')
        time.sleep(5)
        if Memoria > 0.5 or Banda > 20 or Procesos > 200 or Tiempo > 120:
            print("Se mandara notificacion")
            deteccion()
            generar_reporte("agustin", "localhost")
            break;

    if ret:
        print (rrdtool.error())
        time.sleep(300)