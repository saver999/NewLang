package Visitor;

import java.util.*;
public class Env {
    private Hashtable<String, RecordSymbolTable> table;
    protected Env prev;

    public Env(Env p) {
        table = new Hashtable<String, RecordSymbolTable>();
        prev = p;
    }

    public void put(String s, String nome, String kind, ArrayList<String> typeParametri, ArrayList<String> typeRitorno) {
        table.put(s, new RecordSymbolTable(nome, kind, typeParametri, typeRitorno));
    }

    public RecordSymbolTable getInTypeEnviroment(String s) {
        for( Env e = this; e != null; e = e.prev ) {
            RecordSymbolTable found = (RecordSymbolTable)(e.table.get(s));
            if( found != null ) return found;
        }
        return null;
    }

    public RecordSymbolTable getInThisTable(String s) {
        RecordSymbolTable found = (RecordSymbolTable)(table.get(s));

        return found;
    }

    public Hashtable<String, RecordSymbolTable> getTable (){
        return table;
    }

}
