package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class FuncallNode extends Node {
    public String nomeNodo;
    public IdVal id;
    public ArrayList<ExprNode> listaExprNode;

    public FuncallNode(String nomeNodo, IdVal id){
        this.nomeNodo = nomeNodo;
        this.id = id;
    }
    public FuncallNode(String nomeNodo, IdVal id,ArrayList<ExprNode> listaExprNode){
        this.nomeNodo = nomeNodo;
        this.id = id;
        this.listaExprNode = listaExprNode;
    }

    public Object accept(Visitatore v){
        return v.visit(this);}
    }


