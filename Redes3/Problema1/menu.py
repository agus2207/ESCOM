from prettytable import PrettyTable
from GetSNMP import consultaSNMP
from datetime import datetime
from conteo import actualizar
import time


def main():
    print("Problema 01")
    menu()


def menu():
    estado = True
    while estado:
        print("1. Predecir CPU. \n"
              "2. Predecir Trafico de red.\n"
              "3. Salir.\n")

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
            print("Cerrando gestor.")
            break;

main()