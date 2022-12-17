package nodi;


import Visitor.Visitatore;

public class IDInitObb extends Node{
    public IdVal id;
    public Const cost;

    public IDInitObb(IdVal id, Const cost) {
        this.id = id;
        this.cost = cost;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
