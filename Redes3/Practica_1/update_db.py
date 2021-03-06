import time
import rrdtool
from getsnmp import consultaSNMP

def update(comunidad, direccion):

    timeout = time.time() + 60 * 1
    multicas = 0
    ipv4 = 0
    icmp = 0
    segmentos = 0
    datagramas = 0

    while 1:
        multicast = int(
            consultaSNMP(comunidad,direccion,
                     '1.3.6.1.2.1.2.2.1.10.2'))     #consulta los octetos de entrada
        ipv4 = int(
            consultaSNMP(comunidad,direccion,
                     '1.3.6.1.2.1.4.9.0'))     #consulta los octetos de salida

        icmp = int(
            consultaSNMP(comunidad, direccion,
                         '1.3.6.1.2.1.5.14.0'))

        segmentos = int(
            consultaSNMP(comunidad, direccion,
                         '1.3.6.1.2.1.6.11.0'))

        datagramas = int(
            consultaSNMP(comunidad, direccion,
                         '1.3.6.1.2.1.7.3.0'))

        valor = "N:" + str(multicast) + ':' + str(ipv4) + ':' + str(icmp) + ':' + str(segmentos) + ':' + str(datagramas)
        #print (valor)
        rrdtool.update('resultados.rrd', valor) #esta funcion es la que actualiza la base de datos
        rrdtool.dump('resultados.rrd','resultados.xml')
        time.sleep(1)
        if time.time() > timeout:
            break
