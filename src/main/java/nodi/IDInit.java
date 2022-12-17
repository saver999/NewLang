package nodi;


import Visitor.Visitatore;

public class IDInit extends Node{
    public IdVal id;
    public ExprNode expr;

    public IDInit(IdVal id, ExprNode expr) {
        this.id = id;
        this.expr = expr;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
