import rrdtool

def crear_db():
    ret = rrdtool.create("rendimiento.rrd",
                     "--start",'N',
                     "--step",'60',
                     "DS:CPUload1:GAUGE:600:U:U",
                     "DS:CPUload2:GAUGE:600:U:U",
                     "DS:CPUload3:GAUGE:600:U:U",
                     "DS:CPUload4:GAUGE:600:U:U",
                     "DS:CPUload5:GAUGE:600:U:U",
                     "DS:CPUload6:GAUGE:600:U:U",
                     "DS:CPUload7:GAUGE:600:U:U",
                     "DS:CPUload8:GAUGE:600:U:U",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100",
                     "RRA:AVERAGE:0.5:1:100")
    if ret:
        print (rrdtool.error())

crear_db()