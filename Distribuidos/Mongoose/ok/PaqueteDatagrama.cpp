using namespace std;

#include "PaqueteDatagrama.h"

PaqueteDatagrama::PaqueteDatagrama(const void *data, size_t dataSize, const std::string &ip, unsigned short port) : dataLength(dataSize), port(port){
	this->data = new unsigned char[dataSize];
	memcpy(this->data, data, dataSize);
	this->ip = ip;
}

PaqueteDatagrama::PaqueteDatagrama(size_t dataLength) : dataLength(dataLength){
	data = new unsigned char[dataLength];
}

PaqueteDatagrama::~PaqueteDatagrama(){
	delete [] data;
}

std::string PaqueteDatagrama::getAddr(){
	return ip;
}

size_t PaqueteDatagrama::getDataLength(){
	return dataLength;
}

unsigned short PaqueteDatagrama::getPort(){
	return port;
}

unsigned char * PaqueteDatagrama::getData(){
	return data;
}

void PaqueteDatagrama::initPort(unsigned short port){
	this->port = port;
}

void PaqueteDatagrama::initAddr(const std::string &ip){
	this->ip = ip;
}

void PaqueteDatagrama::initData(const void *data){
	memcpy(this->data, data, dataLength);
}
