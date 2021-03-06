import rrdtool

def crear_db():
    ret = rrdtool.create("resultados.rrd",
                     "--start",'N',     #N obtiene la hora del sistema
                     "--step",'60',
                     "DS:multicast:COUNTER:600:U:U",
                     "DS:ipv4:COUNTER:600:U:U",
                     "DS:icmp:COUNTER:600:U:U",
                     "DS:segmentos:COUNTER:600:U:U",
                     "DS:datagramas:COUNTER:600:U:U",
                     "RRA:AVERAGE:0.5:1:600",
                     "RRA:AVERAGE:0.5:1:600",
                     "RRA:AVERAGE:0.5:1:600",
                     "RRA:AVERAGE:0.5:1:600",
                     "RRA:AVERAGE:0.5:1:600")

    rrdtool.dump("resultados.rrd", "resultados.xml")

    if ret:
        print (rrdtool.error())