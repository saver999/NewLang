package nodi;

import Visitor.Visitatore;

public class Const extends Node{
    public String nomeNodo;
    public Node nodo;

    public Const (String nomeNodo, Node nodo) {
        this.nomeNodo = nomeNodo;
        this.nodo = nodo;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
