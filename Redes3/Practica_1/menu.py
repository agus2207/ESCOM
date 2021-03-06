from getsnmp import consultaSNMP
from walksnmp import walk
from update_db import update
from graficar import grafica
from generar_pdf import generar_reporte

def main():
    print("Practica 01 : Monitoreo con SNMP")
    menu()

def menu():
    she_love_you = True
    while she_love_you:
        archivo = open("agentes.txt", "r")
        for linea in archivo.readlines():
            #print(linea)
            parametros = linea.split(",")
            comunidad = parametros[0]
            direccion = parametros[1].strip()
            print("Agente: \n")
            print(consultaSNMP(comunidad, direccion, '1.3.6.1.2.1.1.1.0'))
            print("\n")
            print("Estatus (1.UP, 2.DOWN): \n")
            walk(comunidad, direccion, '1.3.6.1.2.1.25.1.2')
            print("\n")
            print("Puertos (1.UP, 2.DOWN, 6.NOT PRESENT): \n")
            walk(comunidad, direccion, '1.3.6.1.2.1.2.2.1.8')
            print("\n")
        archivo.close()
        print("1. Agregar agente. \n"
              "2. Eliminar agente.\n"
              "3. Graficas de agente.  \n"
              "4. Actualizar.\n"
              "5. Salir.\n")

        opt = int(input("> "))

        if opt == 1:
            comunidad = input("Comunidad: ")
            hostname = input("Direccion IP: ")
            version = input("Version SNMP: ")
            port = input("Puerto: ")
            archivo = open("agentes.txt", "a")
            archivo.write(comunidad+","+hostname+","+version+","+port+"\n")
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
            print("Generando reporte")
            update(comunidad, direccion)
            grafica()
            generar_reporte(comunidad, direccion)

        elif opt == 4:
            menu()

        elif opt == 5:
            print("Cerrando gestor.")
            break;

main()