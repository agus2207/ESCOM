from prettytable import PrettyTable
from getsnmp import consultaSNMP
from datetime import datetime
from actualizar import actualizar
from Deteccion import deteccion

def main():
    print("Practica 02 : Adquision de rendimiento")
    menu()
    #crear_db()

def menu():

    estado = True
    table = PrettyTable(["Dispositivo", "Software", "Tiempo de actividad (segundos)", "Fecha", "Comunidad"])
    while estado:
        archivo = open("agentes.txt", "r")
        for linea in archivo.readlines():
            parametros = linea.split(",")
            comunidad = parametros[0]
            direccion = parametros[1].strip()
            name = consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.5.0')
            software = consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0')
            time = consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.3.0')
            if not time:
                print("")
            else:
                table.add_row([name, software, round(int(time)/1000), datetime.now(), comunidad])
        archivo.close()
        print(table)
        print("1. Agregar agente. \n"
               "2. Eliminar agente.\n"
               "3. Adquisicion de rendimiento.  \n"
               "4. Actualizar.\n"
               "5. Salir.\n")

        opt = int(input("> "))

        if opt == 1:
            comunidad = input("Comunidad: ")
            hostname = input("Direccion IP: ")
            version = input("Version SNMP: ")
            port = input("Puerto: ")
            archivo = open("agentes.txt", "a")
            archivo.write(comunidad + "," + hostname + "," + version + "," + port + "\n")
            archivo.close()

        elif opt == 2:
            comunidad = input("Comunidad: ")
            direccion = input("Direccion: ")
            archivo = open("agentes.txt", "r")
            lineas = archivo.readlines()
            archivo.close()
            archivo = open("agentes.txt", "w")
            for linea in lineas:
                if linea.find(direccion) == -1:
                    archivo.write(linea)
            archivo.close()

        elif opt == 3:
            comunidad = input("Comunidad: ")
            direccion = input("Direccion IP: ")
            CPUload = consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.25.3.3.1.2.196608')
            print("CPULOAD = " + CPUload)
            RAM = consultaSNMP(comunidad, direccion, "1.3.6.1.4.1.2021.4.6.0")
            print("RAM = " + RAM)
            DISCO = consultaSNMP(comunidad, direccion, "1.3.6.1.2.1.1.3.0")
            print("DISCO = " +DISCO)
            actualizar(comunidad, direccion)
            deteccion()
            table.clear_rows()

        elif opt == 4:
            menu()

        elif opt == 5:
            print("Cerrando gestor.")
            break;

main()