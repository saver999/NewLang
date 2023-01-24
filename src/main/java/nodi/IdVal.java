package nodi;

import Visitor.Visitatore;

public class IdVal extends Node{

    public String val;

    public String typeNode;
    public boolean isOut;

    public IdVal(String val) {
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
