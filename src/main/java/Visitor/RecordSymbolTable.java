package Visitor;


import nodi.ParDecl;

import java.util.ArrayList;

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
