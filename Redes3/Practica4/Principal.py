import os
import getpass
import shutil
import telnetlib
from ftplib import FTP
import time

def extraer_config(ip):
    tel=telnetlib.Telnet(ip)
    tel.read_until(b"User: ")
    time.sleep(1)
    tel.write("rcp".encode('ascii') + b"\n")
    time.sleep(1)
    tel.read_until(b"Password:")
    time.sleep(1)
    tel.write("rcp".encode('ascii') + b"\n")
    time.sleep(1)
    tel.write(b"enable\n")
    time.sleep(1)
    tel.write(b"configure\n")
    time.sleep(1)
    tel.write(b"copy running-config startup-config\n")
    time.sleep(1)
    tel.write(b"exit\n")
    tel.close()
    time.sleep(1)
    ftp=FTP(ip)
    ftp.login("rcp","rcp")
    ftp.retrbinary('RETR startup-config',open('startup-config','wb').write)
    ftp.close()
    #mover archivo a una carpeta
    ipn=""
    for i in ip:
        if i!=".":
            ipn+=i
    print (ipn)
    if not os.path.exists(ipn):
        os.mkdir(ipn)
    else:
        os.remove(ipn+"/startup-config")
    shutil.move("startup-config",ipn)

def importar_conf(ip,ip2):
    archivo_config = input("Introduce el nombre del archivo de configuracion: ")
    nombre = input("Intoduce el nombre con el que deseas guardar el archivo: ")
    stor = 'STOR '+nombre
    ipn=""
    for i in ip:
        if (i!="."):
            ipn+=i
    archivo=open(ipn+"/"+archivo_config,"rb")
    ftp=FTP(ip2)
    ftp.login("rcp","rcp")
    ftp.storbinary(stor, archivo)
    ftp.close()
    archivo.close()
    #reiniciar router

def mover_configuracion(ip, ip2):
    archivo_config = input("Introduce el nombre del archivo de configuracion: ")
    nombre = input("Intoduce el nombre con el que deseas guardar el archivo: ")
    instruccion = "copy "+archivo_config+" ftp://rcp:rcp@"+ip2+"/"+nombre
    tel = telnetlib.Telnet(ip)
    tel.read_until(b"User: ")
    time.sleep(1)
    tel.write("rcp".encode('ascii') + b"\n")
    time.sleep(1)
    tel.read_until(b"Password:")
    time.sleep(1)
    tel.write("rcp".encode('ascii') + b"\n")
    time.sleep(1)
    tel.write(b"enable\n")
    time.sleep(1)
    tel.write(b"configure\n")
    time.sleep(1)
    tel.write(instruccion.encode('ascii') + b"\n")
    time.sleep(1)
    tel.write(b"exit\n")
    tel.close()


def compara_version(ip):
    ipn=""
    i=0
    for i in ip:
        if (i!="."):
            ipn+=i
#checar codigo
    ftp=FTP(ip)
    ftp.login("rcp","rcp")
    ftp.retrbinary('RETR startup-config',open('startup-config1','wb').write)
    ftp.close()
    #mover archivo a una carpeta
    print(ipn)
    if not os.path.exists(ipn):
        os.mkdir(ipn)
    shutil.move("startup-config1",ipn)

    list_archivo=os.listdir(os.path.abspath(ipn))
    #print list_archivo.__len__()
    archivo=open(ipn+"/"+list_archivo[0],"r")
    archivo1=open(ipn+"/"+list_archivo[1],"r")
    cad=str(archivo.readlines()).split("ethernet")
    cad1=str(archivo1.readlines()).split("ethernet")
    del cad[0]
    del cad1[0]
    cad=str(cad).replace("!interface","")
    cad1=str(cad1).replace("!interface","")
    cad=str(cad).replace("!","")
    cad1=str(cad1).replace("!","")
    cad=str(cad).replace("\\n","")
    cad1=str(cad1).replace("\\n","")
    cad=str(cad).replace("'\\\'","")
    cad1=str(cad1).replace("'\\\'","")
    cad=str(cad).replace("'\'","")
    cad1=str(cad1).replace("'\'","")
    cad=cad.split(",")
    cad1=cad1.split(",")
    #print cad
    #print cad1
    for i in range(len(cad)):
        if(cad[i]!=cad1[i]):
            print(cad[i],cad1[i])
    #buscar ethx de los dos documentos y comparar direccion,mtu y edo de la interface(no shutdown o shutdow !interface)
    archivo.close()
    archivo1.close()

#extraer_config("192.168.1.90")
#importar_conf("192.168.1.90","192.168.1.92")
#compara_version("192.168.1.90")
def pedirNumeroEntero():
    correcto=False
    num=0
    while(not correcto):
        try:
            num = int(input("Elige una opcion: "))
            correcto=True
        except ValueError:
            print('Error, introduce un numero entero')
    return num

def menu():
    while 1:
        opcion=0
        print("1. Extraer")
        print("2. Importar")
        print("3. Comparar")
        print("4. Router-Router")
        print("5. Salir")
        opcion=pedirNumeroEntero()
        if (opcion==1):
            print("Extraer configuracion de un router")
            ip=input("Escribir ip:")
            extraer_config(ip)
        if (opcion==2):
            print("Importar configuracion de un router")
            ip=input("Escribir ip:")
            ip1=input("Escribir ip:")
            importar_conf(ip,ip1)
        if (opcion==3):
            print("Comparar version actual con la version guardada")
            ip=input("Escribir ip:")
            compara_version(ip)
        if (opcion == 4):
            print("Pasar configuracion de router a router")
            ip = input("Escribir ip:")
            ip1 = input("Escribir ip:")
            mover_configuracion(ip, ip1)
        if(opcion == 5):
            break
        else:
            print ("Introduce un numero entre 1 y 3")

menu()