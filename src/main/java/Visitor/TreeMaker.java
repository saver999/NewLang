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

    @Override
    public String visit(ExprNode node) {
        this.content = "";


        CharConst nodo = (CharConst) node.nodo1;
        this.content += nodo.accept(this);
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
