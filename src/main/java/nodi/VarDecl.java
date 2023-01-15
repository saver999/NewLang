package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class VarDecl extends Node{
    public String nomeNodo;
    public String type;
    public ArrayList<IDInit> listaID;
    public ArrayList<IDInitObb> idInitObb;

    public String typeNode;


    public VarDecl(String nomeNodo, String type, ArrayList<IDInit> listaID) {
        this.nomeNodo = nomeNodo;
        this.type = type;
        this.listaID = listaID;
    }
    public VarDecl(String nomeNodo, ArrayList<IDInitObb> listaIDObb) {
        this.nomeNodo = nomeNodo;
        this.idInitObb = listaIDObb;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
