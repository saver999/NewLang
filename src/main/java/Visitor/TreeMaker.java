package Visitor;

import nodi.*;

public class TreeMaker implements Visitatore{
public String content;

    @Override
    public String visit(ExprNode node) {
        return null;
    }

    public String visit(BoolConst node) {

        if(node.val == true)
            content = "(TRUE)";
        else
            content = "(FALSE)";

        return content;
    }
    public String visit(IntegerConst node){
        content =  "("+node.val+")";
        return content;
    }

    /*public String visit(Node node){
        String content;
        content =
        return content;
    }*/

    public String visit(RealConst node){
        String content;
        content = "("+node.val+")";
        return content;
    }

    public String visit(StringConst node){
        String content;
        content = "("+ node.val+")";
        return content;
    }

    public String visit(CharConst node){
        String content;
        content = "("+ node.val +")";
        return content;
    }
}
