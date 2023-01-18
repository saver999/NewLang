package nodi;

import Visitor.Visitatore;

public class Stat extends Node{


    public Node nodo;
    public String typeNode;
    public String nameStat;
    public String tipoRitorno;

    public Stat( Node nodo){
        this.nodo = nodo;
    }
    public Stat(String nameStat, Node nodo){
        this.nodo = nodo;
        this.nameStat = nameStat;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
