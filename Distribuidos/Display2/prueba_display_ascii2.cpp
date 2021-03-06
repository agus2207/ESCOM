#include <iostream>
#include <string.h>
#include <cmath>
#include "gfxModified.h"
#include <unistd.h>
#include <sys/time.h>
#include <time.h>
using namespace std;

//Resolución de la pantalla
#define ANCHURA 800
#define ALTURA 600

int main()
{
    int i, contador, aux, aux1, h, m, s;
    struct timeval t;
    int time=gettimeofday(&t, NULL);
    struct tm ts;
    ts=*localtime(&t.tv_sec);
    gfx_open(ANCHURA, ALTURA, "Ejemplo gfx_display_ascii");
    while(1) {
    	time=gettimeofday(&t, NULL);
    	ts=*localtime(&t.tv_sec);
        gfx_color(87,1,2);
            gfx_clear();
            gfx_display_ascii(0, 20, 10 , (ts.tm_hour /10)+48);
            gfx_display_ascii(100,20,10, (ts.tm_hour%10)+48);
            gfx_rectangle(175, 60, 5, 5);
            gfx_rectangle(176,61, 3, 3);
            gfx_rectangle(177, 62, 1,1);

            gfx_rectangle(175, 90, 5, 5);
            gfx_rectangle(176,91, 3, 3);
            gfx_rectangle(177, 92, 1,1);

            gfx_display_ascii(200,20,10, (ts.tm_min/10)+48);
            gfx_display_ascii(300,20,10, (ts.tm_min%10)+48);

            gfx_rectangle(375, 60, 5, 5);
            gfx_rectangle(376,61, 3, 3);
            gfx_rectangle(377, 62, 1,1);

            gfx_rectangle(375, 90, 5, 5);
            gfx_rectangle(376,91, 3, 3);
            gfx_rectangle(377, 92, 1,1);

            gfx_display_ascii(400,20,10, (ts.tm_sec/10)+48);
            gfx_display_ascii(500,20,10, (ts.tm_sec%10)+48);

            gfx_rectangle(575, 60, 5, 5);
            gfx_rectangle(576,61, 3, 3);
            gfx_rectangle(577, 62, 1,1);

            gfx_rectangle(575, 90, 5, 5);
            gfx_rectangle(576,91, 3, 3);
            gfx_rectangle(577, 92, 1,1);

            gfx_display_ascii(600,20,10, (t.tv_usec/100000)+48);
            gfx_display_ascii(700,20,10, ((t.tv_usec%100000)/10000)+48);
            gfx_flush();
            usleep(10000);
    }
    

}


