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
            }else node.typeNode = "VOID"; //da capire

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
        return null;
    }

    @Override
    public String visit(VarDecl node) {
        int flag=0;
        //scorriamo uan lista di id nella casisitica dove il type è esplicito
        for(int i =0; i < node.listaID.size();i++ ) {
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
                            flag=1;
                            throw new Exception("Esiste già una funzione con lo stesso nome: " + node.listaID.get(i).id.val);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                try {
                    flag=1;
                    throw new Exception("Dichiarazione multipla");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
            for(int i = 0; i < node.listaID.size(); i++){
                node.listaID.get(i).accept(this);
            }


            for(int i = 0; i < node.listaID.size(); i++){
                if(node.listaID.get(i).typeNode.equals("error"))
                    flag = 1;
            }

            // idinitobblist

        for(int i =0; i < node.idInitObb.size();i++ ) {
            if (top.getInThisTable(node.idInitObb.get(i).id.val) == null) {//controlla se è nella tabella corrente
                RecordSymbolTable recordPrec;
                recordPrec = top.getInTypeEnviroment(node.idInitObb.get(i).id.val);

                if (recordPrec == null) {
                    node.idInitObb.get(i).cost.accept(this);
                    top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode );//inserisce nella tabella al top
                } else {
                    if (recordPrec.kind.equalsIgnoreCase("var")) {
                        node.idInitObb.get(i).cost.accept(this);
                        top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode); //ci assicuriamo che sia una variabile se si tartta di un metodo allora errore es: int a a()
                    } else {
                        try {
                            flag=1;
                            throw new Exception("Esiste già una funzione con lo stesso nome: " + node.idInitObb.get(i).id.val);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                try {
                    flag=1;
                    throw new Exception("Dichiarazione multipla");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < node.idInitObb.size(); i++){
            node.idInitObb.get(i).accept(this);
        }


        for(int i = 0; i < node.idInitObb.size(); i++){
            if(node.idInitObb.get(i).typeNode.equals("error"))
                flag = 1;
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
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo;
            nodo.accept(this);
        } else if(classe == IdVal.class){
            IdVal nodo = (IdVal)node.nodo;
            nodo.accept(this);
        } else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst)node.nodo;
            nodo.accept(this);
        } else if(classe == StringConst.class){
            StringConst nodo = (StringConst)node.nodo;
            nodo.accept(this);
        } else if(classe == CharConst.class) {
            CharConst nodo = (CharConst) node.nodo;
             nodo.accept(this);
        }
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
