package nodi;

import Visitor.Visitatore;

public class Stat extends Node{


    public Node nodo;


    public Stat( Node nodo){
        this.nodo = nodo;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
