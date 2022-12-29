package nodi;

import Visitor.Visitatore;

public class BoolConst extends Node{
    public Boolean val;
    public String typeNode;

    public BoolConst(Boolean val){
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
