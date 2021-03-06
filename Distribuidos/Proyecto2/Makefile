run: servidor cliente

cliente: cliente.o 
	gcc cliente.o -lpthread -o cliente
servidor: servidor.o 
	gcc servidor.o -lpthread -o servidor
servidor.o: servidor.c
	gcc -Wall -c servidor.c
cliente.o: cliente.c
	gcc -Wall -c cliente.c