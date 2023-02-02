package Visitor;


import nodi.ParDecl;

import java.util.ArrayList;

/*
* Questa classe rappresenta un determinato record di una tabella dei simboli contine informazioni come:
* Nome: il nome dell'identificativo
* Kind: il tipo "fun" se una funzione ,"var" se una variabile,"mainFunc" se si tratta del main
* typeParametri: è un'arraylist contenente il tipo di ogni parametro
* typeRitorno: nel caso di una funzione rappresenta il tipo di ritorno di quest'ultima, nel caso di una variabile contine il tipo
* isout: ci dice se una funzione ha un parametro passato per riferimento
* */

public class RecordSymbolTable {
    public String nome;
    public String kind;
    public ArrayList<String> typeParametri;
    public String typeRitorno;

    public Boolean isout;

    public ArrayList<ParDecl> parDecls;

    public RecordSymbolTable(String nome, String kind, ArrayList<String> typeParametri, String typeRitorno, Boolean isout) {
        this.isout=isout;
        this.nome = nome;
        this.kind = kind;
        this.typeParametri = typeParametri;
        this.typeRitorno = typeRitorno;
    }

    @Override
    public String toString() {
        return "RecordSymbolTable{" +
                "nome=" + nome +
                ", kind='" + kind + '\'' +
                ", typeParametri=" + typeParametri +
                ", typeRitorno=" + typeRitorno +
                ", isOut=" + isout +
                ", parDecls=" + parDecls +
                '}';
    }
}
