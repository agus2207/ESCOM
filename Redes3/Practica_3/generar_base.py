import rrdtool

def crear_db():
    ret = rrdtool.create("conteo.rrd",
                     "--start",'N',
                     "--step",'60',
                     "DS:Memoria:GAUGE:600:U:U",
                     "DS:Banda:GAUGE:600:U:U",
                     "DS:Procesos:GAUGE:600:U:U",
                     "DS:Tiempo:GAUGE:600:U:U",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100")
    if ret:
        print (rrdtool.error())

crear_db()