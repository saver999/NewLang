package Visitor;
import nodi.*;

import java.util.ArrayList;

public interface Visitatore {
    public String visit(ExprNode node);
    String visit(BoolConst node);

    String visit(IntegerConst node);
    String visit(RealConst node);
    String visit(StringConst node);
    String visit(CharConst node);
    String visit(IdVal node);
    String visit(FuncallNode node);

    String visit(AssignStat node);
    String visit(WriteStat node);
    String visit(ReadStat node);

    String visit(ParDecl node);
    String visit (IDInit node);

    String visit(IDInitObb node);

    String visit(VarDecl node);

    String visit(Const node);
    String visit(Stat node);



    String visit(Body node);

    String visit(Body node, ArrayList<ParDecl> parDecls);

    String visit(IfStat node);

    String visit(WhileStat node);

    String visit(ForStat node);

    String visit(FunDecl node);

    String visit(MainFunDecl node);

    String visit(ProgramRoot node);

    Object visit(ElseStat node);
}
