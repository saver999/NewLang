package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class WriteStat extends Node{
    public String nomeNodo;
    public ArrayList<ExprNode> listaExpr;

    public WriteStat(String nomenodo, ArrayList<ExprNode> listaExpr) {
        this.nomeNodo = nomenodo;
        this.listaExpr = listaExpr;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
