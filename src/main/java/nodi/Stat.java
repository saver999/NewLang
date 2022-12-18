package nodi;

import Visitor.Visitatore;

public class Stat extends Node{

    public String nomeStatement;

    public Node nodo;

    public Stat(String nomeStatement, Node nodo){
        this.nomeStatement = nomeStatement;
        this.nodo = nodo;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
