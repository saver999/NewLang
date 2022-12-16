package nodi;

import Visitor.Visitatore;

public class CharConst extends Node{

    public String val;

    public CharConst(String val) {
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
