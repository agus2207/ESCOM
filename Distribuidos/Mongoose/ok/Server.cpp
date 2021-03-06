using namespace std;

#include "mongoose.h"
#include "SocketDatagrama.h"
#include "PaqueteDatagrama.h"
#include <stdio.h>
#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <sys/time.h>

#define SVR9_PORT				7200
#define TIMEOUT_SECONDS 		10
#define TIMEOUT_USECONDS		0

typedef struct{
	int a;
	int b;
	int res;
	string svrAddr;
	struct timeval elapsedTime;
}reply;

static const char *s_http_port = "8000";
static struct mg_serve_http_opts s_http_server_opts;

vector<reply> discoverOnlineServers(int a, int b, const std::string &broadcastAddr, unsigned short svrPort){
	SocketDatagrama socketlocal;
	struct timeval tv;
	vector<reply> rv;
	int buff[2] = {a, b};

	socketlocal.setBroadcast();
	PaqueteDatagrama recvPkt(sizeof(int));
	PaqueteDatagrama sndPkt(buff, sizeof(buff), broadcastAddr, svrPort);
	/*snd packet*/
	socketlocal.send(sndPkt);
	gettimeofday(&tv, NULL);
	/*waits for server to answer*/
	while(socketlocal.receiveTimeout(recvPkt, TIMEOUT_SECONDS, TIMEOUT_USECONDS)==sizeof(int) && recvPkt.getPort()==svrPort){
		reply r = {.a = a, .b = b, .res = 0};
		gettimeofday(&r.elapsedTime, NULL);
		timersub(&r.elapsedTime, &tv, &r.elapsedTime);
		memcpy(&r.res, recvPkt.getData(), sizeof(int));
		r.svrAddr = recvPkt.getAddr();
		rv.push_back(r);
	}
	return rv;
}

static void serve_request(struct mg_connection *nc, struct http_message *hm){
		char broadcastAddr[16];
		string foundSvrs = "";
		vector<reply> rv;

		mg_get_http_var(&hm->body, "query", broadcastAddr, sizeof(broadcastAddr));
		
		rv = discoverOnlineServers(rand()%100, rand()%100, broadcastAddr, SVR9_PORT);
		if(rv.empty()){
			foundSvrs = "No servers found.</br>\n";
		}else{
			for(int i = 0; i<rv.size(); i++){
				foundSvrs = foundSvrs + "\n</br><" + rv[i].svrAddr + ">: " +
					to_string(rv[i].a) + " + " + to_string(rv[i].b) + " = " + to_string(rv[i].res) +
					" - elapsed time: " + to_string((double)(rv[i].elapsedTime.tv_sec)*1000 + (rv[i].elapsedTime.tv_usec)/1000) + " mili seconds";
			}
		}
		printf("Answer: %s\n", foundSvrs.c_str());
		mg_send_head(nc, 200, foundSvrs.length(), "Content-Type: text/plain");
		mg_printf(nc, "%s", foundSvrs.c_str());
}

static void ev_handler(struct mg_connection *nc, int ev, void *p){
	char query[16];
 	struct http_message *hm = (struct http_message *)p;
	
	if(ev==MG_EV_HTTP_REQUEST){
		if(mg_vcmp(&hm->uri, "/search")==0){
			mg_get_http_var(&hm->body, "query", query, sizeof(query));
		    printf("Request: %s\n", query);
		    serve_request(nc, hm);
		}else{
			mg_serve_http(nc, (struct http_message *) p, s_http_server_opts);
		}
	}

}

int main(void){
	struct mg_mgr mgr;
	struct mg_connection *nc;
	mg_mgr_init(&mgr, NULL);

	printf("Starting web server on port %s\n\n", s_http_port);
	nc = mg_bind(&mgr, s_http_port, ev_handler);
	if(nc==NULL){
		printf("Failed to create listener\n\n");
		return 1;
	}
	// Set up HTTP server parameters
	mg_set_protocol_http_websocket(nc);
	s_http_server_opts.document_root = "www"; // Serve current directory
	s_http_server_opts.enable_directory_listing = "yes";
	for(;;){
		mg_mgr_poll(&mgr, 1000);
	}
	mg_mgr_free(&mgr);
	return 0;
}
