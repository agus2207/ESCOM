#include <stdio.h>

#ifndef FILE_H_
#define FILE_H_

class File{
	private:
		char buffer[BUFSIZ];
		char origen[255];
		char destino[255];
	public:
		File(char*, char*);
		int buscar();
		int escribir();
};

#endif