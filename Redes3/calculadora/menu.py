def menu():
    print("Leonardo es el cacas")
    numero1 = input("Ingrese numero uno:\n")
    numero2 = input("Ingrese numero dos:\n")
    print("1.Suma")
    print("2.Resta")
    print("3.Multiplicacion")
    print("4.Division")
    opcion = input("Ingrese operacion:\n")
    if(opcion==1):
        resultado = int(numero1)+int(numero2)
        print(resultado)
    elif (opcion==2):
        resultado = int(numero1)-int(numero2)
        print(resultado)
    elif (opcion==3):
        resultado = int(numero1) * int(numero2)
        print(resultado)
    elif (opcion==4):
        resultado = int(numero1) / int(numero2)
        print(resultado)


menu()