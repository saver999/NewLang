package Visitor;

import java.util.*;
/*
*
* La classe in questione rappresenta una singola tabella del typeEvniroment
* prev punta alla tabella padre
* */
public class Env {

    private Hashtable<String, RecordSymbolTable> table;
    protected Env prev;

    public Env(Env p) {
        table = new Hashtable<String, RecordSymbolTable>();
        prev = p;
    }

    public void put( String nome, String kind, ArrayList<String> typeParametri, String typeRitorno) {
        table.put(nome, new RecordSymbolTable(nome, kind, typeParametri, typeRitorno,false));
    }

    public void put( String nome, String kind, ArrayList<String> typeParametri, String typeRitorno,Boolean isout) {
        table.put(nome, new RecordSymbolTable(nome, kind, typeParametri, typeRitorno,isout));

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
