package Visitor;

import nodi.*;

import java.util.ArrayList;

public class AnalisiSemantica implements Visitatore{

    Env top = null; //tabella dei simboli corrente
    ArrayList<Env> stack = new ArrayList<Env>(); //stack per tenere conto di tutte le tabelle dei simboli
    OpTypeTable opTypeTable = new OpTypeTable();
    @Override
    public String visit(ExprNode node) {
        Class classe = node.nodo1.getClass();
        String typeFirstOperand = "";

        if(classe == BoolConst.class){
            BoolConst nodo = (BoolConst)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == IdVal.class){
            IdVal nodo = (IdVal)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == StringConst.class){
            StringConst nodo = (StringConst)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == CharConst.class){
            CharConst nodo = (CharConst)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == FuncallNode.class){
            FuncallNode nodo = (FuncallNode)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = nodo.typeNode;
            } else {
                typeFirstOperand = nodo.typeNode;
            }
        } else if(classe == ExprNode.class){
            ExprNode nodo = (ExprNode)node.nodo1;
            nodo.accept(this);
            if(node.nodo2 == null) {
                node.typeNode = opTypeTable.searchOp(node.nomeNodo, nodo.typeNode, ""); //controllo operazioni unarie
            }
            typeFirstOperand = nodo.typeNode;

        }

        String tipoNodo2 = "";
        if(node.nodo2 != null){
            classe = node.nodo2.getClass();

            if(classe == ExprNode.class){
                ExprNode nodo = (ExprNode)node.nodo2;
                nodo.accept(this);
                tipoNodo2 = nodo.typeNode;
                node.typeNode = opTypeTable.searchOp(node.nomeNodo, typeFirstOperand, tipoNodo2); //controllo operazioni a due operatori
            }
        }

