package nodi;

import Visitor.Visitatore;

public class IfStat extends Node{
    public String nomenodo;
    public ExprNode nodeEx;
    public Body body;
    public ElseStat els;
    public String tipoRitorno;

    public String typeNode;

    public IfStat(String nome, ExprNode node, Body body, ElseStat els){
        this.nomenodo=nome;
        this.nodeEx= node;
        this.body=body;
        this.els = els;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
