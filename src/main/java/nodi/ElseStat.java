package nodi;

import Visitor.Visitatore;

public class ElseStat extends  Node {
    public String nomeNodo;
    public Body body;
    public String typeNode;

    public ElseStat(String nome, Body body) {
        this.nomeNodo = nome;
        this.body = body;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
