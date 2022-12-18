package Visitor;
import nodi.*;
public interface Visitatore {
    public String visit(ExprNode node);
    String visit(BoolConst boolConst);

    String visit(IntegerConst integerConst);
    String visit(RealConst realConst);
    String visit(StringConst stringConst);
    String visit(CharConst charConst);
    String visit(IdVal idVal);
    String visit(FuncallNode funcall);

    String visit(AssignStat assignStat);
    String visit(WriteStat writeStat);
    String visit(ReadStat readStat);

    String visit(ParDecl parDecl);
    String visit (IDInit idInit);

    String visit(IDInitObb idInitObb);

    String visit(VarDecl varDecl);

    String visit(Const cost);
    String visit(Stat stat);



    String visit(Body body);
}
