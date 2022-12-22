package Visitor;

import nodi.*;

import java.io.*;

public class TreeMaker implements Visitatore{
public String content= "";

    @Override
    public String visit(FuncallNode node) {
        this.content = String.format("<%s>",node.nomeNodo);

        this.content += node.id.accept(this);

        if(node.listaExprNode != null) {
            this.content += String.format("<%s>","ParamOp");
            for(int i = 0; i < node.listaExprNode.size(); i++){
                this.content += node.listaExprNode.get(i).accept(this);
            }
            this.content += String.format("</%s>","ParamOp");
        }

        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    public String visit(Const node){

        this.content = "";

        Class classe = node.nodo.getClass();

        if(classe == BoolConst.class){
            BoolConst nodo = (BoolConst)node.nodo;
            this.content += nodo.accept(this);
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo;
            this.content += nodo.accept(this);
        } else if(classe == IdVal.class){
            IdVal nodo = (IdVal)node.nodo;
            this.content += nodo.accept(this);
        } else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst)node.nodo;
            this.content += nodo.accept(this);
        } else if(classe == StringConst.class){
            StringConst nodo = (StringConst)node.nodo;
            this.content += nodo.accept(this);
        } else if(classe == CharConst.class) {
            CharConst nodo = (CharConst) node.nodo;
            this.content += nodo.accept(this);
        }

