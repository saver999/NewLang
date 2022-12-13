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

}
