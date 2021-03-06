prueba_display_ascii2:  prueba_display_ascii2.o gfxModified.o
	g++ -std=c++11 prueba_display_ascii2.o gfxModified.o -o prueba_display_ascii2 -lX11
prueba_display_ascii.o: prueba_display_ascii2.cpp gfxModified.o
	g++ -std=c++11 prueba_display_ascii2.cpp -c
gfxModified.o: gfxModified.c gfxModified.h
	gcc gfxModified.c -c