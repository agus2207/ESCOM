import threading

import matplotlib.pyplot as plt
import numpy as np
import wave
import sys
import serial


PRUEBA = 128
RATE = 44100
BYTESPRUEBA = bytes(PRUEBA)
f = 1  # Frequency, in cycles per second, or Hertz
Fs = 50000  # Sampling rate, or number of measurements per second
ff = 1024  # frequency of the signal
Ts = 1/Fs
fMax = 2700
t = np.arange(0, 1, Ts) # Time Vector



# fig, ax = plt.subplots(2, 1)




ser = serial.Serial('COM3', 9600, timeout=5)
###### PRUEBA PARA LEER DATOS A LA COMPU
#### RECORDAR USAR PYSERIAL no SERIAL
# ser = serial.Serial('/dev/ttyUSB1', 2000000, timeout=None)
ser.flushInput()
ser.flushOutput()
#
if ser.is_open:
    print("Puerto abierto")
    while True:
        bytesToRead = ser.inWaiting()
        ser.read(bytesToRead)
        # bytesToRead = ser.readline()
        print(bytesToRead)
#
#         t = np.linspace(0, 2, 2 * f_s, endpoint=False)
#         # x = np.sin(f * 2 * bytesToRead * t)
#         x = np.sin(f * 2 * PRUEBA * t)
#         # spf = wave.open('prueba.wav','r')
#         #Extract Raw Audio from Wav File
#         # signal = spf.readframes(-1)
#         #x = np.linspace(0,100,10)
#         #y = heaviside(np.random.random(10)-.5)
#         signal = np.fromstring(BYTESPRUEBA, 'Int8')
#
#         #If Stereo
#         # if spf.getnchannels() == 2:
#         #     print ('Just mono files')
#         #     sys.exit(0)
#         # plt.title('Señal de Voz')
#         # plt.figure(1)
#         fig, ax = plt.subplots()
#         ax.plot(t, x)
#         ax.set_xlabel('Tiempo [s]')
#         ax.set_ylabel('Amplitud');
#         # plt.step(x,y)
#         # plt.plot(signal)
#         plt.show()
# else:
#     print("El puerto no esta abierto")


######  PRUEBAS MANUALES
i=0
while True:
    i += 1
    # ff = 2 ** i
    # x = np.linspace(0, 2, 2 * Fs, endpoint=False)
    # x = np.sin(f * 2 * bytesToRead * t)
    # y = np.sin(f * 2 * (PRUEBA * i) * x)
    # spf = wave.open('prueba.wav','r')
    #Extract Raw Audio from Wav File
    # signal = spf.readframes(-1)
    #x = np.linspace(0,100,10)
    #y = heaviside(np.random.random(10)-.5)
    signal = np.fromstring(BYTESPRUEBA, 'Int16')
    y = np.sin(2 * np.pi * ff * t)
    print("tasa x ", y)
    n = len(y)  # length of the signal
    k = np.arange(n)
    T = n / Fs
    frq = k / T  # two sides frequency range
    frq = frq[range(n // 2)]  # one side frequency range
    Y = np.fft.fft(y) / n  # fft computing and normalization
    Y = Y[range(n // 2)]
    # fig, ax = plt.subplots()
    plt.clf()
    # threading.Timer(5.0, plt.clf()).start()
    plt.title('Señal de Voz')
    plt.xlabel('Tiempo')
    plt.ylabel('Amplitud')
    # plt.figure(1)
    # plt.plot(x, y)
    plt.plot(frq, abs(Y))
    # ax.set_xlabel('Tiempo [s]')
    # ax.set_ylabel('Amplitud')
    plt.pause(0.01)
    # plt.step(x,y)
    # plt.plot(signal)

plt.show()

