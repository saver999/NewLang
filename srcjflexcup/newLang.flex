
package esercitazione4;
import java_cup.runtime.Symbol; //This is how we pass tokens to the parser

%%
// Declarations for JFlex
%unicode // We wish to read text files

%cup // Declare that we expect to use Java CUP
// Abbreviations for regular expressions

whitespace = [ \r\n\t\f]

symbol = [$_A-Za-z]
all_symbol = [$_A-Za-z0-9]
digit =[0-9]
integer = {digit}+
real = {digit}+("."{digit}+)?
id = {symbol}({all_symbol})*
%%
// Now for the actual tokens and assocated actions
"start:" { return new Symbol(sym.MAIN) ; }
";" { return new Symbol(sym.SEMI); }
","  { return new Symbol(sym.COMMA); }
"|"  { return new Symbol(sym.PIPE); }
"var" { return new Symbol(sym.VAR);}
"integer" { return new Symbol(sym.INTEGER);}
"real" { return new Symbol(sym.REAL);}
"string" { return new Symbol(sym.STRING);}
"boolean" { return new Symbol(sym.BOOL);}
"char" { return new Symbol(sym.CHAR);}
"void"  { return new Symbol(sym.VOID);}
"end" { return new Symbol(sym.END);}

"def"  { return new Symbol(sym.DEF);}
"out"   { return new Symbol(sym.OUT);}
"for"  { return new Symbol(sym.FOR);}
"if"  { return new Symbol(sym.IF);}
"else"  { return new Symbol(sym.ELSE);}
"while" { return new Symbol(sym.WHILE);}
"to"  { return new Symbol(sym.TO);}
"loop" { return new Symbol(sym.LOOP);}
"<--"  { return new Symbol(sym.READ);}
"-->"  { return new Symbol(sym.WRITE);}
"-->!"  { return new Symbol(sym.WRITELN);}

"("  { return new Symbol(sym.LPAR);}
")"  { return new Symbol(sym.RPAR);}
"{"  { return new Symbol(sym.LBRACK);}
"}"  { return new Symbol(sym.RBRACK);}
":"  { return new Symbol(sym.COLON);}
"<<"  { return new Symbol(sym.ASSIGN);}
"return" { return new Symbol(sym.RETURN);}
"end" { return new Symbol(sym.END); }

"true" { return new Symbol(sym.TRUE);}
"false" { return new Symbol(sym.FALSE);}
"+"  { return new Symbol(sym.PLUS);}
"-"  { return new Symbol(sym.MINUS);}
"*"  { return new Symbol(sym.TIMES);}
"/"  { return new Symbol(sym.DIV);}
"^"  { return new Symbol(sym.POW);}
"&"  { return new Symbol(sym.STR_CONCAT);}
"="  { return new Symbol(sym.EQ);}
"<>" { return new Symbol(sym.NE);}
"!="  { return new Symbol(sym.NE);}
"<"  { return new Symbol(sym.LT);}
"<=" { return new Symbol(sym.LE);}
">"  { return new Symbol(sym.GT);}
">=" { return new Symbol(sym.GE);}
"and" { return new Symbol(sym.AND);}
"or"  { return new Symbol(sym.OR);}
"not"  { return new Symbol(sym.NOT);}

{id} { return new Symbol(sym.ID,yytext()); }
{integer} {return new Symbol(sym.INTEGER_CONST, yytext());}
{real} {return new Symbol(sym.REAL_CONST, yytext());}

{whitespace} { /* ignore */ }
[^]           { throw new Error("\n\nIllegal character < "+ yytext()+" >\n"); }
// End of file