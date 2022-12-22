package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class ProgramRoot extends Node{

    public String nomeRoot;
    public ArrayList<Node> declist1;
    public MainFunDecl mainFun;
    public ArrayList<Node> declist2;

    public ProgramRoot(String nomeRoot, ArrayList<Node> declist1,MainFunDecl mainFun, ArrayList<Node> declist2) {
        this.nomeRoot = nomeRoot;
        this.declist1 = declist1;
        this.mainFun =mainFun;
        this.declist2 = declist2;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
