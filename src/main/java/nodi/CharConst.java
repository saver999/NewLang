package nodi;

import Visitor.Visitatore;

public class CharConst extends Node{

    public char val;

    public CharConst(char val) {
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
