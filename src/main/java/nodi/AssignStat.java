package nodi;

import Visitor.Visitatore;

import java.util.ArrayList;

public class AssignStat extends Node{

   public String nomeNodo;
   public ArrayList<IdVal> idList;
   public ArrayList<ExprNode> exprList;
    public String typeNode;




    public AssignStat(String nomeNodo, ArrayList<IdVal> listId, ArrayList<ExprNode> listaExprNode){
        this.nomeNodo = nomeNodo;
        this.idList = listId;
        this.exprList = listaExprNode;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
