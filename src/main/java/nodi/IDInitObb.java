package nodi;


import Visitor.Visitatore;

public class IDInitObb extends Node{
    public IdVal id;
    public String cost;

    public IDInitObb(IdVal id, String cost) {
        this.id = id;
        this.cost = cost;
    }

    public Object accept(Visitatore v) {
        return v.visit(this);
    }

}
