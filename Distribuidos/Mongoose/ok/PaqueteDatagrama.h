#ifndef  PAQUETEDATAGRAMA_H_
#define  PAQUETEDATAGRAMA_H_

#include <iostream>
#include <cstring>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netdb.h>
#include <strings.h>

class PaqueteDatagrama {    
	private:
		unsigned char *data;
		size_t dataLength;
		std::string ip;
		unsigned short port;
	public:
		PaqueteDatagrama(const void *data, size_t dataSize, const std::string &ip, unsigned short port);
		PaqueteDatagrama(size_t dataLength);
		~PaqueteDatagrama();
		std::string getAddr();
		size_t getDataLength();
		unsigned short getPort();
		unsigned char * getData();
		void initPort(unsigned short port);
		void initAddr(const std::string &ip);
		void initData(const void *data);    
};

#endif