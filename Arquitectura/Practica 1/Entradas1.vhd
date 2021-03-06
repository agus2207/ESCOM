library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity practica1 is port(
	uno: in std_logic_vector(3 downto 0);--dato de entrada 1
	dos: in std_logic_vector(3 downto 0);--dato de entrada 2
	operando: in std_logic_vector(2 downto 0);--selector de operador con 5 opciones distintas
	save:in std_logic;--boton para guardar registro en dato
	cargar:in std_logic;--boton para retomar el registro y operar con el
	dato: inout std_logic_vector(3 downto 0);--registro donde guardaremos el dato con el boton save
	salida: inout std_logic_vector(3 downto 0);--dato de salida despues de operar con uno y dos
	display : inout  STD_LOGIC_VECTOR(6 downto 0):="1111111");--salida al 7 segmentos
end practica1;

architecture entrada of practica1  is 

component alu is port(--porteamos la entidad del archivo alu como componente 
	uno: in std_logic_vector(3 downto 0);
	dos: in std_logic_vector(3 downto 0);
	operando: in std_logic_vector(2 downto 0);
	save:in std_logic;
	cargar:in std_logic;
	dato:inout std_logic_vector(3 downto 0);
	salida: out std_logic_vector(3 downto 0));
end component;

component decod7s is Port ( --porteamos la entidad del archivo 7seg como componente 
		D : in STD_LOGIC_vector(3 downto 0); 
		S : out STD_LOGIC_VECTOR (6 downto 0)); 
end component;

begin
	c1: alu port map(uno, dos, operando, save, cargar, dato, salida);-- hacemos port map de los datos de entrada de este archivo al archivo alu
	c2: decod7s port map(salida,display);-- hacemos port map de los datos de entrada de este archivo al archivo 7 segementos

end entrada;
