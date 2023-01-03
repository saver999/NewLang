package Visitor;

import nodi.*;

import java.util.ArrayList;

public class AnalisiSemantica implements Visitatore{

    Env top = null; //tabella dei simboli corrente
    ArrayList<Env> stack = new ArrayList<Env>(); //stack per tenere conto di tutte le tabelle dei simboli
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
        node.typeNode =" INT";
        return null;
    }

    @Override
    public String visit(RealConst node) {
        node.typeNode = "FLOAT";
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
        return null;
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


        return null;
    }

    @Override
    public String visit(IDInitObb node) {
        return null;
    }

    @Override
    public String visit(VarDecl node) {
        int flag=0;
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
                    top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode );//inserisce nella tabella al top
                } else {
                    if (recordPrec.kind.equalsIgnoreCase("var")) {
                        top.put(node.idInitObb.get(i).id.val, "var", null, node.idInitObb.get(i).cost.typeNode); //ci assicuriamo che sia una variabile se si tartta di un metodo allora errore es: int a a()
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
