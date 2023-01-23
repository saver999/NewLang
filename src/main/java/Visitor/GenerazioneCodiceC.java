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
        this.content="";
        this.content += node.id.accept(this);

        if(node.expr != null){
            this.content += "=";
            this.content += node.expr.accept(this);
        }


        return content;
    }

    @Override
    public String visit(IDInitObb node) {
        this.content="";
        this.content += node.id.accept(this);
        this.content += node.cost.accept(this);


        return content;
    }

    @Override
    public String visit(VarDecl node) {
        this.content = "";

        if(node.nomeNodo.equalsIgnoreCase("VarDeclObb")){
            for(int i=0; i<node.idInitObb.size(); i++){


                switch (node.idInitObb.get(i).cost.typeNode){
                    case "STRING":
                        this.content += "char *";
                        break;

                    case "BOOL":
                        this.content += node.type.toLowerCase();
                        break;
                    case "REAL":
                        this.content += "float";
                        break;
                    case "INTEGER":
                        this.content += "int";
                        break;
                    case "CHAR":
                        this.content += node.type.toLowerCase();
                        break;

                }

                this.content += node.idInitObb.get(i).accept(this);

                if( i != node.idInitObb.size()-1)
                    this.content += ",";



            }
            this.content += ";\n";

        }else{
            switch (node.type){
                case "STRING":
                    this.content += "char *";
                    break;

                case "BOOL":
                    this.content += node.type.toLowerCase();
                    break;
                case "REAL":
                    this.content += "float";
                    break;
                case "INTEGER":
                    this.content += "int";
                    break;
                case "CHAR":
                    this.content += node.type.toLowerCase();
                    break;

            }
            for(int i=0; i<node.listaID.size();i++) {

                this.content += node.listaID.get(i).accept(this);

                if (i != node.listaID.size() - 1)
                    this.content += ",";

            }

            this.content += ";\n";


        }

        return content;
    }

    @Override
    public String visit(Const node) {
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


