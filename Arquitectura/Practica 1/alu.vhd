library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity alu is port(
	uno: in std_logic_vector(3 downto 0);--dato de entrada 1 que porteamos desde Entradas1
	dos: in std_logic_vector(3 downto 0);--dato de entrada 2 que porteamos desde Entradas1
	operando: in std_logic_vector(2 downto 0);--dato donde seleccionamos el tipo de operando con 5 opciones disintas
	save:in std_logic;--boton para guardar el dato en un registro de nombre dato
	cargar:in std_logic;--boton en el que tomamos el valor guardado en el registro dato para operar con el
	dato:inout std_logic_vector(3 downto 0);--registro donde guardamos el dato de entrada
	salida: out std_logic_vector(3 downto 0));--salida que portearemos al 7 segmentos
end alu;

architecture op of alu  is
begin
	process(uno,dos,operando,save,cargar,dato)
	 begin
		if(save='1') then--si save esta en 1 guardamos el valor de uno en dato
			dato<=uno;
		end if;
		
		if(cargar='0') then-- si cargar esta en 0 trabajamos con los valores de uno y dos para operar con ellos
			case operando is
				when"000"=>salida<=uno+dos;
				when"001"=>salida<=uno-dos;
				when"010"=>salida<=uno and dos;
				when"011"=>salida<=uno or dos;
				when others=>salida<=dos-uno;
			end case;	
		else -- si cargar esta en 1 trabajamos con el registro "dato" junto con el valor de entrada 2
			case operando is
				when"000"=>salida<=dato+dos;
				when"001"=>salida<=dato-dos;
				when"010"=>salida<=dato and dos;
				when"011"=>salida<=dato or dos;
				when others=>salida<=dos-dato;
			end case;	
		end if;
		end process;	
end op;