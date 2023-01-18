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
                throw new Exception("identificatore non dihiarato: "+node.val);
            }catch (Exception e){
                e.printStackTrace();
            }
            node.typeNode = "error";
        }else{
            if(rs.kind.equalsIgnoreCase("var")) {
                node.typeNode = rs.typeRitorno;
            }else node.typeNode = "NOTYPE";

        }

        return null;
    }

    @Override
    public String visit(FuncallNode node) {
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
                if (node.exprList.get(i).nomeNodo.equalsIgnoreCase("id")) {
                    IdVal idV = (IdVal) node.exprList.get(i).nodo1;
                    if (idV.typeNode.equals("error")) {
                        flag = 1;
                    }

                    typeExprFinale.add(node.exprList.get(i).typeNode);

                }
            }
        }

        if(node.idList.size() == typeExprFinale.size() && flag == 0){ //controlliamo se la lista di variabili è della stessa size della lista dei valori che assegnamo
            node.typeNode = "VOID";
            for(int i = 0; i < node.idList.size(); i++){ //controlliamo se i tipi che assegnamo coincidono
                if(!((node.idList.get(i).typeNode).equals(typeExprFinale.get(i)))) {
                    node.typeNode = "error";
                    try {
                        throw new Exception("Assegnazione non consentita " + node.nomeNodo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }else{
            node.typeNode = "error";
            try {
                throw new Exception("Assegnazione non consentita " + node.nomeNodo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

        node.id.accept(this);

        if(node.expr != null){
            node.expr.accept(this);
            if(node.id.typeNode.equals(node.expr.typeNode)){
                node.typeNode = "VOID";
            }else {
                node.typeNode ="error";
                try {
                    throw new Exception("errore inizializzazione");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else{
            node.typeNode = "VOID";
        }

        return null;
    }

    @Override
    public String visit(IDInitObb node) {
        node.id.accept(this);
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
                node.typeNode = "VOID";
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
            node.tipoRitorno = nodo.tipoRitorno;
        }else if(classe == ForStat.class){
            ForStat nodo =(ForStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
            node.tipoRitorno = nodo.tipoRitorno;
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
            node.tipoRitorno = nodo.tipoRitorno;
        }else if(classe == ReadStat.class){
            ReadStat nodo =(ReadStat) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }else if(classe == ExprNode.class){
            ExprNode nodo =(ExprNode) node.nodo;
            nodo.accept(this);
            node.typeNode = nodo.typeNode;
        }


        return null;
    }

    @Override
    public String visit(Body node) {
        top= node.currentEnv;

        if(node.listaVar != null) {
        for (int i = 0; i < node.listaVar.size(); i++) {
            node.listaVar.get(i).accept(this);
        }
}

        if(node.listaStat != null) {
            for (int i = 0; i < node.listaStat.size(); i++) {

                node.listaStat.get(i).accept(this);
                if(node.listaStat.get(i).nameStat.equals("return") ){

                    node.tipoRitorno = node.listaStat.get(i).tipoRitorno;
                    top= top.prev;
                    return null;
                }else if(node.listaStat.get(i).nameStat.equals("returnVoid")){
                    node.tipoRitorno = "void";
                    top= top.prev;
                    return null;
                }
            }
        }


        top= top.prev;
        return null;
    }

    @Override
    public String visit(Body body, IdVal idVal) {
        return null;
    }

    @Override
    public String visit(Body body, ArrayList<ParDecl> parDecls) {
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
    //QUIII
    public String visit(FunDecl node) {
        int flag =0;

        node.id.accept(this);
        if(node.id.typeNode.equalsIgnoreCase("notype")){
            if(node.listaPar != null){
                for(int i=0; i< node.listaPar.size(); i++){
                    node.listaPar.get(i).accept(this);
                    if(node.listaPar.get(i).typeNode.equalsIgnoreCase("error")){
                        flag =1;
                    }
                }
            }
            node.body.accept(this);
            if(node.body.typeNode.equalsIgnoreCase("error"))
                flag=1;

        }else{
            node.typeNode ="error";
        }


        return null;
    }

    @Override
    public String visit(MainFunDecl node) {
        return null;
    }

    @Override
    public String visit(ProgramRoot node) {
        top = node.currentEnv; //tabella gloal

        for(int i =0; i<node.declist1.size();i++){

            Class classe = node.declist1.get(i).getClass();
            if(classe == VarDecl.class){
                VarDecl vardecl =(VarDecl) node.declist1.get(i);
                vardecl.accept(this);
            }
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) node.declist1.get(i);
                funDecl.accept(this);
            }

        }

        for(int i=0; i<node.declist2.size();i++) {
            Class classe = node.declist2.get(i).getClass();
            if (classe == VarDecl.class) {
                VarDecl vardecl = (VarDecl) node.declist2.get(i);
                vardecl.accept(this);

            }
            if(classe == FunDecl.class){
                FunDecl funDecl =(FunDecl) node.declist2.get(i);
                funDecl.accept(this);
            }
        }

        node.mainFun.accept(this);

        printSymbleTable();


        return null;
    }
    public void printSymbleTable(){
        int num = 0;
        for( Env e = top; e != null; e = e.prev ) {
            System.out.println("Tabella: " + num++);
            System.out.println(e.getTable().toString());
        }
    }
    @Override
    public Object visit(ElseStat elseStat) {
        return null;
    }
}
