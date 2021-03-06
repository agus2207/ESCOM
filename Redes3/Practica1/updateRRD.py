import time
import rrdtool
from getSNMP import consultaSNMP
total_input_traffic = 0
total_output_traffic = 0

while 1:
    total_input_traffic = int(
        consultaSNMP('agustin','localhost',
                     '1.3.6.1.2.1.2.2.1.10.3'))     #consulta los octetos de entrada
    total_output_traffic = int(
        consultaSNMP('agustin','localhost',
                     '1.3.6.1.2.1.2.2.1.16.3'))     #consulta los octetos de salida

    valor = "N:" + str(total_input_traffic) + ':' + str(total_output_traffic)
    print (valor)
    rrdtool.update('trafico.rrd', valor) #esta funcion es la que actualiza la base de datos
    rrdtool.dump('trafico.rrd','trafico.xml')
    time.sleep(1)

if ret:
    print (rrdtool.error())
    time.sleep(300)


#la variable valor esta formada por:
    #N tiempo actual dos puntos
    #el primer valor(los octetos de entrada) dos puntos
    #el valor de los octetos de salida
    #porque se meteran dos datos se deben de actualizar ambos valores cada vez que se actualize rrdtool
    #los : sirven para separar los datos