        if(node.typeNode.equals("error")) {
            try {
                if(node.nodo2 != null)
                    throw new Exception("Operazione binaria non consentita " + node.nomeNodo + " tra: " + typeFirstOperand + " e " + tipoNodo2);
                else
                    throw new Exception("Operazione unaria non consentita " + node.nomeNodo + " su: " + typeFirstOperand);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
    public String visit(IdVal node) {

        RecordSymbolTable rs = top.getInTypeEnviroment(node.val);
        if(rs == null){
            try{
                throw new Exception("identificatore non dihiarato: "+node.val+printSymbleTable());

            }catch (Exception e){

                e.printStackTrace();
            }
            node.typeNode = "error";
        }else{
            if(rs.kind.equalsIgnoreCase("var") || rs.kind.equalsIgnoreCase("varOUT") ) {
                node.typeNode = rs.typeRitorno;
            }else node.typeNode = "NOTYPE";

        }

        return null;
    }

    @Override
    public String visit(FuncallNode node) {
        int flag=0;
        node.id.accept(this);

        if(node.listaExprNode !=null){
            for(int i =0 ;i< node.listaExprNode.size();i++){
                node.listaExprNode.get(i).accept(this);
            }
        }

        int sizeList = 0;

        RecordSymbolTable recordSymbolTable = top.getInTypeEnviroment(node.id.val);
        if (recordSymbolTable != null && (recordSymbolTable.kind.equals("func") || (recordSymbolTable.kind.equals("mainFunc")) )){
            if(node.listaExprNode != null)
                sizeList = node.listaExprNode.size();

            if(sizeList == recordSymbolTable.typeParametri.size()) {//da rivedere

                for (int i = 0; i < sizeList; i++) { //controllo che i parametri passati alla proc siano del tipo corretto
                    if (!(node.listaExprNode.get(i).typeNode.equalsIgnoreCase(recordSymbolTable.typeParametri.get(i)))) {
                        flag = 1;
                        node.typeNode = "error";
                        try {
                            throw new Exception("Tipo di parametri errato" + node.nomeNodo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                if(flag ==0) {
                    if(recordSymbolTable.typeRitorno.equalsIgnoreCase("void")) {
                        node.typeNode = "notype";
                    }else{
                        node.typeNode = recordSymbolTable.typeRitorno;
                    }

                }
            } else {
                node.typeNode = "error";
                try {
                    throw new Exception("Numero di parametri errato" + node.nomeNodo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            node.typeNode = "error";
            try {
                throw new Exception("Funzione non esistente " + node.id.val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }






        return null;
    }

    @Override
    public String visit(AssignStat node) {


        for(int i = 0; i < node.idList.size(); i++){
            node.idList.get(i).accept(this);
        }

        for(int i = 0; i < node.exprList.size(); i++){
            node.exprList.get(i).accept(this);
        }

        ArrayList<String> typeExprFinale = new ArrayList<>();
        int flag = 0;
        for(int i = 0; i < node.exprList.size(); i++) { //aggiungo a typeExprFinale tutti i tipi che andiamo ad assegnare
            if (node.exprList.get(i).nomeNodo.equals("FuncallOp")) {
                FuncallNode proc = (FuncallNode) node.exprList.get(i).nodo1;
                if (proc.typeNode.equals("error")) {
                    flag = 1;
                }
                RecordSymbolTable record = top.getInTypeEnviroment(proc.id.val);
                typeExprFinale.add(record.typeRitorno);
            } else {


                    typeExprFinale.add(node.exprList.get(i).typeNode);

                }
            }


        if(node.idList.size() == typeExprFinale.size() && flag == 0) { //controlliamo se la lista di variabili è della stessa size della lista dei valori che assegnamo

            node.typeNode = "notype";
                for (int i = 0; i < node.idList.size(); i++) { //controlliamo se i tipi che assegnamo coincidono
                    RecordSymbolTable record = top.getInTypeEnviroment(node.idList.get(i).val);

                    if (!((record.typeRitorno).equals(typeExprFinale.get(i)))) {
                       // if (!(record.typeRitorno.equals("REAL") && typeExprFinale.get(i).equals("INTEGER"))) {
                            node.typeNode = "error";
                            try {
                                throw new Exception("Assegnazione non consentita " + node.nomeNodo + node.idList.get(i).val + node.exprList.get(i).nodo1.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                      //  }
                    }
                }
            }else{
                node.typeNode = "error";
                try {
                    throw new Exception("Assegnazione non consentita " + node.nomeNodo + node.idList.get(0).val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        return null;
    }

    @Override
    public String visit(WriteStat node) {
        int flag =0;

        for(int i=0; i<node.listaExpr.size(); i++) {
            node.listaExpr.get(i).accept(this);

        }

        for(int i=0; i<node.listaExpr.size(); i++){
           if( node.listaExpr.get(i).typeNode.equalsIgnoreCase("error"))
            flag =1;
        }

        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in: "+node.nomeNodo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String visit(ReadStat node) {
        for(int i = 0; i < node.idList.size(); i++){
            node.idList.get(i).accept(this);
        }

        int flag=0;
        for(int i = 0; i < node.idList.size(); i++){
            if(node.idList.get(i).typeNode.equalsIgnoreCase("error"))
                flag =1;
        }
        if(node.val != null){
            node.val.accept(this);
        }


        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in read");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String visit(ParDecl node) {

        for(int i = 0; i<node.listaID.size(); i++ ){

            node.listaID.get(i).accept(this);
        }
        for(int i = 0; i<node.listaID.size(); i++ ){
          if( node.listaID.get(i).typeNode.equals("error")){
              node.typeNode = "error";
            }else{
              node.typeNode="notype";
          }
        }



        return null;
    }

    @Override
    public String visit(IDInit node) {

        node.id.accept(this);

        if(node.expr != null){
            node.expr.accept(this);
            if(node.id.typeNode.equals(node.expr.typeNode)){
                node.typeNode = "notype";
            }else {
                node.typeNode ="error";
                try {
                    throw new Exception("errore inizializzazione");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else{
            node.typeNode = "notype";
        }

        return null;
    }

    @Override
    public String visit(IDInitObb node) {
        node.id.accept(this);
        node.cost.accept(this);
        node.typeNode = node.id.typeNode;


        return null;
    }

    @Override
    public String visit(VarDecl node) {


        int flag=0;
if(node.listaID !=null) {
    for (int i = 0; i < node.listaID.size(); i++) {

        node.listaID.get(i).accept(this);
    }


    for (int i = 0; i < node.listaID.size(); i++) {
        if (node.listaID.get(i).typeNode.equals("error"))
            flag = 1;
    }
}
            // idinitobblist
if(node.idInitObb != null) {
    for (int i = 0; i < node.idInitObb.size(); i++) {
        node.idInitObb.get(i).accept(this);
    }


    for (int i = 0; i < node.idInitObb.size(); i++) {

        if (node.idInitObb.get(i).typeNode.equals("error"))
            flag = 1;

        }


    }


            if (flag == 0) {
                node.typeNode = "notype";
            } else {
                node.typeNode = "error";
                try {
                    throw new Exception("Errore in var decl");
                } catch (Exception e) {
                    e.printStackTrace();
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
            node.typeNode = nodo.typeNode;

        }else if(classe == ForStat.class){
            ForStat nodo =(ForStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;

        }else if(classe == WriteStat.class){
            WriteStat nodo =(WriteStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;

        }else if(classe == AssignStat.class){
            AssignStat nodo =(AssignStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }else if(classe == FuncallNode.class){
            FuncallNode nodo =(FuncallNode) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }else if(classe == WhileStat.class){
            WhileStat nodo =(WhileStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;

        }else if(classe == ReadStat.class){
            ReadStat nodo =(ReadStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }else if(classe == ExprNode.class){
            ExprNode nodo =(ExprNode) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
            node.tipoRitorno = nodo.typeNode;
        }


        return null;
    }

    @Override
    public String visit(Body node) {
        top= node.currentEnv;
        int flag =0;

        if(node.listaVar != null) {
        for (int i = 0; i < node.listaVar.size(); i++) {
            node.listaVar.get(i).accept(this);
        }
}


            for (int i = 0; i < node.listaStat.size(); i++) {
                if(node.listaStat.get(i)!=null) {
                    node.listaStat.get(i).accept(this);
                }

            }

            printSymbleTable2();
            top= top.prev;

            for (int i = 0; i < node.listaVar.size(); i++) {
                if(node.listaVar.get(i).typeNode.equals("error"))
                    flag=1;
            }
            if(node.listaStat.size()!=0){
                for (int i = 0; i < node.listaStat.size(); i++) {
                    if(node.listaStat.get(i)!= null && node.listaStat.get(i).typeNode != null  ) {
                        if (node.listaStat.get(i).typeNode.equals("error"))
                            flag = 1;

                    }
                }
            }


        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in : " + node.nomeNodo );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

            for (int i = 0; i < node.listaStat.size(); i++) {


                if( node.listaStat.get(i)!= null &&  node.listaStat.get(i).nameStat.equals("return") ){

                    node.tipoRitorno = node.listaStat.get(i).tipoRitorno;
                    return null;
                }else if( node.listaStat.get(i)!= null && node.listaStat.get(i).nameStat.equals("returnVoid")){
                    node.tipoRitorno = "void";
                    return null;
                }

                    node.tipoRitorno = "void";


            }



        return null;
    }



    @Override
    public String visit(Body body, ArrayList<ParDecl> parDecls) {
        return null;
    }

    @Override
    public String visit(IfStat node) {
        int flag = 0;

        node.nodeEx.accept(this);
        node.body.accept(this);

        if(node.els != null) {
            node.els.accept(this);

            if(node.els.typeNode.equals("error"))
                flag=1;
        }



        if(!node.nodeEx.typeNode.equals("BOOL") ||  (node.body.typeNode.equals("error")) ){

            flag=1;
        }
        if(flag == 0){
            node.typeNode = "notype";
        }else{
            node.typeNode = "error";
        }



        return null;
    }

    @Override
    public String visit(WhileStat node) {

        int flag = 0;

       // printSymbleTable2();
        node.nodeEx.accept(this);
        node.body.accept(this);





        if(!node.nodeEx.typeNode.equals("BOOL") ||  (node.body.typeNode.equals("error")) ){

            node.typeNode = "error";
            try {
                throw new Exception("eccezione nodo" + node.nomenodo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            node.typeNode = "notype";

        }



        return null;
    }

    @Override
    public String visit(ForStat node) {
        int flag=0;

        node.id.accept(this);
        node.val1.accept(this);
        node.val2.accept(this);
        node.body.accept(this);

        if(node.id.typeNode.equalsIgnoreCase("error"))
            flag=1;


        if(node.body.typeNode.equalsIgnoreCase("error"))
            flag=1;

        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in for");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return null;
    }

    @Override
    //QUIII
    public String visit(FunDecl node) {
        int flag =0;
        top = node.body.currentEnv;


        node.id.accept(this);



        if(node.id.typeNode.equalsIgnoreCase("NOTYPE")){
            if(node.listaPar != null){
                for(int i=0; i< node.listaPar.size(); i++){

                        node.listaPar.get(i).accept(this);

                        if (node.listaPar.get(i).typeNode.equalsIgnoreCase("error")) {
                            flag = 1;

                    }
                }

            }

            node.body.accept(this);
            if(node.body.typeNode.equalsIgnoreCase("error"))
                flag=1;


        }else{
            flag =1;
        }


        if(!node.type.equalsIgnoreCase(node.body.tipoRitorno))
            flag =1;


        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in fundecls: " + node.id.val + node.listaPar.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // top = top.prev;
        return null;
    }

    @Override
    public String visit(MainFunDecl node) {
        node.fundecl.accept(this);
        node.typeNode=node.fundecl.typeNode;
        return null;
    }


    @Override
    public String visit(ProgramRoot node) {
        int flag =0;
        top = node.currentEnv; //tabella gloal

        for(int i =0; i<node.declist1.size();i++){

            Class classe = node.declist1.get(i).getClass();
            if(classe == VarDecl.class){
                VarDecl vardecl =(VarDecl) node.declist1.get(i);
                vardecl.accept(this);
                if(vardecl.typeNode.equals("error"))
                    flag=1;
            }
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) node.declist1.get(i);
                funDecl.accept(this);
                if(funDecl.typeNode.equals("error"))
                    flag=1;
            }

        }

        for(int i=0; i<node.declist2.size();i++) {
            Class classe = node.declist2.get(i).getClass();
            if (classe == VarDecl.class) {
                VarDecl vardecl = (VarDecl) node.declist2.get(i);
                vardecl.accept(this);
                if(vardecl.typeNode.equals("error"))
                    flag=1;

            }
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) node.declist2.get(i);
                funDecl.accept(this);
                if(funDecl.typeNode.equals("error"))
                    flag=1;
            }
        }


        node.mainFun.accept(this);
        if(node.mainFun.typeNode.equals("error"))
            flag=1;


        if (flag == 0) {
            node.typeNode = "notype";
        } else {
            node.typeNode = "error";
            try {
                throw new Exception("Errore in root");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }
    public String  printSymbleTable(){
        int num = 0;
        String tabella = "";
        for( Env e = top; e != null; e = e.prev ) {


            System.out.println(e.getTable().toString());
            tabella += e.getTable().toString();
        }
        return tabella;
    }

    public void printSymbleTable2(){
        int num = 0;
        for( Env e = top; e != null; e = e.prev ) {
            System.out.println("Tabella: " + num++);
            System.out.println(e.getTable().toString());
        }
    }


    @Override
    public Object visit(ElseStat node) {
        node.body.accept(this);
        if(node.body.typeNode.equals("notype")){
            node.typeNode = "notype";
        }else{
            node.typeNode = "error";
        }

        return null;
    }
}
