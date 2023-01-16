package Visitor;

import nodi.*;

import java.util.ArrayList;

public class ScopingVisitor implements Visitatore{

    Env top = null; //tabella dei simboli corrente

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

        String type = parDecl.type;

        for(int i=0; i < parDecl.listaID.size(); i++){
            if(top.getInTypeEnviroment(parDecl.listaID.get(i).val) == null){
                top.put(parDecl.listaID.get(i).val, "var",null,type);
            }
        }

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
        top = new Env(top);

        for(int i=0; i< body.listaVar.size(); i++){
            body.listaVar.get(i).accept(this);
        }
        for(int i=0; i< body.listaStat.size(); i++){
            body.listaStat.get(i).accept(this);
        }

        body.currentEnv = top;
        top = top.prev;

        return null;
    }

    @Override
    public String visit(Body body, ArrayList<ParDecl> parDecls) {

        top = new Env(top);
        for(int i=0; i< parDecls.size();i++){
            parDecls.get(i).accept(this);
        }


        for(int i=0; i< body.listaVar.size(); i++){
            body.listaVar.get(i).accept(this);
        }
        for(int i=0; i< body.listaStat.size(); i++){
            body.listaStat.get(i).accept(this);
        }
        body.currentEnv = top;
        top = top.prev;


        return null;
    }

    @Override
    public String visit(IfStat ifStat) {
        ifStat.body.accept(this);
        if(ifStat.els != null){
            ifStat.els.accept(this);
        }

        return null;
    }

    public String visit(ElseStat elseStat) {

        elseStat.body.accept(this);

        return null;
    }
    @Override
    public String visit(WhileStat whileStat) {
        whileStat.body.accept(this);

        return null;
    }

    @Override
    public String visit(ForStat forStat) {
        return null;
    }

    @Override
    public String visit(FunDecl funDecl) {

        funDecl.body.accept(this,funDecl.listaPar);
        funDecl.id.accept(this);
        return null;
    }

    @Override
    public String visit(MainFunDecl mainDunDecl) {
        return null;
    }

    @Override
    public String visit(ProgramRoot programRoot) {

        top = new Env(top);


        return null;
    }
}
