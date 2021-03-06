import sys
import rrdtool
import time
tiempo_actual = int(time.time())
tiempo_final = tiempo_actual - 86400
tiempo_inicial = tiempo_final -25920000

while 1:
    ret = rrdtool.graph( "trafico.png",
                     "--start",'1567002120',
 #                    "--end","N",
                     "--vertical-label=Bytes/s",
                     "--title=Trafico de red de agente SNMP",
                     "DEF:inoctets=trafico.rrd:inoctets:AVERAGE",   #Se define coleccion con su origen, el datasource y la funcion
                     "DEF:outoctets=trafico.rrd:outoctets:AVERAGE",     #Se pueden declaran varias colecciones
                     "AREA:inoctets#00FF00:In traffic",     #Se grafica una area, se puede definir un color y una etiqueta0
                     "LINE3:outoctets#0000FF:Out traffic")    #Line es el tipo de grafica el numero es el ancho de la linea

    time.sleep(30)  #Cada treinta segundos se actualiza la grafica