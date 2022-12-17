package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class StatList extends Node{
    public String nomeNodo;
    public ArrayList<Node> listaStat;

    public StatList(String nomeNodo, ArrayList<Node> listaStat){
        this.nomeNodo = nomeNodo;
        this.listaStat = listaStat;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
