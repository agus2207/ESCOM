#!/usr/bin/env python

import rrdtool
ret = rrdtool.create("trafico.rrd",
                     "--start",'N',     #N obtiene la hora del sistema
                     "--step",'60',
                     "DS:inoctets:COUNTER:600:U:U",     #guarda los octetos de entrada
                     "DS:outoctets:COUNTER:600:U:U",    #guarda los octetos de salida
                     "RRA:AVERAGE:0.5:6:700",   #fila 1 con 700 datos
                     "RRA:AVERAGE:0.5:1:600")   #fila 2 con 600 datos

rrdtool.dump("trafico.rrd", "trafico.xml")

if ret:
    print (rrdtool.error())
