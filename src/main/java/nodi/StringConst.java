package nodi;

import Visitor.Visitatore;

public class StringConst extends Node{

    public String val;

    public String typeNode;
    public StringConst(String val) {
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
