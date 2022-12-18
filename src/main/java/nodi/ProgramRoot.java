package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class ProgramRoot extends Node{

    public String nomeRoot;
    public ArrayList<Node> varDeclList;
    public MainFunDecl mainFun;
    public ArrayList<Node> varDecl;

    public ProgramRoot(String nomeRoot, ArrayList<Node> varDeclList,MainFunDecl mainFun, ArrayList<Node> varDecl) {
        this.nomeRoot = nomeRoot;
        this.varDeclList = varDeclList;
        this.mainFun =mainFun;
        this.varDecl = varDecl;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
