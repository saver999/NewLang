package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class ReadStat extends Node{
    public String nomeNodo;
    public ArrayList<IdVal> idList;
    public StringConst val;

    public ReadStat(String nomeNodo, ArrayList<IdVal> listaID, StringConst val){
        this.nomeNodo = nomeNodo;

        this.val= val;
        idList = listaID;
    }


    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
