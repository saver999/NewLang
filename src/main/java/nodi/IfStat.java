package nodi;

import Visitor.Visitatore;

public class IfStat extends Node{
    public String nomenodo;
    public ExprNode nodeEx;
    public Body body;

    public IfStat(String nome, ExprNode node, Body body){
        this.nomenodo=nome;
        this.nodeEx= node;
        this.body=body;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
