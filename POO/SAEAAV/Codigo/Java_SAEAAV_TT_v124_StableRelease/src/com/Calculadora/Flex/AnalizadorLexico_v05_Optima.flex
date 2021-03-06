package Principal;
import java_cup.runtime.Symbol;
%%
%cup
%ignorecase
%type java_cup.runtime.Symbol
%implements java_cup.runtime.Scanner
%class scanner
%line
%column
%{
private String valor;
private int numeroDeLinea;
private String simbolo;
private int numeroDeColumna;
public String getValor() 
{
    return valor;
}
public int getNumeroDeLinea() 
{
    return numeroDeLinea;
}
public int getNumeroDeColumna() 
{
    return numeroDeColumna;
}
public String getSimbolo() 
{
        return simbolo;
}
public void PrintToken(String str)
{
    //System.out.println(str);
}
%}
%eofval{
//System.out.println("Fin del archivo");
return null;
%eofval}
DIGITO=[0-9]
NUMERO_ENTERO={DIGITO}+
NUMERO_REAL={NUMERO_ENTERO}("."{NUMERO_ENTERO})?
EXTRAS=[\t\r\n]
%%
";" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_PTOCOMA); }
"+" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_MAS); }
"-" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_MENOS); }
"*" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_MULT); }
"/" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_DIV); }
"%" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_MOD); }
"^" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_POT); }
sen { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_SEN); }
cos { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_COS); }
tan { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_TAN); }
senh { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_SENH); }
cosh { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_COSH); }
tanh { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_TANH); }
arcsen { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_ARCSEN); }
arccos { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_ARCCOS); }
arctan { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_ARCTAN); }
log { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_LOG); }
sqrt { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_SQRT); }
rad { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_RAD); }
deg { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_DEG); }
exp { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_EXP); }
PI { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_PI); }
E { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_E); }
"(" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_PABRE); }
")" { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_PCIERRA); }
x { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_VARX); }
y { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_VARY); }
z { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_VARZ); }
{NUMERO_REAL} { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_NUMREAL, new Double(Double.parseDouble(yytext()))); }
{EXTRAS} {  /*No hacer nada*/ }
" " {  /*No hacer nada*/ }
. { this.numeroDeLinea=yyline+1; this.numeroDeColumna=yycolumn+1; this.valor=yytext(); return new Symbol(sym.TKN_ERROR); /*No hacer nada-Se encontro error*/}