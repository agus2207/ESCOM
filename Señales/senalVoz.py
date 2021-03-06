import matplotlib.pyplot as plt
import wave
import numpy as np
import sys

CHUNK = 256
SIGSCALE = 1.5


def intToBytes(x, tam):
	result = []
	for i in range (0, tam):
		result.append(x >> (i*8) & 0xff)
		result.reverse()
	return result



x = intToBytes(CHUNK, 16)

plt.figure(1)
plt.title('Señal de voz')
r = 2**16/2
a = plt.subplot(211)
a.set_ylim([-r,r])
a.set_xlabel('tiempo[s]')
a.set_ylabel('señal')


amplitude = np.frombuffer(bytes(x), dtype="int16")
plt.plot(amplitude)




