import time
import rrdtool
from getsnmp import consultaSNMP
carga_CPU = 0

def actualizar(comunidad, direccion):
    timeout = time.time() + 60 * 3
    while 1:
        carga_CPU1 = int(consultaSNMP(comunidad,direccion,'1.3.6.1.2.1.25.3.3.1.2.196608'))
        carga_CPU2 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196609'))
        carga_CPU3 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196610'))
        carga_CPU4 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196611'))
        carga_CPU5 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196612'))
        carga_CPU6 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196613'))
        carga_CPU7 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196614'))
        carga_CPU8 = int(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196615'))
        valor = "N:" + str(carga_CPU1) + ':' + str(carga_CPU2) + ':' + str(carga_CPU3) + ':' + str(carga_CPU4) + ':'
        valor2 = str(carga_CPU5) + ':' + str(carga_CPU6) + ':' + str(carga_CPU7) + ':' + str(carga_CPU8)
        valorf = valor + valor2
        print (valorf)
        ret = rrdtool.update("rendimiento.rrd", valorf)
        rrdtool.dump("rendimiento.rrd", 'trend.xml')
        time.sleep(30)
        if time.time() > timeout:
            break

    if ret:
        print (rrdtool.error())
        time.sleep(300)