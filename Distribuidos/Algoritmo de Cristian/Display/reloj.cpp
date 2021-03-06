#include <stdio.h>
#include <iostream>
#include <time.h> 
#include <sstream>
#include <string.h>
#include <sys/time.h>
#define SEC_PER_DAY   86400
#define SEC_PER_HOUR  3600
#define SEC_PER_MIN   60

using namespace std;

int main() {
    struct timeval tv;
    struct timezone tz;
    gettimeofday(&tv, &tz);
    printf("TimeZone-1 = %d\n", tz.tz_minuteswest);
    // Cast members as specific type of the members may be various 
    // signed integer types with Unix.
    printf("TimeVal-3  = %lld\n", (long long) tv.tv_sec);
    printf("TimeVal-4  = %lld\n", (long long) tv.tv_usec);

    // Form the seconds of the day
    long hms = tv.tv_sec % SEC_PER_DAY;
    hms += tz.tz_dsttime * SEC_PER_HOUR;
    hms -= tz.tz_minuteswest * SEC_PER_MIN;
    // mod `hms` to insure in positive range of [0...SEC_PER_DAY)
    hms = (hms + SEC_PER_DAY) % SEC_PER_DAY;

    // Tear apart hms into h:m:s
    int hour = hms / SEC_PER_HOUR;
    int min = (hms % SEC_PER_HOUR) / SEC_PER_MIN;
    int sec = (hms % SEC_PER_HOUR) % SEC_PER_MIN; // or hms % SEC_PER_MIN

    stringstream prueba;
    prueba << hour-5 <<":" << min <<":" << sec;
    string temp_str = prueba.str();
    char* char_type = (char*)temp_str.c_str();
    //printf("Current local time: %d:%02d:%02d\n", hour-5, min, sec);
    cout << char_type;
    return 0;
}