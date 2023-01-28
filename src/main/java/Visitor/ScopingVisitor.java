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
                if(parDecl.nomeNodo.equalsIgnoreCase("ParDeclOutOP")) {
                    top.put(parDecl.listaID.get(i).val, "varOUT", null, type,true);
                    parDecl.listaID.get(i).isOut=true;
                    parDecl.isOut=true;
                }
                else
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
        /*
         * andiamo a controllare che l'id non sia nella tabella corrente
         * se c'è allora dichiarazione multipla
         * se non c'è facciamo
         * il controllo nel type environment e andiamo a vedere se non è già dichiarata una funzione con lo stesso nome
         * se è una variabile va be ne perchè la portiamo anche in questo scope(most closed nested)
        */


        //scorriamo uan lista di id nella casisitica dove il type è esplicito
        if(node.listaID !=null) {
            for (int i = 0; i < node.listaID.size(); i++) {
                if (top.getInThisTable(node.listaID.get(i).id.val) == null) {//controlla se è nella tabella corrente
                    RecordSymbolTable recordPrec;
                    recordPrec = top.getInTypeEnviroment(node.listaID.get(i).id.val);
                    if (recordPrec == null) {
                        top.put(node.listaID.get(i).id.val, "var", null, node.type);//inserisce nella tabella al top
                    } else {
                        if (recordPrec.kind.equalsIgnoreCase("var")  ) {
                            top.put(node.listaID.get(i).id.val, "var", null, node.type); //ci assicuriamo che sia una variabile se si tartta di un metodo allora errore es: int a a() (most closely nested)
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

        if(node.nodo != null) {
            Class classe = node.nodo.getClass();

            if (classe == IfStat.class) {
                IfStat nodo = (IfStat) node.nodo;
                nodo.accept(this);
            } else if (classe == ForStat.class) {
                ForStat nodo = (ForStat) node.nodo;
                nodo.accept(this);
            } else if (classe == WriteStat.class) {
                WriteStat nodo = (WriteStat) node.nodo;
                nodo.accept(this);
            } else if (classe == AssignStat.class) {
                AssignStat nodo = (AssignStat) node.nodo;
                nodo.accept(this);
            } else if (classe == FuncallNode.class) {
                FuncallNode nodo = (FuncallNode) node.nodo;
                nodo.accept(this);
            } else if (classe == WhileStat.class) {
                WhileStat nodo = (WhileStat) node.nodo;
                nodo.accept(this);
            } else if (classe == ReadStat.class) {
                ReadStat nodo = (ReadStat) node.nodo;
                nodo.accept(this);
            } else if (classe == ExprNode.class) {
                ExprNode nodo = (ExprNode) node.nodo;
                nodo.accept(this);
            }
        }

        return null;
    }

    @Override
    public String visit(Body body) {
        top = new Env(top);

        if(body.idfor != null)
            top.put(body.idfor, "var", null,"INTEGER");

        for(int i=0; i< body.listaVar.size(); i++){
            body.listaVar.get(i).accept(this);
        }
        for(int i=0; i< body.listaStat.size(); i++){
            if(body.listaStat.get(i)!=null) {
                body.listaStat.get(i).accept(this);
            }
        }

        body.currentEnv =top;
        top = top.prev;

        return null;
    }



    @Override
    public String visit(Body body, ArrayList<ParDecl> parDecls) {

        top = new Env(top);

        for(int i=0; i<parDecls.size();i++){
            parDecls.get(i).accept(this);
        }

        for(int i=0; i< body.listaVar.size(); i++){
            body.listaVar.get(i).accept(this);
        }

        for (int i = 0; i < body.listaStat.size(); i++) {
            if(body.listaStat.get(i)!=null) {
                body.listaStat.get(i).accept(this);
            }
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

        forStat.body.idfor = forStat.id.val;
        forStat.body.accept(this);

        return null;
    }

    @Override
    public String visit(FunDecl funDecl) {

        funDecl.body.accept(this,funDecl.listaPar);
        funDecl.id.accept(this);

        return null;
    }

    @Override
    public String visit(MainFunDecl mainFunDecl) {
        mainFunDecl.fundecl.accept(this);
        return null;
    }

    @Override
    public String visit(ProgramRoot programRoot) {
        /*in questo metodo chiamiamo prima tutti i vardecl affinchè siano visibili alle funzioni
        * successivamente andiamo ad inserire nella tabella global i record relativi alle funzioni sia di declist 1 che declist 2 cosi che
        * ogni funzione sia poi visibile ad ogni altra
        * dopo queste operazioni preleminari posso chiamare gli accept dei fundecl i quali andranno a creare lo scope relativo
        *
        * Non è possibile dichiarare una funzione che si chiama main, nenache il main stesso!
        * */

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
            ArrayList<String> listaparametri1 = new ArrayList<String>();
            Class classe = programRoot.declist1.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl fundecl =(FunDecl) programRoot.declist1.get(i);
                if(top.getInThisTable(fundecl.id.val) == null && !fundecl.id.val.equalsIgnoreCase("main") ){
                    for(int j=0;j<fundecl.listaPar.size();j++){

                        for(int k = 0; k < fundecl.listaPar.get(j).listaID.size(); k++) {

                            listaparametri1.add(0,fundecl.listaPar.get(j).type);
                        }
                    }

                    top.put(fundecl.id.val, "func", listaparametri1, fundecl.type);

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
            ArrayList<String> listaparametri2 = new ArrayList<String>();
            Class classe = programRoot.declist2.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl fundecl =(FunDecl) programRoot.declist2.get(i);

                if(top.getInThisTable(fundecl.id.val) == null&& !fundecl.id.val.equalsIgnoreCase("main")  ) {

                    for (int j = 0; j < fundecl.listaPar.size(); j++) {
                        for (int k = 0; k < fundecl.listaPar.get(j).listaID.size(); k++) {

                            listaparametri2.add(0,fundecl.listaPar.get(j).type);
                        }

                    }

                    top.put(fundecl.id.val, "func", listaparametri2, fundecl.type);


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
                //sovrascrivo recordsymboltable
                 for(int j=0;j<fundecl.listaPar.size();j++){

                     top.getInThisTable(fundecl.id.val).parDecls=fundecl.listaPar;
//                     if(fundecl.listaPar.get(j).isOut) {
//                         top.getInThisTable(fundecl.id.val).isout=true;
//
//
//                     }

                 }
            }
        }




        //richiamo accept su i fundecl di declist2
        for(int i=0; i<programRoot.declist2.size();i++){
            Class classe = programRoot.declist2.get(i).getClass();
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) programRoot.declist2.get(i);
                funDecl.accept(this);
                for(int j=0;j<funDecl.listaPar.size();j++){

                    top.getInThisTable(funDecl.id.val).parDecls=funDecl.listaPar;
//                    if(funDecl.listaPar.get(j).isOut) {
//                        top.getInThisTable(funDecl.id.val).isout=true;
//
//
//                    }

                }
            }
        }

        // aggiungo id del main alla tabella global
        programRoot.mainFun.accept(this);
        ArrayList<String> listaparametri3 = new ArrayList<String>();
        for (int j = 0; j < programRoot.mainFun.fundecl.listaPar.size(); j++) {
            for (int k = 0; k < programRoot.mainFun.fundecl.listaPar.get(j).listaID.size(); k++) {

                listaparametri3.add(0,programRoot.mainFun.fundecl.listaPar.get(j).type);
            }

        }

        if(top.getInThisTable(programRoot.mainFun.fundecl.id.val)==null&& !programRoot.mainFun.fundecl.id.val.equalsIgnoreCase("main")  ){
            top.put(programRoot.mainFun.fundecl.id.val,"mainFunc",listaparametri3,programRoot.mainFun.fundecl.type);
            top.getInThisTable(programRoot.mainFun.fundecl.id.val).parDecls=programRoot.mainFun.fundecl.listaPar;
        }else{
            try {
                throw new Exception("Identificativo main già dichiarato");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



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
