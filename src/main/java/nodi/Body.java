package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class Body extends Node{
    public String nomeNodo;
    public ArrayList<VarDecl> listaVar;
    public ArrayList<StatList> listaStat;

    public Body(String nomeNodo, ArrayList<VarDecl> listaVar, ArrayList<StatList> listaStat){
        this.nomeNodo = nomeNodo;
        this.listaVar = listaVar;
        this.listaStat = listaStat;

    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
