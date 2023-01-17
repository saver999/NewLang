package nodi;

import Visitor.Env;
import Visitor.Visitatore;

import java.util.ArrayList;

public   class Body extends Node{
    public String nomeNodo;
    public ArrayList<VarDecl> listaVar;
    public ArrayList<Stat> listaStat;

    public String typeNode;

    public Env currentEnv;

    public Body(String nomeNodo, ArrayList<VarDecl> listaVar, ArrayList<Stat> listaStat){
        this.nomeNodo = nomeNodo;
        this.listaVar = listaVar;
        this.listaStat = listaStat;

    }



    public Object accept(Visitatore v) {
        return v.visit(this);
    }
    public Object accept(Visitatore v,ArrayList<ParDecl> pardecls) {
        return v.visit(this, pardecls);
    }

    public Object accept(Visitatore v,IdVal idVal) {
        return v.visit(this,  idVal);
    }

}
