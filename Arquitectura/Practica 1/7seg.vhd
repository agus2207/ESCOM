library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity decod7s is Port ( 
		D : in STD_LOGIC_vector(3 downto 0);--vector de entrada del 7 segmentos
		S : out STD_LOGIC_VECTOR (6 downto 0):="1111111"); --vector de salida, conversion de bcd a 7 seg
end decod7s;
architecture a_decod7s of decod7s is
begin
process(D)
	begin
		case D is
			when "0000" =>S<="1000000";--para altera cyclone, encendemos con '0's y el A es el bit menos significativo
			when "0001" =>S<="1111001";--y g es el bit mas significativo
			when "0010" =>S<="0100100";
			when "0011" =>S<="0110000";
			when "0100" =>S<="0011001";
			when "0101" =>S<="0010010";
			when "0110" =>S<="0000010";
			when "0111" =>S<="1111000";
			when "1000" =>S<="0000000";
			when "1001" =>S<="0011000";
			when "1010" =>S<="0001000";
			when "1011" =>S<="0000011";
			when "1100" =>S<="1000110";
			when "1101" =>S<="0100001";
			when "1110" =>S<="0000110";
			when "1111" =>S<="0001110";
         when others =>S<="0000110";
		end case;
	end process;

end a_decod7s;