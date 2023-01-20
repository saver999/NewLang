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
    public String visit(BoolConst node) {

        node.typeNode = "BOOL";
        return null;
    }

    @Override
    public String visit(IntegerConst node) {
        node.typeNode = "INTEGER";
        return null;
    }

    @Override
    public String visit(RealConst node) {
        node.typeNode = "REAL";
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
    public String visit(VarDecl node) {


        //scorriamo uan lista di id nella casisitica dove il type è esplicito
        if(node.listaID !=null) {
            for (int i = 0; i < node.listaID.size(); i++) {
                if (top.getInThisTable(node.listaID.get(i).id.val) == null) {//controlla se è nella tabella corrente
                    RecordSymbolTable recordPrec;
                    recordPrec = top.getInTypeEnviroment(node.listaID.get(i).id.val);
                    if (recordPrec == null) {
                        top.put(node.listaID.get(i).id.val, "var", null, node.type);//inserisce nella tabella al top
                    } else {
                        if (recordPrec.kind.equalsIgnoreCase("var")) {
                            top.put(node.listaID.get(i).id.val, "var", null, node.type); //ci assicuriamo che sia una variabile se si tartta di un metodo allora errore es: int a a()
                        } else {
                            try {

                                throw new Exception("Esiste già una funzione con lo stesso nome: " + node.listaID.get(i).id.val);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } else {
                    try {

                        throw new Exception("Dichiarazione multipla");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // idinitobblist
if(node.idInitObb != null) {
    for (int i = 0; i < node.idInitObb.size(); i++) {
        if (top.getInThisTable(node.idInitObb.get(i).id.val) == null) {//controlla se è nella tabella corrente
            RecordSymbolTable recordPrec;
            recordPrec = top.getInTypeEnviroment(node.idInitObb.get(i).id.val);

            if (recordPrec == null) {

                node.idInitObb.get(i).cost.accept(this);
                top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode);//inserisce nella tabella al top

            } else {
                if (recordPrec.kind.equalsIgnoreCase("var")) {
                    node.idInitObb.get(i).cost.accept(this);
                    top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode); //ci assicuriamo che sia una variabile se si tartta di un metodo allora errore es: int a a()
                } else {
                    try {

                        throw new Exception("Esiste già una funzione con lo stesso nome: " + node.idInitObb.get(i).id.val);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            try {

                throw new Exception("Dichiarazione multipla");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

        return null;
    }

    @Override
    public String visit(Const node) {

        Class classe = node.nodo.getClass();

        if(classe == BoolConst.class){
            BoolConst nodo = (BoolConst)node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }  else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst)node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        } else if(classe == StringConst.class){
            StringConst nodo = (StringConst)node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        } else if(classe == CharConst.class) {
            CharConst nodo = (CharConst) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }
        return null;
    }


    @Override
    public String visit(Stat node) {

        Class classe = node.nodo.getClass();

        if (classe == IfStat.class) {
            IfStat nodo = (IfStat) node.nodo;
             nodo.accept(this);
        }else if(classe == ForStat.class){
            ForStat nodo =(ForStat) node.nodo;
            nodo.accept(this);
        }else if(classe == WriteStat.class){
            WriteStat nodo =(WriteStat) node.nodo;
            nodo.accept(this);
        }else if(classe == AssignStat.class){
            AssignStat nodo =(AssignStat) node.nodo;
            nodo.accept(this);
        }else if(classe == FuncallNode.class){
            FuncallNode nodo =(FuncallNode) node.nodo;
            nodo.accept(this);
        }else if(classe == WhileStat.class){
            WhileStat nodo =(WhileStat) node.nodo;
            nodo.accept(this);
        }else if(classe == ReadStat.class){
            ReadStat nodo =(ReadStat) node.nodo;
            nodo.accept(this);
        }else if(classe == ExprNode.class){
            ExprNode nodo =(ExprNode) node.nodo;
            nodo.accept(this);
        }

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

        body.currentEnv =top;
        top = top.prev;

        return null;
    }

    @Override
    public String visit(Body body, IdVal idVal) {
        top = new Env(top);

        if(top.getInTypeEnviroment(idVal.val)==null){
            top.put(idVal.val,"var", null,"INTEGER");
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
        mainDunDecl.fundecl.accept(this);
        return null;
    }

    @Override
    public String visit(ProgramRoot programRoot) {

        top = new Env(top);

        // chiamo accept su var decl sia di declist1 che declist2
        for(int i=0; i<programRoot.declist1.size();i++){
            Class classe = programRoot.declist1.get(i).getClass();
            if(classe == VarDecl.class){
                VarDecl vardecl =(VarDecl) programRoot.declist1.get(i);
                vardecl.accept(this);
            }
        }

        for(int i=0; i<programRoot.declist2.size();i++) {
            Class classe = programRoot.declist2.get(i).getClass();
            if (classe == VarDecl.class) {
                VarDecl vardecl = (VarDecl) programRoot.declist2.get(i);
                vardecl.accept(this);

            }
        }

        //aggiungo tutti gli id delle funzioni alla tabella dei simboli global sia di declist1 che declist2


        for(int i=0; i<programRoot.declist1.size(); i++){
            ArrayList<String> listaparametri = new ArrayList<String>();
            Class classe = programRoot.declist1.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl fundecl =(FunDecl) programRoot.declist1.get(i);
                if(top.getInThisTable(fundecl.id.val) == null){
                    for(int j=0;j<fundecl.listaPar.size();j++){

                        for(int k = 0; k < fundecl.listaPar.get(j).listaID.size(); k++) {

                            listaparametri.add(0,fundecl.listaPar.get(j).type);
                        }
                    }

                    top.put(fundecl.id.val,"func",listaparametri,fundecl.type);
                }else{
                    try {
                        throw new Exception("Dichiarazione funzione multipla");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for(int i=0; i<programRoot.declist2.size(); i++){
            ArrayList<String> listaparametri = new ArrayList<String>();
            Class classe = programRoot.declist2.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl fundecl =(FunDecl) programRoot.declist2.get(i);
                if(top.getInThisTable(fundecl.id.val) == null) {
                    for (int j = 0; j < fundecl.listaPar.size(); j++) {
                        for (int k = 0; k < fundecl.listaPar.get(j).listaID.size(); k++) {

                            listaparametri.add(0,fundecl.listaPar.get(j).type);
                        }
                    }

                    top.put(fundecl.id.val,"func",listaparametri,fundecl.type);
                }else{
                    try {
                        throw new Exception("Dichiarazione funzione multipla");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //richiamo accept su i fundecl di declist1
        for(int i=0; i<programRoot.declist1.size();i++){
            Class classe = programRoot.declist1.get(i).getClass();
             if(classe == FunDecl.class){
                FunDecl fundecl =(FunDecl) programRoot.declist1.get(i);

                fundecl.accept(this);
            }
        }




        //richiamo accept su i fundecl di declist1
        for(int i=0; i<programRoot.declist2.size();i++){
            Class classe = programRoot.declist2.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) programRoot.declist2.get(i);
                funDecl.accept(this);
            }
        }

        // aggiungo id del main alla tabella global
        programRoot.mainFun.accept(this);
        if(top.getInThisTable(programRoot.mainFun.fundecl.id.val)==null){
            top.put(programRoot.mainFun.fundecl.id.val,"mainFunc",null,programRoot.mainFun.fundecl.type);
        }
        printSymbleTable();
        programRoot.currentEnv=top;


        return null;
    }

    public void printSymbleTable(){
        int num = 0;
        for( Env e = top; e != null; e = e.prev ) {
            System.out.println("Tabella: " + num++);
            System.out.println(e.getTable().toString());
        }
    }
}
