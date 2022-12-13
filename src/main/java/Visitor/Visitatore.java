package Visitor;
import nodi.*;
public interface Visitatore {
    public String visit(ExprNode node);
    String visit(BoolConst boolConst);

    String visit(IntegerConst integerConst);
}
