#include "SocketDatagrama.h"

SocketDatagrama::SocketDatagrama(unsigned short port){
	udpsocket = socket(AF_INET, SOCK_DGRAM, 0);
	bzero((char *)&local, sizeof(local));
	local.sin_family = AF_INET;
	local.sin_addr.s_addr = INADDR_ANY;
	local.sin_port = htons(port);
	bind(udpsocket, (struct sockaddr *)&local, sizeof(local));
}

SocketDatagrama::SocketDatagrama(const std::string &addr, unsigned short port){
	udpsocket = socket(AF_INET, SOCK_DGRAM, 0);
	bzero((char *)&local, sizeof(local));
	local.sin_family = AF_INET;
	local.sin_addr.s_addr = inet_addr(addr.c_str());
	local.sin_port = htons(port);
	bind(udpsocket, (struct sockaddr *)&local, sizeof(local));
}

SocketDatagrama::~SocketDatagrama(){
	close(udpsocket);
}

int SocketDatagrama::setBroadcast(){
	int yes = 1;
	return setsockopt(udpsocket, SOL_SOCKET, SO_BROADCAST, &yes, sizeof(int));
}

int SocketDatagrama::unsetBroadcast(){
	int yes = 0;
	return setsockopt(udpsocket, SOL_SOCKET, SO_BROADCAST, &yes, sizeof(int));
}

ssize_t SocketDatagrama::receive(PaqueteDatagrama &p){
	ssize_t received;
	unsigned int addrLgth;
	
	timeout.tv_sec = 0;
	timeout.tv_usec = 0;
	setsockopt(udpsocket, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
	bzero((char *)&remote, sizeof(remote));
	addrLgth = sizeof(remote);
	if((received = recvfrom(udpsocket, p.getData(), p.getDataLength(), 0, (struct sockaddr *)&remote, &addrLgth))<=0){
		fprintf(stderr, "Error processing incoming message.\n");
	}else{
		p.initPort(ntohs(remote.sin_port));
		p.initAddr(inet_ntoa(remote.sin_addr));
		printf("Message received: from <%s><%d> - <%zd bytes> received\n", p.getAddr().c_str(), p.getPort(), received);
	}
	return received;
}

ssize_t SocketDatagrama::receiveTimeout(PaqueteDatagrama &p, time_t seconds, suseconds_t useconds){
	ssize_t received;
	unsigned int addrLgth;

	timeout.tv_sec = seconds;
	timeout.tv_usec = useconds;
	setsockopt(udpsocket, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
	bzero((char *)&remote, sizeof(remote));
	addrLgth = sizeof(remote);

	if((received = recvfrom(udpsocket, p.getData(), p.getDataLength(), 0, (struct sockaddr *)&remote, &addrLgth))<0){
		if(errno==EWOULDBLOCK){
			fprintf(stderr, "Tiempo para recepción transcurrido\n");
		}else{
			fprintf(stderr, "Error en recvfrom\n");
		}
		received = -1;
	}else{
		p.initPort(ntohs(remote.sin_port));
		p.initAddr(inet_ntoa(remote.sin_addr));
		printf("Message received: from <%s><%d> - <%zd bytes> received\n", p.getAddr().c_str(), p.getPort(), received);
	}
	return received;
}

ssize_t SocketDatagrama::send(PaqueteDatagrama &p){
	ssize_t sent;
	bzero((char *)&remote, sizeof(remote));
	remote.sin_family = AF_INET;
	remote.sin_addr.s_addr = inet_addr(p.getAddr().c_str());
	remote.sin_port = htons(p.getPort());
	if((sent = sendto(udpsocket, p.getData(), p.getDataLength(), 0, (struct sockaddr *)&remote, sizeof(remote)))==-1){
		fprintf(stderr, "Message send failure.\n");
	}else{
		printf("Message sent successfully: to <%s><%d> - <%zd bytes> sent\n", inet_ntoa(remote.sin_addr), ntohs(remote.sin_port), sent);
	}
	return sent;
}
