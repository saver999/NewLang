package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class FunDecl extends Node{
    public String nomeNodo;
    public IdVal id;
    public ArrayList<ParDecl> listaPar;
    public String type;
    public Body body;

    public FunDecl(String nomeNodo, IdVal idval,ArrayList<ParDecl> listaPar, String type, Body body){
        this.nomeNodo = nomeNodo;
        this.id = idval;
        this.listaPar = listaPar;
        this.type = type;
        this.body=body;

    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
