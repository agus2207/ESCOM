import time
import rrdtool

title="Deteccion de comportamiento anomalo"
endDate = rrdtool.last("netP.rrd") #ultimo valor del XML
begDate = endDate - 600
DatosAyer=begDate - 600
FinAyer=endDate - 600
#rrdtool.tune(rrdname, '--alpha', '0.1')
ret = rrdtool.graph("pred.png",
                        '--start', str(begDate), '--end', str(endDate), '--title=' + title,
                        "--vertical-label=Bytes/s",
                        '--slope-mode',
                        "DEF:obs=netP.rrd:inoctets:AVERAGE",
                        "DEF:obsAyer=netP.rrd:inoctets:AVERAGE:start="+str(DatosAyer)+":end="+str(FinAyer),
                        "DEF:pred=netP.rrd:inoctets:HWPREDICT",
                        "DEF:dev=netP.rrd:inoctets:DEVPREDICT",
                        "DEF:fail=netP.rrd:inoctets:FAILURES",
                        'SHIFT:obsAyer:86400',
                    #"RRA:DEVSEASONAL:1d:0.1:2",
                    #"RRA:DEVPREDICT:5d:5",
                    #"RRA:FAILURES:1d:7:9:5""
                        "CDEF:scaledobs=obs,8,*",
                        "CDEF:scaledobsAyer=obsAyer,8,*",
                        "CDEF:upper=pred,dev,2,*,+",
                        "CDEF:lower=pred,dev,2,*,-",
                        "CDEF:scaledupper=upper,8,*",
                        "CDEF:scaledlower=lower,8,*",
                        "CDEF:scaledpred=pred,8,*",
                    "TICK:fail#FDD017:1.0: Fallas",
                    "AREA:scaledobsAyer#9C9C9C:Ayer",
                    "LINE3:scaledobs#00FF00:In traffic",
                    "LINE1:scaledpred#FF00FF:Prediccion",
                    #"LINE1:outoctets#0000FF:Out traffic",
                    "LINE1:scaledupper#ff0000:Upper Bound Average bits in",
                    "LINE1:scaledlower#0000FF:Lower Bound Average bits in")