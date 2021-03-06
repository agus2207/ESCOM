reloj_display:  reloj_display.o gfxModified.o
	g++ -std=c++11 reloj_display.o gfxModified.o -o reloj_display -lX11
reloj_display.o: reloj_display.cpp gfxModified.o
	g++ -std=c++11 reloj_display.cpp -c
gfxModified.o: gfxModified.c gfxModified.h
	gcc gfxModified.c -c