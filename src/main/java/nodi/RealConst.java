package nodi;

import Visitor.Visitatore;

public class RealConst extends Node{
    public float val;

    public RealConst(float val) {
        this.val = val;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
