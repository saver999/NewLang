package Visitor;

import nodi.*;

import java.util.ArrayList;

public class AnalisiSemantica implements Visitatore{

    Env top = null; //tabella dei simboli corrente
    ArrayList<Env> stack = new ArrayList<Env>(); //stack per tenere conto di tutte le tabelle dei simboli
    @Override
    public String visit(ExprNode node) {
        return null;
    }

    @Override
    public String visit(BoolConst node) {

        node.typeNode = "BOOL";
        return null;
    }

    @Override
    public String visit(IntegerConst node) {
        node.typeNode =" INT";
        return null;
    }

    @Override
    public String visit(RealConst node) {
        node.typeNode = "FLOAT";
        return null;
    }

    @Override
    public String visit(StringConst node) {
        node.typeNode = "STRING";
        return null;
    }

    @Override
    public String visit(CharConst node) {
        node.typeNode = "CHAR";
        return null;
    }

    @Override
    public String visit(IdVal node) {
        return null;
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
    public String visit(IfStat node) {
        top = new Env(top); //tabella dei simboli per if
        stack.add(top);
        return null;
    }

    @Override
    public String visit(WhileStat node) {
        top = new Env(top); //tabella dei simboli del while
        stack.add(top);
        return null;
    }

    @Override
    public String visit(ForStat node) {
        top = new Env(top); //tabella dei simboli per il for
        stack.add(top);
        return null;
    }

    @Override
    public String visit(FunDecl node) {
        top = new Env(top); //tabella dei simboli per dichiarazioni di funzioni
        stack.add(top);
        return null;
    }

    @Override
    public String visit(MainFunDecl node) {
        return null;
    }

    @Override
    public String visit(ProgramRoot node) {
        top = new Env(top); //tabella dei simboli root
        stack.add(top);

        return null;
    }
}
