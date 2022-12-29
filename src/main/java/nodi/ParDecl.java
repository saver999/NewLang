package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class ParDecl {

    public String nomeNodo;
    public String type;
    public ArrayList<IdVal> listaID;

    public String typeNode;

    public ParDecl(String nomeNodo,String type,ArrayList<IdVal> listaID){
        this.nomeNodo = nomeNodo;
        this.type = type;
        this.listaID = listaID;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
