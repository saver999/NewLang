package esercitazione5;


import Visitor.AnalisiSemantica;
import Visitor.GenerazioneCodiceC;
import Visitor.ScopingVisitor;
import Visitor.TreeMaker;
import nodi.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("nome file mancante");
            System.exit(1);
        }
        Path inputPath = Paths.get(args[0]);
        String inputFileName = inputPath.getFileName().toString();
//        if(inputFileName.contains("invalid")) {
//            System.err.println("il file " + inputPath + " contiene un errore");
//            System.exit(2);
//        }
        int dotIndex = inputFileName.lastIndexOf(".");
        String cFileName = (dotIndex == -1 ? inputFileName : inputFileName.substring(0, dotIndex)) + ".c";
        String coutdir = "test_files"+ File.separator+"c_out";
        Path cFilePath = Path.of(coutdir, cFileName);

        FileReader inFile = new FileReader(args[0]);


        parser p = new parser(new Yylex(inFile));


        ProgramRoot pr = (ProgramRoot) p.parse().value;
        TreeMaker ev = new TreeMaker();
        String result = (String) pr.accept(ev);
        System.out.println("result: " + result);
        ev.saveFileXML();

        ScopingVisitor sv = new ScopingVisitor();
        pr.accept(sv);

        AnalisiSemantica as = new AnalisiSemantica();
        pr.accept(as);
       // System.out.println("Programma corretto semanticamente? ");
        if(pr.typeNode.equals("notype")) {
           // System.out.println("SI");
            GenerazioneCodiceC gc = new GenerazioneCodiceC();
            pr.accept(gc);//generazione codice C
           // System.out.println("##### GENERAZIONE CODICE C #####");
            String codiceC=gc.saveFileC();
            try {
                Files.createDirectories(Path.of(coutdir));
                Files.writeString(cFilePath, codiceC);
            } catch (IOException e) {
                System.err.println("impossibile scrivere il file " + cFilePath);
                System.exit(3);
            }

            runProgramInC();
        }else {
           // System.out.println("NO");
        }
    }

    public static void runProgramInC(){
    String os = System.getProperty("os.name").toLowerCase();

    try {
        Process p = Runtime.getRuntime().exec("gcc -v");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    } catch (Exception e) {
        System.out.println("GCC is not installed on this system.");
        e.printStackTrace();
    }

    if (os.contains("win")) {

        try {
            Runtime rt = Runtime.getRuntime();
            Process processGCC = rt.exec("gcc output/main.c -o output/main.exe");
            processGCC.waitFor();
            Process processEXE = Runtime.getRuntime().exec("cmd /k start cmd.exe @cmd /k output\\main.exe");
            processEXE.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (os.contains("mac")) {
        try {
            String gccCommand = "gcc -o main output/main.c";
            Process p = Runtime.getRuntime().exec(gccCommand);
            p.waitFor();

            String terminalCommand = "open -a Terminal ";
            Runtime.getRuntime().exec(terminalCommand + "./main");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




}


