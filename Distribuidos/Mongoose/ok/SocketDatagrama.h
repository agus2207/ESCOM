#ifndef SocketDatagrama_H_
#define SocketDatagrama_H_

#include <iostream>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netdb.h>
#include <strings.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "PaqueteDatagrama.h"

class SocketDatagrama{
	private:
		struct sockaddr_in local;
		struct sockaddr_in remote;
		int udpsocket;
		struct timeval timeout;
	public:
		SocketDatagrama(unsigned short port = 0);
		SocketDatagrama(const std::string &addr, unsigned short port = 0);
		~SocketDatagrama();
		int setBroadcast();
		int unsetBroadcast();
		ssize_t receive(PaqueteDatagrama &p);
		ssize_t receiveTimeout(PaqueteDatagrama &p, time_t seconds, suseconds_t useconds);
		ssize_t send(PaqueteDatagrama &p);
};

#endif