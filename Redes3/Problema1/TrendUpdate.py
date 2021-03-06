import time
import rrdtool
from GetSNMP import consultaSNMP

carga_CPU = 0

while 1:
    carga_CPU = int(
        consultaSNMP('SNMPcom','localhost',
                     '1.3.6.1.2.1.25.3.3.1.2.196608'))

    valor = "N:" + str(carga_CPU)
    print(valor)
    rrdtool.update('trend.rrd', valor)
    rrdtool.dump('trend.rrd','trend.xml')
    time.sleep(1)

if ret:
    print(rrdtool.error())
    time.sleep(300)