package Visitor;

import nodi.*;

public class ScopingVisitor implements Visitatore{

    public Env getTable(){
        return top;
    }

    @Override
    public String visit(ExprNode node) {
        return null;
    }

    @Override
    public String visit(BoolConst boolConst) {
        return null;
    }

    @Override
    public String visit(IntegerConst integerConst) {
        return null;
    }

    @Override
    public String visit(RealConst realConst) {
        return null;
    }

    @Override
    public String visit(StringConst stringConst) {
        return null;
    }

    @Override
    public String visit(CharConst charConst) {
        return null;
    }

    @Override
    public String visit(IdVal idVal) {
        return null;
    }

    @Override
    public String visit(FuncallNode funcall) {
        return null;
    }

    @Override
    public String visit(AssignStat assignStat) {
        return null;
    }

    @Override
    public String visit(WriteStat writeStat) {
        return null;
    }

    @Override
    public String visit(ReadStat readStat) {
        return null;
    }

    @Override
    public String visit(ParDecl parDecl) {
        return null;
    }

    @Override
    public String visit(IDInit idInit) {
        return null;
    }

    @Override
    public String visit(IDInitObb idInitObb) {
        return null;
    }

    @Override
    public String visit(VarDecl varDecl) {
        return null;
    }

    @Override
    public String visit(Const cost) {
        return null;
    }

    @Override
    public String visit(Stat stat) {
        return null;
    }

    @Override
    public String visit(Body body) {
        return null;
    }

    @Override
    public String visit(IfStat ifStat) {
        return null;
    }

    @Override
    public String visit(WhileStat whileStat) {
        return null;
    }

    @Override
    public String visit(ForStat forStat) {
        return null;
    }

    @Override
    public String visit(FunDecl funDecl) {
        return null;
    }

    @Override
    public String visit(MainFunDecl mainDunDecl) {
        return null;
    }

    @Override
    public String visit(ProgramRoot programRoot) {
        return null;
    }
}
