package Visitor;

import nodi.*;

import java.util.ArrayList;

public class GenerazioneCodiceC implements Visitatore{
    String content;
    Env top;

    @Override
    public String visit(ExprNode node) {
        this.content ="";



        return content;
    }

    @Override
    public String visit(BoolConst node) {
        content = node.val+"";
        return content;
    }

    @Override
    public String visit(IntegerConst node) {
        content = node.val+"";
        return content;

    }

    @Override
    public String visit(RealConst node) {
        content = node.val+"";
        return content;
    }

    @Override
    public String visit(StringConst node) {
        content = "\""+node.val+"\"";

        return content;
    }

    @Override
    public String visit(CharConst node) {
        content = "\'"+node.val+"\'";

        return content;
    }

    @Override
    public String visit(IdVal node) {
        content = node.val;
        return content;
    }

    @Override
    public String visit(FuncallNode node) {
        return null;
    }

    @Override
    public String visit(AssignStat node) {
        return null;
    }

    @Override
    public String visit(WriteStat node) {
        return null;
    }

    @Override
    public String visit(ReadStat node) {
        return null;
    }

    @Override
    public String visit(ParDecl node) {
        return null;
    }

    @Override
    public String visit(IDInit node) {
        return null;
    }

    @Override
    public String visit(IDInitObb node) {
        return null;
    }

    @Override
    public String visit(VarDecl node) {
        return null;
    }

    @Override
    public String visit(Const node) {
        return null;
    }

    @Override
    public String visit(Stat node) {
        return null;
    }

    @Override
    public String visit(Body node) {
        return null;
    }

    @Override
    public String visit(Body node, ArrayList<ParDecl> parDecls) {
        return null;
    }

    @Override
    public String visit(IfStat node) {
        return null;
    }

    @Override
    public String visit(WhileStat node) {
        return null;
    }

    @Override
    public String visit(ForStat node) {
        return null;
    }

    @Override
    public String visit(FunDecl node) {
        return null;
    }

    @Override
    public String visit(MainFunDecl node) {
        return null;
    }

    @Override
    public String visit(ProgramRoot node) {
        return null;
    }

    @Override
    public Object visit(ElseStat node) {
        return null;
    }
}


