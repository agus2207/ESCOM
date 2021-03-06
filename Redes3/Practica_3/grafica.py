import rrdtool
#from generar_pdf import generar_reporte
from notify import send_alert_attached

def deteccion():
    tiempo_final = int(rrdtool.last("conteo.rrd"))
    tiempo_inicial = tiempo_final - 300


    ret1 = rrdtool.graphv("Memoria.png",
                         "--start",str(tiempo_inicial),
                         "--end",str(tiempo_final),
                        "--title","Memoria",
                         "--vertical-label=Uso de Almacenamiento",
                        '--lower-limit', '0',
                        '--upper-limit', '1',
                         "DEF:carga=conteo.rrd:Memoria:AVERAGE",
                         "CDEF:umbral0.5=carga,0.5,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Uso de Almacenamiento",
                         "AREA:umbral0.5#FF9F00:Alamcenamiento mayor a 500MB",
                         "HRULE:0.5#FF0000:Umbral 1 - 500",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret2 = rrdtool.graphv("Banda.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Ancho de Banda",
                         "--vertical-label=Ancho de Banda",
                         '--lower-limit', '0',
                         '--upper-limit', '30',
                         "DEF:carga=conteo.rrd:Banda:AVERAGE",
                         "CDEF:umbral20=carga,20,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Ancho de Banda",
                         "AREA:umbral20#FF9F00:Tráfico mayor a 20",
                         "HRULE:20#FF0000:Umbral 1 - 20",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret3 = rrdtool.graphv("Procesos.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Procesos",
                         "--vertical-label=Total de procesos",
                         '--lower-limit', '0',
                         '--upper-limit', '250',
                         "DEF:carga=conteo.rrd:Procesos:AVERAGE",
                         "CDEF:umbral200=carga,200,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Numero de procesoso",
                         "AREA:umbral200#FF9F00:Mas de 200 procesos",
                         "HRULE:200#FF0000:Umbral 1 - 200",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret4 = rrdtool.graphv("Tiempo.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Tiempo",
                         "--vertical-label=Timepo encendido del equipo",
                         '--lower-limit', '0',
                         '--upper-limit', '130',
                         "DEF:carga=conteo.rrd:Tiempo:AVERAGE",
                         "CDEF:umbral120=carga,120,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Tiempo encendido",
                         "AREA:umbral120#FF9F00:Mas de 120 minutos encendido",
                         "HRULE:120#FF0000:Umbral 1 - 120",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    #print (ret)
    #print(ret.keys())
    #print(ret.items())

