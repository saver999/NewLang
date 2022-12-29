package esercitazione5;

import java_cup.runtime.Symbol;

import java.io.FileReader;
import java.io.IOException;

public class TesterLexer {

    public static void main(String[] args) throws IOException {
        FileReader inFile = new FileReader(args[0]);
        Yylex lexer = new Yylex(inFile);

        Symbol token;
        while ((token = lexer.next_token()).sym != sym.EOF) {

            if(token.sym == sym.error) {
                System.out.println("<" + sym.terminalNames[token.sym] + ", " + "\"" + token.value + "\"" + ">" +
                        " (" + args[0] + ": " + (token.left+1) + ", " + (token.right+1) + ")" );
                continue;
            }
            if(token.value != null) {
                System.out.println("<" + sym.terminalNames[token.sym] + ", " + "\"" + token.value + "\"" + ">");
            } else {
                System.out.println("<" + sym.terminalNames[token.sym] + ">");
            }
        }


    }
}
