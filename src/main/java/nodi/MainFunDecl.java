package nodi;

import Visitor.Visitatore;

public class MainFunDecl extends Node{
    public String nomeNodo;
    public FunDecl fundecl;

    public String typeNode;

    public MainFunDecl(String nomeNodo, FunDecl fundecl) {
        this.nomeNodo=nomeNodo;
        this.fundecl = fundecl;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