            return content;
        }

    @Override
    public String visit(Stat node) {
        this.content = "";

        Class classe = node.nodo.getClass();

        if (classe == IfStat.class) {
            IfStat nodo = (IfStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == ForStat.class){
            ForStat nodo =(ForStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == WriteStat.class){
            WriteStat nodo =(WriteStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == AssignStat.class){
            AssignStat nodo =(AssignStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == FuncallNode.class){
            FuncallNode nodo =(FuncallNode) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == WhileStat.class){
            WhileStat nodo =(WhileStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == ReadStat.class){
            ReadStat nodo =(ReadStat) node.nodo;
            this.content += nodo.accept(this);
        }else if(classe == ExprNode.class){
            ExprNode nodo =(ExprNode) node.nodo;
            this.content += nodo.accept(this);
        }

        return content;
    }


    @Override
    public String visit(Body node) {

        this.content = String.format("<%s>", node.nomeNodo);

        if (node.listaVar.size() >= 1)
            this.content += String.format("<%s>", "varDeclList");

        for (int i = 0; i < node.listaVar.size(); i++) {
            this.content += node.listaVar.get(i).accept(this);
        }

        if (node.listaVar.size() >= 1)
            this.content += String.format("</%s>", "varDeclList");

        if (node.listaStat.get(0)!= null){
            this.content += String.format("<%s>", "statList");

        for (int i = 0; i < node.listaStat.size(); i++) {
            this.content += node.listaStat.get(i).accept(this);
        }

        this.content += String.format("</%s>", "statList");
    }
        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }


    @Override
    public String visit(ExprNode node) {
        int flag = 0;
        this.content = "";

        Class classe = node.nodo1.getClass();

        if(classe == BoolConst.class){
            BoolConst nodo = (BoolConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == IdVal.class){
            IdVal nodo = (IdVal)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == StringConst.class){
            StringConst nodo = (StringConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == CharConst.class){
            CharConst nodo = (CharConst) node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == FuncallNode.class){
            FuncallNode nodo = (FuncallNode) node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == ExprNode.class){
            flag = 1;
            this.content += String.format("<%s>",node.nomeNodo);
            ExprNode nodo = (ExprNode)node.nodo1;
            this.content += nodo.accept(this);

        }

        if(node.nodo2 != null){
            classe = node.nodo2.getClass();

            if(classe == BoolConst.class){
                BoolConst nodo = (BoolConst)node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == RealConst.class){
                RealConst nodo = (RealConst)node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == IdVal.class){
                IdVal nodo = (IdVal)node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == IntegerConst.class){
                IntegerConst nodo = (IntegerConst)node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == StringConst.class){
                StringConst nodo = (StringConst)node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == CharConst.class){
                CharConst nodo = (CharConst) node.nodo2;
                this.content += nodo.accept(this);
            }else if(classe == FuncallNode.class){
                FuncallNode nodo = (FuncallNode) node.nodo2;
                this.content += nodo.accept(this);
            } else if(classe == ExprNode.class){
                flag = 1;

                ExprNode nodo = (ExprNode)node.nodo2;
                this.content += nodo.accept(this);

            }
        }
       if(flag == 1){
            this.content += String.format("</%s>",node.nomeNodo);
        }

        return content;
    }

    public String visit(BoolConst node) {

        if(node.val == true)
            content = "(TRUE)";
        else
            content = "(FALSE)";

        return content;
    }
    public String visit(IntegerConst node){
        content =  "(INTEGER_CONST: "+node.val+")";
        return content;
    }

    /*public String visit(Node node){
        String content;
        content =
        return content;
    }*/

    public String visit(RealConst node){
        this.content = "";
        this.content += "(REAL_CONST: "+node.val+")";
        return content;

    }

    public String visit(StringConst node){
        this.content= "";
        this.content += "(STRING_CONST: "+ node.val+")";
        return content;
    }

    public String visit(CharConst node){
        this.content = "";
        this.content += "(CHAR_CONST: "+ node.val +")";
        return content;
    }
    public String visit(IdVal node){
        this.content= "";
        this.content += "(ID: "+ node.val + ")";
        return content;
    }

    public String visit(AssignStat node){
        this.content = String.format("<%s>",node.nomeNodo);

        this.content += String.format("<%s>", "IdList");
        for(int i = 0; i < node.idList.size(); i++){
            this.content += node.idList.get(i).accept(this);
        }
        this.content += String.format("</%s>", "IdList");

        for(int i = 0; i < node.exprList.size(); i++){
            this.content += node.exprList.get(i).accept(this);
        }

        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    public String visit(WriteStat node){
        this.content = String.format("<%s>",node.nomeNodo);
        for(int i = 0; i < node.listaExpr.size(); i++){
            this.content += node.listaExpr.get(i).accept(this);
        }
        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

   public String visit(ReadStat node){
        this.content = String.format("<%s>",node.nomeNodo);
        this.content += String.format("<%s>","IdListOp");

        for(int i = 0; i < node.idList.size(); i++){
           this.content += node.idList.get(i).accept(this);
        }
       this.content += String.format("</%s>","IdListOp");
        if(node.val !=null)
        this.content += String.format((String) node.val.accept(this));

       this.content += String.format("</%s>",node.nomeNodo);
       return content;
   }

    @Override
    public String visit(ParDecl node) {
        this.content = String.format("<%s>", node.nomeNodo);

        this.content += "(" + node.type + ")";

        for(int i = 0; i < node.listaID.size(); i++){
            this.content += node.listaID.get(i).accept(this);
        }

        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    public String visit(IDInit node) {
        this.content = "";

        this.content += node.id.accept(this);

        if(node.expr != null)
            this.content += node.expr.accept(this);


        return content;
    }
    public String visit(IDInitObb node) {
        this.content = "";

        this.content += node.id.accept(this);


        if(node.cost != null)
            this.content += node.cost.accept(this);

        return content;
    }
    public String visit(VarDecl node) {
        this.content = String.format("<%s>",node.nomeNodo);

        if(node.type != null) {
            this.content += "(" + node.type + ")";

            if (node.listaID.size() >= 1)
                this.content += String.format("<%s>", "IdListInitOp");

            for (int i = 0; i < node.listaID.size(); i++) {
                this.content += node.listaID.get(i).accept(this);
            }

            if (node.listaID.size() >= 1)
                this.content += String.format("</%s>", "IdListInitOp");
        }else{
            if (node.idInitObb.size() >= 1)
                this.content += String.format("<%s>", "IdListInitObbOp");

            for (int i = 0; i < node.idInitObb.size(); i++) {
                this.content += node.idInitObb.get(i).accept(this);
            }

            if (node.idInitObb.size() >= 1)
                this.content += String.format("</%s>", "IdListInitObbOp");
        }

        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    public String visit(IfStat node){
        this.content = String.format("<%s>",node.nomenodo);


        this.content += node.nodeEx.accept(this);
        this.content += node.body.accept(this);

        this.content += String.format("</%s>",node.nomenodo);
        return content;

    }

    @Override
    public String visit(WhileStat node) {
        this.content = String.format("<%s>",node.nomenodo);


        this.content += node.nodeEx.accept(this);
        this.content += node.body.accept(this);

        this.content += String.format("</%s>",node.nomenodo);
        return content;

    }
    public String visit(ForStat node) {
        this.content = String.format("<%s>",node.nomenodo);


        this.content += node.id.accept(this);
        this.content += node.val1.accept(this);
        this.content += node.val2.accept(this);
        this.content += node.body.accept(this);

        this.content += String.format("</%s>",node.nomenodo);
        return content;

    }

    @Override
    public String visit(FunDecl node) {
        this.content = String.format("<%s>",node.nomeNodo);
        this.content += node.id.accept(this);

        if(node.listaPar.size()>=1) {
            for (int i = 0; i < node.listaPar.size(); i++) {
                this.content += node.listaPar.get(i).accept(this);
            }
        }
        this.content+= node.type;
        this.content+= node.body.accept(this);
        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    @Override
    public String visit(MainFunDecl node) {
        this.content = String.format("<%s>",node.nomeNodo);
        this.content+= node.fundecl.accept(this);
        this.content += String.format("</%s>",node.nomeNodo);
        return content;
    }

    @Override
    public String visit(ProgramRoot node) {


        this.content = String.format("<%s>",node.nomeRoot);

        if(node.declist1.size() >= 1) {
            this.content += String.format("<%s>", "DeclList");

            for (int i = 0; i < node.declist1.size(); i++) {
                Class classe = node.declist1.get(i).getClass();

                if (classe == VarDecl.class) {
                    VarDecl nodo = (VarDecl) node.declist1.get(i);
                    this.content += nodo.accept(this);
                }

                if (classe == FunDecl.class) {
                    FunDecl nodo = (FunDecl) node.declist1.get(i);
                    this.content += nodo.accept(this);
                }
            }
            this.content += String.format("</%s>", "DeclList");
        }



        this.content+= node.mainFun.accept(this);


        if(node.declist2.size() >= 1) {
            this.content += String.format("<%s>", "DeclList");

            for (int i = 0; i < node.declist2.size(); i++) {
                Class classe = node.declist2.get(i).getClass();

                if (classe == VarDecl.class) {
                    VarDecl nodo = (VarDecl) node.declist2.get(i);
                    this.content += nodo.accept(this);
                }

                if (classe == FunDecl.class) {
                    FunDecl nodo = (FunDecl) node.declist2.get(i);
                    this.content += nodo.accept(this);
                }
            }
            this.content += String.format("</%s>", "DeclList");
        }



                this.content += String.format("</%s>",node.nomeRoot);
                return content;
    }

    public void saveFileXML(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("file.xml"), "utf-8"));
            writer.write(this.content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Errore nella scrittura del file");
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("Errore durante la chiusura del file");
            }
        }
    }
}
