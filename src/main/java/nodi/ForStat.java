package nodi;

import Visitor.Visitatore;

public class ForStat extends Node{
    public String nomenodo;
    public IdVal id;
    public IntegerConst val1;
    public IntegerConst val2;
    public Body body;

    public ForStat(String nodo, IdVal id, IntegerConst val1, IntegerConst val2, Body body){
        this.nomenodo=nodo;
        this.id = id;
        this.val1 = val1;
        this.val2 = val2;
        this.body=body;
    }
    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
