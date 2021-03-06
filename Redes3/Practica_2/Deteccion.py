import rrdtool
from generar_pdf import generar_reporte
from notify import send_alert_attached

def deteccion():
    tiempo_final = int(rrdtool.last("rendimiento.rrd"))
    tiempo_inicial = tiempo_final - 300


    ret1 = rrdtool.graphv("deteccion1.png",
                         "--start",str(tiempo_inicial),
                         "--end",str(tiempo_final),
                        "--title","Carga de CPU 1",
                         "--vertical-label=Uso de CPU (%)",
                        '--lower-limit', '0',
                        '--upper-limit', '100',
                         "DEF:carga=rendimiento.rrd:CPUload1:AVERAGE",
                         "CDEF:umbral70=carga,70,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Carga del CPU",
                         "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                         "HRULE:70#FF0000:Umbral 1 - 70%",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret2 = rrdtool.graphv("deteccion2.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Carga de CPU 2",
                         "--vertical-label=Uso de CPU (%)",
                         '--lower-limit', '0',
                         '--upper-limit', '100',
                         "DEF:carga=rendimiento.rrd:CPUload2:AVERAGE",
                         "CDEF:umbral70=carga,70,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Carga del CPU",
                         "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                         "HRULE:70#FF0000:Umbral 1 - 70%",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret3 = rrdtool.graphv("deteccion3.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Carga de CPU 3",
                         "--vertical-label=Uso de CPU (%)",
                         '--lower-limit', '0',
                         '--upper-limit', '100',
                         "DEF:carga=rendimiento.rrd:CPUload3:AVERAGE",
                         "CDEF:umbral70=carga,70,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Carga del CPU",
                         "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                         "HRULE:70#FF0000:Umbral 1 - 70%",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret4 = rrdtool.graphv("deteccion4.png",
                         "--start", str(tiempo_inicial),
                         "--end", str(tiempo_final),
                         "--title", "Carga de CPU 4",
                         "--vertical-label=Uso de CPU (%)",
                         '--lower-limit', '0',
                         '--upper-limit', '100',
                         "DEF:carga=rendimiento.rrd:CPUload4:AVERAGE",
                         "CDEF:umbral70=carga,70,LT,0,carga,IF",
                         "VDEF:cargaMAX=carga,MAXIMUM",
                         "VDEF:cargaMIN=carga,MINIMUM",
                         "VDEF:cargaSTDEV=carga,STDEV",
                         "VDEF:cargaLAST=carga,LAST",
                         "AREA:carga#00FF00:Carga del CPU",
                         "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                         "HRULE:70#FF0000:Umbral 1 - 70%",
                         "PRINT:cargaMAX:%6.2lf %S",
                         "GPRINT:cargaMIN:%6.2lf %SMIN",
                         "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                         "GPRINT:cargaLAST:%6.2lf %SLAST" )

    ret5 = rrdtool.graphv("deteccion5.png",
                          "--start", str(tiempo_inicial),
                          "--end", str(tiempo_final),
                          "--title", "Carga de CPU 5",
                          "--vertical-label=Uso de CPU (%)",
                          '--lower-limit', '0',
                          '--upper-limit', '100',
                          "DEF:carga=rendimiento.rrd:CPUload5:AVERAGE",
                          "CDEF:umbral70=carga,70,LT,0,carga,IF",
                          "VDEF:cargaMAX=carga,MAXIMUM",
                          "VDEF:cargaMIN=carga,MINIMUM",
                          "VDEF:cargaSTDEV=carga,STDEV",
                          "VDEF:cargaLAST=carga,LAST",
                          "AREA:carga#00FF00:Carga del CPU",
                          "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                          "HRULE:70#FF0000:Umbral 1 - 70%",
                          "PRINT:cargaMAX:%6.2lf %S",
                          "GPRINT:cargaMIN:%6.2lf %SMIN",
                          "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                          "GPRINT:cargaLAST:%6.2lf %SLAST")

    ret6 = rrdtool.graphv("deteccion6.png",
                          "--start", str(tiempo_inicial),
                          "--end", str(tiempo_final),
                          "--title", "Carga de CPU 6",
                          "--vertical-label=Uso de CPU (%)",
                          '--lower-limit', '0',
                          '--upper-limit', '100',
                          "DEF:carga=rendimiento.rrd:CPUload6:AVERAGE",
                          "CDEF:umbral70=carga,70,LT,0,carga,IF",
                          "VDEF:cargaMAX=carga,MAXIMUM",
                          "VDEF:cargaMIN=carga,MINIMUM",
                          "VDEF:cargaSTDEV=carga,STDEV",
                          "VDEF:cargaLAST=carga,LAST",
                          "AREA:carga#00FF00:Carga del CPU",
                          "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                          "HRULE:70#FF0000:Umbral 1 - 70%",
                          "PRINT:cargaMAX:%6.2lf %S",
                          "GPRINT:cargaMIN:%6.2lf %SMIN",
                          "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                          "GPRINT:cargaLAST:%6.2lf %SLAST")

    ret7 = rrdtool.graphv("deteccion7.png",
                          "--start", str(tiempo_inicial),
                          "--end", str(tiempo_final),
                          "--title", "Carga de CPU 7",
                          "--vertical-label=Uso de CPU (%)",
                          '--lower-limit', '0',
                          '--upper-limit', '100',
                          "DEF:carga=rendimiento.rrd:CPUload7:AVERAGE",
                          "CDEF:umbral70=carga,70,LT,0,carga,IF",
                          "VDEF:cargaMAX=carga,MAXIMUM",
                          "VDEF:cargaMIN=carga,MINIMUM",
                          "VDEF:cargaSTDEV=carga,STDEV",
                          "VDEF:cargaLAST=carga,LAST",
                          "AREA:carga#00FF00:Carga del CPU",
                          "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                          "HRULE:70#FF0000:Umbral 1 - 70%",
                          "PRINT:cargaMAX:%6.2lf %S",
                          "GPRINT:cargaMIN:%6.2lf %SMIN",
                          "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                          "GPRINT:cargaLAST:%6.2lf %SLAST")

    ret8 = rrdtool.graphv("deteccion8.png",
                          "--start", str(tiempo_inicial),
                          "--end", str(tiempo_final),
                          "--title", "Carga de CPU 8",
                          "--vertical-label=Uso de CPU (%)",
                          '--lower-limit', '0',
                          '--upper-limit', '100',
                          "DEF:carga=rendimiento.rrd:CPUload8:AVERAGE",
                          "CDEF:umbral70=carga,70,LT,0,carga,IF",
                          "VDEF:cargaMAX=carga,MAXIMUM",
                          "VDEF:cargaMIN=carga,MINIMUM",
                          "VDEF:cargaSTDEV=carga,STDEV",
                          "VDEF:cargaLAST=carga,LAST",
                          "AREA:carga#00FF00:Carga del CPU",
                          "AREA:umbral70#FF9F00:Tráfico de carga mayor que 70",
                          "HRULE:70#FF0000:Umbral 1 - 70%",
                          "PRINT:cargaMAX:%6.2lf %S",
                          "GPRINT:cargaMIN:%6.2lf %SMIN",
                          "GPRINT:cargaSTDEV:%6.2lf %SSTDEV",
                          "GPRINT:cargaLAST:%6.2lf %SLAST")
    #print (ret)
    #print(ret.keys())
    #print(ret.items())

    ultimo_valor1=float(ret1['print[0]'])
    ultimo_valor2 = float(ret2['print[0]'])
    ultimo_valor3 = float(ret3['print[0]'])
    ultimo_valor4 = float(ret4['print[0]'])
    ultimo_valor5 = float(ret5['print[0]'])
    ultimo_valor6 = float(ret6['print[0]'])
    ultimo_valor7 = float(ret7['print[0]'])
    ultimo_valor8 = float(ret8['print[0]'])

    if ultimo_valor1>70 or ultimo_valor2>70 or ultimo_valor3>70 or ultimo_valor4>70 or ultimo_valor5>70 or ultimo_valor6>70 or ultimo_valor7>70 or ultimo_valor8>70:
        print("Ya se paso")
        generar_reporte("agustin", "localhost")
        send_alert_attached("Sobrepasa Umbral línea base")