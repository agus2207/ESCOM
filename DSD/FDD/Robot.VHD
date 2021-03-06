library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Robot is
Port ( 
  A,B,C,D,E : in std_logic;
  F1, F2 : out std_logic 
);
end Robot;

architecture rtl  of Robot is
begin
  F1<=  (D AND E) OR (not A AND D) OR (A AND not B AND not D) 
  OR (not A AND not B AND not E );

  F2 <= (not A AND D AND E) OR (A AND B AND not D) 
  OR (A AND B AND not E) OR (not B AND D AND E);
end rtl;