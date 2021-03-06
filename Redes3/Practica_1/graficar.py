import rrdtool
import time
tiempo_actual = int(time.time())
tiempo_final = tiempo_actual - 86400
tiempo_inicial = tiempo_final -25920000

def grafica():
    ret = rrdtool.graph( "multicast.png",
                     "--start",'1568212812',
                      "--end","1568213412",
                     "--vertical-label=Bytes/s",
                     "--title=Paquetes multicast recibidos",
                     "DEF:multicast=resultados.rrd:multicast:AVERAGE",
                     "LINE3:multicast#00FF00:In traffic")
    ret = rrdtool.graph("ipv4.png",
                        "--start",'1568212812',
                      "--end","1568213412",
                        "--vertical-label=Bytes/s",
                        "--title=Paquetes recibidos protocolo IPv4",
                        "DEF:ipv4=resultados.rrd:ipv4:AVERAGE",
                        "LINE3:ipv4#00FF00:In traffic")
    ret = rrdtool.graph("icmp.png",
                        "--start",'1568212812',
                      "--end","1568213412",
                        "--vertical-label=Bytes/s",
                        "--title=Mensajes de respuesta ICMP",
                        "DEF:icmp=resultados.rrd:icmp:AVERAGE",
                        "LINE3:icmp#00FF00:In traffic")
    ret = rrdtool.graph("segmentos.png",
                        "--start",'1568212812',
                      "--end","1568213412",
                        "--vertical-label=Bytes/s",
                        "--title=Segmentos enviados sin octetos retrasmitidos",
                        "DEF:segmentos=resultados.rrd:segmentos:AVERAGE",
                        "LINE3:segmentos#00FF00:In traffic")
    ret = rrdtool.graph("datagramas.png",
                        "--start",'1568212812',
                      "--end","1568213412",
                        "--vertical-label=Bytes/s",
                        "--title=Datagramas no entregados",
                        "DEF:datagramas=resultados.rrd:datagramas:AVERAGE",
                        "LINE3:datagramas#00FF00:In traffic")