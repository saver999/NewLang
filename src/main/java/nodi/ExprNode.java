package nodi;
import Visitor.Visitatore;
public class ExprNode extends Node{
    public String nomeNodo;
    public Node nodo1;
    public Node nodo2;

    public String typeNode;
    public ExprNode (String nomeNodo, Node nodo1) {
        this.nomeNodo = nomeNodo;
        this.nodo1 = nodo1;
    }

    public ExprNode (String nomeNodo, Node nodo1, Node nodo2) {
        this.nomeNodo = nomeNodo;
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }


    public Object accept(Visitatore v) {
        return v.visit(this);
    }
}
