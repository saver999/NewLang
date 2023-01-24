package esercitazione5;


import Visitor.AnalisiSemantica;
import Visitor.GenerazioneCodiceC;
import Visitor.ScopingVisitor;
import Visitor.TreeMaker;
import nodi.*;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader inFile = new FileReader(args[0]);
        /*System.out.println("Type in circuit, hit Return, then Cmd-D (in MacOs) o Ctrl-D (in Windows)");
        InputStreamReader inp = new InputStreamReader(System.in);
        Reader keyboard = new BufferedReader(inp);*/
        parser p = new parser(new Yylex(inFile));
        //System.out.println("Nodo radice is "+ p.debug_parse().value); // l'uso di p.debug_parse() al posto di p.parse() produce tutte le azioni del parser durante il riconoscimento


       ProgramRoot pr = (ProgramRoot) p.parse().value;
        TreeMaker ev = new TreeMaker();
        String result = (String) pr.accept(ev);
        System.out.println("result: " + result);
        ev.saveFileXML();

        ScopingVisitor sv = new ScopingVisitor();
        pr.accept(sv);

        AnalisiSemantica as = new AnalisiSemantica();
        pr.accept(as);
        System.out.println("Programma corretto semanticamente? ");
        if(pr.typeNode.equals("notype")) {
            System.out.println("SI");
            GenerazioneCodiceC gc = new GenerazioneCodiceC();
            pr.accept(gc);//generazione codice C
            System.out.println("##### GENERAZIONE CODICE C #####");
            gc.saveFileC();
        }else {
            System.out.println("NO");
        }
    }
}


