package Visitor;
import nodi.*;
public interface Visitatore {
    public String visit(ExprNode node);
    String visit(BoolConst boolConst);

    String visit(IntegerConst integerConst);
    String visit(RealConst realConst);
    String visit(StringConst stringConst);
    String visit(CharConst charConst);
}
