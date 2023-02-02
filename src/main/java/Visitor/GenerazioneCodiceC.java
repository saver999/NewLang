package Visitor;

import nodi.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GenerazioneCodiceC implements Visitatore{
    String content;
    Env top;

    @Override
    public String visit(ExprNode node) {
        this.content ="";
        String tmp="";
        String copyContent="";
        int flagOPString=0;


        //controllo se è un operazione unaria per mettere prima il simbolo
        switch (node.nomeNodo){
            case "UminusOp":
                this.content += " -";
                break;
            case "NotOp":
                this.content += " !";
                break;
            case "InparOp":
                this.content += "(";
                break;
        }


        Class classe = node.nodo1.getClass();
        if(classe == BoolConst.class){
            BoolConst nodo = (BoolConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == RealConst.class){
            RealConst nodo = (RealConst)node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == IdVal.class){
            IdVal nodo = (IdVal) node.nodo1;

            RecordSymbolTable record = top.getInTypeEnviroment(nodo.val);
            if(record.isout)
                this.content +="*";

            this.content += nodo.accept(this);
        } else if(classe == IntegerConst.class){
            IntegerConst nodo = (IntegerConst) node.nodo1;
            this.content += nodo.accept(this);
        }  else if(classe == StringConst.class){
            StringConst nodo = (StringConst) node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == FuncallNode.class){
            FuncallNode nodo = (FuncallNode) node.nodo1;
            this.content += nodo.accept(this);
        } else if(classe == ExprNode.class) {
            ExprNode nodo = (ExprNode) node.nodo1;

            if(nodo.typeNode.equalsIgnoreCase("string")&& node.nodo2!=null){
                if(node.nomeNodo.equalsIgnoreCase("StrConcatOp")){
                    this.content += "strcat(strcpy(supporto,"+nodo.accept(this)+"),";

                }else{
                    //GESTIONE strcmp
                    copyContent = this.content;
                    tmp += nodo.accept(this);
                    this.content = copyContent;
                    this.content+="strcmp(";
                    this.content += tmp;
                    flagOPString = 1;
                }
            }else if(node.nomeNodo.equalsIgnoreCase("StrConcatOp")&& node.nodo2!=null){
                if(nodo.typeNode.equalsIgnoreCase("integer")){

                    this.content += "strcat(strcpy(supporto,conversioneInt("+nodo.accept(this)+")),";
                }else if(nodo.typeNode.equalsIgnoreCase("real")){

                    this.content += "strcat(strcpy(supporto,conversioneFloat("+nodo.accept(this)+")),";
                }

           } else if(node.nomeNodo.equalsIgnoreCase("PowOp")){
                this.content+= "pow(";
                this.content += nodo.accept(this);
                this.content+= ",";
            } else{
                this.content += nodo.accept(this);
                if(node.nodo2 == null && node.nomeNodo.equalsIgnoreCase("InparOp")){ //controlli per chiudere le parentesi di inparOP
                    this.content += ")";
                }
            }

        }

        if(node.nodo2 != null) { //nodo2 non è null se operazione non è unaria

            classe = node.nodo2.getClass();
            if (flagOPString == 0) {
                switch (node.nomeNodo) {
                    case "AddOp":
                        this.content += " + ";
                        break;
                    case "MinusOp":
                        this.content += " - ";
                        break;
                    case "MulOp":
                        this.content += " * ";
                        break;
                    case "DivOp":
                        this.content += " / ";
                        break;
                    case "AndOp":
                        this.content += " && ";
                        break;
//                        case "PowOp":
//                            this.content += " && ";
//                            break;
//                        case "StrConcatOp":
//                            this.content += " || ";
//                            break;
                    case "OrOp":
                        this.content += " || ";
                        break;
                    case "GtOp":
                        this.content += " > ";
                        break;
                    case "GeOp":
                        this.content += " >= ";
                        break;
                    case "LtOp":
                        this.content += " < ";
                        break;
                    case "LeOp":
                        this.content += " <= ";
                        break;
                    case "EqOp":
                        this.content += " == ";
                        break;
                    case "NeOp":
                        this.content += " != ";
                        break;
                }
            }


            if (classe == ExprNode.class) {
                ExprNode nodo2 = (ExprNode) node.nodo2;
                if (flagOPString == 0) {
                    if (node.nomeNodo.equalsIgnoreCase("PowOp")) {

                        this.content += nodo2.accept(this);
                        this.content += ")";
                    } else if (node.nomeNodo.equalsIgnoreCase("StrConcatOp")) {

                        if(nodo2.typeNode.equalsIgnoreCase("string")) {
                            this.content += nodo2.accept(this);
                            this.content += ")";
                        }else  if(nodo2.typeNode.equalsIgnoreCase("real")){
                            this.content += "conversioneFloat("+nodo2.accept(this)+")";
                            this.content += ")";

                        }else  if(nodo2.typeNode.equalsIgnoreCase("integer")){
                            this.content += "conversioneInt("+nodo2.accept(this)+")";
                            this.content += ")";
                        }

                    } else {
                        this.content += nodo2.accept(this);
                    }
                } else {
                    tmp="";
                    copyContent = this.content;
                    tmp += nodo2.accept(this);
                    this.content = copyContent;
                    this.content += ", ";
                    this.content += tmp;
                    this.content += ")";
                }

                if (flagOPString == 1) { //se operazione su stringhe
                    switch (node.nomeNodo) {
                        case "GtOp":
                            this.content += " > 0";
                            break;
                        case "GeOp":
                            this.content += " >= 0";
                            break;
                        case "LtOp":
                            this.content += " < 0";
                            break;
                        case "LeOp":
                            this.content += " <= 0";
                            break;
                        case "EqOp":
                            this.content += " == 0";
                            break;
                        case "NeOp":
                            this.content += " != 0";
                            break;
                    }
                }
            }
        }
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
        RecordSymbolTable recordSymbolTable = top.getInTypeEnviroment(node.id.val);
        int tmp = 0;

        this.content = "";
        this.content += node.id.accept(this);
        this.content += "(";


        if (recordSymbolTable.parDecls != null){
            Collections.reverse(recordSymbolTable.parDecls);
            for (int i = 0; i < recordSymbolTable.parDecls.size(); i++) {
                for (int j = 0; j < recordSymbolTable.parDecls.get(i).listaID.size(); j++) {
                    if (recordSymbolTable.parDecls.get(i).isOut) {
                        this.content += "&";
                        this.content += node.listaExprNode.get(tmp).accept(this);
                        if (j != recordSymbolTable.parDecls.get(i).listaID.size() - 1)
                            this.content += ",";
                    } else {
                        this.content += node.listaExprNode.get(tmp).accept(this);
                        if (j != recordSymbolTable.parDecls.get(i).listaID.size() - 1)
                            this.content += ",";
                    }

                    tmp++;
                }

                if (i != recordSymbolTable.parDecls.size() - 1)
                    this.content += ",";

            }
            Collections.reverse(recordSymbolTable.parDecls);
    }


        this.content += ")";


        return content;
    }


    @Override
    public String visit(AssignStat node) {
        this.content ="";
        if(node.idList.size()==node.exprList.size()) {
            for (int i = 0; i < node.idList.size(); i++) {
                RecordSymbolTable record = top.getInTypeEnviroment(node.idList.get(i).val);

                if(record.isout)
                    this.content+="*";

                this.content+=node.idList.get(i).accept(this);
                this.content+=" = ";
                this.content+= node.exprList.get(i).accept(this);
                this.content+=";\n";

            }
        }

        return content;
    }

    @Override
    public String visit(WriteStat node) {
        this.content+="";
        if(node.nomeNodo.equalsIgnoreCase("WritelnOp")){
            this.content+= "printf(\"";
            for(int i=0;i<node.listaExpr.size();i++) {
                switch (node.listaExpr.get(i).typeNode){
                    case "INTEGER":
                        this.content+= "%d ";
                        break;
                    case "REAL":
                        this.content+= "%f ";
                        break;
                    case "STRING":
                        this.content+="%s ";
                        break;
                    case "CHAR":
                        this.content+="%c ";
                        break;
                    case "BOOL":
                        this.content+="%d ";
                        break;

                }


            }
            this.content += "\\n\"";
            for(int i=0;i<node.listaExpr.size();i++) {
                this.content +=", ";
                this.content += node.listaExpr.get(i).accept(this);
            }
            this.content += ");\n";
        }else{
            this.content+= "printf(\"";
            for(int i=0;i<node.listaExpr.size();i++) {
                switch (node.listaExpr.get(i).typeNode){
                    case "INTEGER":
                        this.content+= "%d ";
                        break;
                    case "REAL":
                        this.content+= "%f ";
                        break;
                    case "STRING":
                        this.content+="%s ";
                        break;
                    case "CHAR":
                        this.content+="%c ";
                        break;
                    case "BOOL":
                        this.content+="%d ";
                        break;

                }


            }
            this.content += "\"";
            for(int i=0;i<node.listaExpr.size();i++) {
                this.content +=", ";
                this.content += node.listaExpr.get(i).accept(this);
            }
            this.content += ");\n";
        }


        return content;
    }

    @Override
    public String visit(ReadStat node) {
        this.content ="";
        if(node.val.val!=null){
            this.content+="printf(";

            this.content+= node.val.accept(this);
            this.content+=");";
            this.content+="\n";
        }
        this.content+="\tscanf(\"";
        for(int i =0;i<node.idList.size();i++){
            switch (node.idList.get(i).typeNode){
                case "INTEGER":
                    this.content += "%d";
                    break;
                case "REAL":
                    this.content += "%f";
                    break;
                case "STRING":
                    this.content += "%s";
                    break;
                case "CHAR":
                    this.content += "%c";
                    break;
                case "BOOL":
                    this.content += "%d";
                    break;
            }
        }

        this.content+="\"";
        for(int i =0;i<node.idList.size();i++) {
            this.content += ",";
            if(!node.idList.get(i).typeNode.equals("STRING"))
                this.content += "&";
            this.content += node.idList.get(i).val;

        }
        this.content += ");\n";

        return content;
    }

    @Override
    public String visit(ParDecl node) {

        this.content="";


       // Collections.reverse(node.listaID);

        if(node.nomeNodo.equalsIgnoreCase("ParDeclOP")) {
            for (int i = 0; i < node.listaID.size(); i++) {
                switch (node.type) {
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

                this.content += " ";
                this.content += node.listaID.get(i).accept(this);

                if (i != node.listaID.size() - 1)
                    this.content += ",";

            }
        }else if(node.nomeNodo.equalsIgnoreCase("ParDeclOutOP")){
            for (int i = 0; i < node.listaID.size(); i++) {
                switch (node.type) {
                    case "STRING":
                        this.content += "char **";
                        break;

                    case "BOOL":
                        this.content += node.type.toLowerCase()+"*";
                        break;
                    case "REAL":
                        this.content += "float*";
                        break;
                    case "INTEGER":
                        this.content += "int*";
                        break;
                    case "CHAR":
                        this.content += node.type.toLowerCase()+"*";
                        break;

                }

                this.content += " ";
                this.content += node.listaID.get(i).accept(this);

                if (i != node.listaID.size() - 1)
                    this.content += ",";

            }
        }

        return content;
    }

    @Override
    public String visit(IDInit node) {
        this.content="";
        if(!node.id.typeNode.equalsIgnoreCase("string")) {
            this.content += node.id.accept(this);
            if (node.expr != null) {
                this.content += "=";
                this.content += node.expr.accept(this);
            }
        }else{
            this.content += node.id.accept(this);
            this.content += " = (char*) malloc(sizeof(char) * 100); ";
            if (node.expr != null) {
                this.content += "strcpy(" + node.id.val + " , " + node.expr.accept(this) + ")";
            }
        }


        return content;
    }

    @Override
    public String visit(IDInitObb node) {
        this.content=" ";
        if(!node.cost.typeNode.equalsIgnoreCase("string")) {
            this.content += node.id.accept(this);
            this.content += " = ";
            this.content += node.cost.accept(this);
        }else{
            this.content += node.id.accept(this);
            this.content += " = (char*) malloc(sizeof(char) * 100);\n";
            this.content+="strcpy("+node.id.val+" , "+node.cost.accept(this)+")";
        }

        return content;
    }

    @Override
    public String visit(VarDecl node) {
        this.content = "\t";
        ArrayList<IDInit> supporto =new ArrayList<>();
        if(node.nomeNodo.equalsIgnoreCase("VarDeclObb")){
            for(int i=0; i<node.idInitObb.size(); i++){


                switch (node.idInitObb.get(i).cost.typeNode){
                    case "STRING":
                        this.content += "char *";
                        break;

                    case "BOOL":
                        this.content += node.idInitObb.get(i).cost.typeNode.toLowerCase();
                        this.content += " ";
                        break;
                    case "REAL":
                        this.content += "float";
                        break;
                    case "INTEGER":
                        this.content += "int";
                        break;
                    case "CHAR":
                        this.content += node.idInitObb.get(i).cost.typeNode.toLowerCase();
                        this.content += " ";
                        break;

                }

                this.content += node.idInitObb.get(i).accept(this);

                if( i != node.idInitObb.size()-1)
                    this.content += ";\n";



            }
            this.content += ";\n";

        }else{
            switch (node.type){
                case "STRING":
                    this.content += "char ";
                    break;

                case "BOOL":
                    this.content += node.type.toLowerCase();
                    this.content += " ";
                    break;
                case "REAL":
                    this.content += "float ";
                    break;
                case "INTEGER":
                    this.content += "int ";
                    break;
                case "CHAR":
                    this.content += node.type.toLowerCase();
                    this.content += " ";
                    break;

            }

            for(int i=0; i<node.listaID.size();i++) {
                if(node.listaID.get(i).expr==null){
                    supporto.add(0,node.listaID.get(i));
                }

            }
            for(int i=0; i<node.listaID.size();i++) {
                if(node.listaID.get(i).expr!=null){
                    supporto.add(node.listaID.get(i));
                }
            }

            for(int i=0; i<supporto.size();i++) {

                if(node.type.equals("STRING"))
                    this.content += "*";


               // node.listaID.get(i).expr.nomeNodo;

                this.content += supporto.get(i).accept(this);

                if (i != supporto.size() - 1 && !node.type.equals("STRING") )
                    this.content += ",";

                if(node.type.equals("STRING")&& i != supporto.size() - 1){
                    this.content += ";\n";
                    this.content += "char ";
                }

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
        this.content = "\t";

        if(node.nodo != null) {
            Class classe = node.nodo.getClass();

            if (classe == IfStat.class) {
                IfStat nodo = (IfStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == ForStat.class) {
                ForStat nodo = (ForStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == WriteStat.class) {
                WriteStat nodo = (WriteStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == AssignStat.class) {
                AssignStat nodo = (AssignStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == FuncallNode.class) {
                FuncallNode nodo = (FuncallNode) node.nodo;
                this.content += nodo.accept(this);
                this.content += ";\n";
            } else if (classe == WhileStat.class) {
                WhileStat nodo = (WhileStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == ReadStat.class) {
                ReadStat nodo = (ReadStat) node.nodo;
                this.content += nodo.accept(this);
            } else if (classe == ExprNode.class) {
                ExprNode nodo = (ExprNode) node.nodo;
                this.content += "return ";
                this.content += nodo.accept(this);
                this.content += ";\n";
            }
//            else if (node.nameStat.equalsIgnoreCase("returnVoid")) {
//                this.content += "return ";
//                this.content += ";\n";
//
//            }
        }else if (node.nameStat.equalsIgnoreCase("returnVoid")) {
                this.content += "return ";
                this.content += ";\n";

        }

        return content;
    }

    @Override
    public String visit(Body node) {
        this.content ="";
        top= node.currentEnv;
        this.content +="{\n";
        ArrayList<VarDecl> vardeclsObb = new ArrayList<>();
        ArrayList<VarDecl> vardeclsFlag1 = new ArrayList<>();
        ArrayList<VarDecl> vardeclsFlag0 = new ArrayList<>();
        for(int i=0;i<node.listaVar.size();i++){
                if (node.listaVar.get(i).nomeNodo.equalsIgnoreCase("vardeclobb")){
                    vardeclsObb.add(node.listaVar.get(i));
                }
        }
        for(int i=0;i<node.listaVar.size();i++){
            int flag=0;

            if (node.listaVar.get(i).nomeNodo.equalsIgnoreCase("VarDecl")){
                for(int h=0; h<node.listaVar.get(i).listaID.size();h++) {
                    if(node.listaVar.get(i).listaID.get(h).expr!=null){
                        if(node.listaVar.get(i).listaID.get(h).expr.nomeNodo.equalsIgnoreCase("id")){
                           flag=1;
                        }
                    }
                }if(flag==1){
                    vardeclsFlag1.add(node.listaVar.get(i));
                }else{
                    vardeclsFlag0.add(node.listaVar.get(i));
                }
            }
        }

        for(int i=0;i<vardeclsObb.size();i++){
            content+=vardeclsObb.get(i).accept(this);
        }
        for(int i=0;i<vardeclsFlag0.size();i++){
            content+=vardeclsFlag0.get(i).accept(this);
        }
        for(int i=0;i<vardeclsFlag1.size();i++){
            content+=vardeclsFlag1.get(i).accept(this);
        }

        Collections.reverse(node.listaStat);
        for(int i=0;i<node.listaStat.size();i++){
            if(node.listaStat.get(i) != null) {

                content += node.listaStat.get(i).accept(this);
            }
        }
        Collections.reverse(node.listaStat);

        this.content +="}\n";
        top= top.prev;
        return content;
    }

    @Override
    public String visit(Body node, ArrayList<ParDecl> parDecls) {
        return null;
    }

    @Override
    public String visit(IfStat node) {
        this.content ="";
        this.content +="if(";
        content+=node.nodeEx.accept(this);
        this.content +=")";
        this.content+=node.body.accept(this);
        if(node.els!=null){
            content+=node.els.accept(this);
        }
        return content;
    }

    @Override
    public String visit(WhileStat node) {
        this.content = "";
        this.content += "   while (";
        content+=node.nodeEx.accept(this);
        this.content += ")";

        this.content+=node.body.accept(this);

        return content;
    }

    @Override
    public String visit(ForStat node) {
        this.content = "";
        this.content += "for (";

        this.content+="int ";
        this.content+= node.id.accept(this);
        this.content += " = ";
        this.content+= node.val1.accept(this);
        this.content += ";";

        this.content+= node.id.accept(this);
        this.content += " <= ";
        this.content+= node.val2.accept(this);
        this.content += ";";

        this.content+= node.id.accept(this);
        this.content += "++";


        this.content += ")";
        this.content+=node.body.accept(this);

        return content;
    }

    @Override
    public String visit(FunDecl node) {

        top = node.body.currentEnv;
        this.content = "";

        switch (node.type){
            case "STRING":
                this.content += "char* ";
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
            case "VOID":
                this.content+= node.type.toLowerCase();

        }




        this.content += " ";
        this.content += node.id.accept(this);
        this.content += "(";
        Collections.reverse(node.listaPar);
        for(int i=0;i<node.listaPar.size();i++) {
            this.content += node.listaPar.get(i).accept(this);
            if(i != node.listaPar.size()-1)
                this.content+=",";
        }
        Collections.reverse(node.listaPar);
        this.content += ")";
        this.content+=node.body.accept(this);

        return content;
    }

    @Override
    public String visit(MainFunDecl node) {
        RecordSymbolTable recordSymbolTable = top.getInTypeEnviroment(node.fundecl.id.val);
        int tmp=0;

        this.content = "";
        this.content += "int main(){\n";
        this.content += "int intero=0;\n";
        this.content += "char carattere=' ';\n";
        this.content += "float float1=0;\n";
        this.content += "char *stringa=\"\";\n";
        this.content += "bool booleano=false;\n";
        this.content += node.fundecl.id.accept(this)+"(";
        if (recordSymbolTable.parDecls != null){
            Collections.reverse(recordSymbolTable.parDecls);
            for (int i = 0; i < recordSymbolTable.parDecls.size(); i++) {
                for (int j = 0; j < recordSymbolTable.parDecls.get(i).listaID.size(); j++) {
                    if (recordSymbolTable.parDecls.get(i).isOut) {
                        this.content += "&";
                       switch (recordSymbolTable.parDecls.get(i).listaID.get(j).typeNode){
                           case "INTEGER":
                               this.content+= "intero ";
                               break;
                           case "REAL":
                               this.content+= "float ";
                               break;
                           case "STRING":
                               this.content+="stringa ";
                               break;
                           case "CHAR":
                               this.content+="carattere ";
                               break;
                           case "BOOL":
                               this.content+="booleano ";
                               break;
                       }

                        if (j != recordSymbolTable.parDecls.get(i).listaID.size() - 1)
                            this.content += ",";
                    } else {
                        switch (recordSymbolTable.parDecls.get(i).listaID.get(j).typeNode){
                            case "INTEGER":
                                this.content+= "intero ";
                                break;
                            case "REAL":
                                this.content+= "float ";
                                break;
                            case "STRING":
                                this.content+="stringa ";
                                break;
                            case "CHAR":
                                this.content+="carattere ";
                                break;
                            case "BOOL":
                                this.content+="booleano ";
                                break;
                        }
                        if (j != recordSymbolTable.parDecls.get(i).listaID.size() - 1)
                            this.content += ",";
                    }

                    tmp++;
                }

                if (i != recordSymbolTable.parDecls.size() - 1)
                    this.content += ",";

            }
            Collections.reverse(recordSymbolTable.parDecls);
        }


        this.content += ");\n";
        this.content += "return 0;\n";
        this.content += "}\n";

        return content;
    }

    @Override
    public String visit(ProgramRoot node) {
        top = node.currentEnv;

        this.content = "#include <stdio.h>\n";
        this.content += "#include <stdlib.h>\n";
        this.content += "#include <string.h>\n";
        this.content += "#include <stdbool.h>\n";
        this.content += "#include <math.h>\n";
        this.content += "//prototipi funzioni\n";

        //scorro la env global per scrivere i prototipi
        if(node.declist1.size() >= 1) {


            for (int i = 0; i < node.declist1.size(); i++) {
                Class classe = node.declist1.get(i).getClass();

                if (classe == FunDecl.class) {
                    String prototipo="";
                    String contentTemp="";
                    FunDecl nodo = (FunDecl) node.declist1.get(i);
                    contentTemp=this.content;
                    prototipo += nodo.accept(this);

                    prototipo=prototipo.substring(0,prototipo.indexOf("{"));
                    prototipo +=";\n";

                    this.content =contentTemp;
                    this.content+=prototipo;
                }
            }
        }
        if(node.declist2.size() >= 1) {



            for (int i = 0; i < node.declist2.size(); i++) {
                Class classe = node.declist2.get(i).getClass();

                if (classe == FunDecl.class) {

                    String prototipo="";
                    String contentTemp="";
                    FunDecl nodo = (FunDecl) node.declist2.get(i);
                    contentTemp=this.content;
                    prototipo += nodo.accept(this);

                    prototipo=prototipo.substring(0,prototipo.indexOf("{"));
                    prototipo +=";\n";
                    this.content =contentTemp;
                    this.content+=prototipo;

                }
            }
        }

        String prototipo="";
        String contentTemp="";
        contentTemp=this.content;
        prototipo +=node.mainFun.fundecl.accept(this);
        prototipo=prototipo.substring(0,prototipo.indexOf("{"));
        prototipo +=";\n";
        this.content =contentTemp;
        this.content+=prototipo;

        this.content +="\n";

        //fine scorrimento per scrittura prototipi
        this.content += "\n" +
                "char  *conversioneFloat(float number){\n" +
                "  \n" +
                " char *buf = malloc(10*sizeof(char));\n" +
                " sprintf (buf, \"%f\", number);\n" +
                "  return buf;\n" +
                "}\n" +
                "char * conversioneInt(int number){\n" +
                " char *buf = malloc(10*sizeof(char));\n" +
                " sprintf (buf, \"%d\", number);\n" +
                "  return buf;\n" +
                "}";

        this.content += "char supporto[100];\n\n";


        //dichiarazioni di variabili globali tutte all'inizio
        if(node.declist1.size() >= 1) {


            for (int i = 0; i < node.declist1.size(); i++) {
                Class classe = node.declist1.get(i).getClass();

                if (classe == VarDecl.class) {
                    VarDecl nodo = (VarDecl) node.declist1.get(i);
                    this.content += nodo.accept(this);
                }
            }
        }
        if(node.declist2.size() >= 1) {


            for (int i = 0; i < node.declist2.size(); i++) {
                Class classe = node.declist2.get(i).getClass();

                if (classe == VarDecl.class) {
                    VarDecl nodo = (VarDecl) node.declist2.get(i);
                    this.content += nodo.accept(this);
                }
            }
        }

        //dichiarazioni di funzioni
        if(node.declist1.size() >= 1) {


            for (int i = 0; i < node.declist1.size(); i++) {
                Class classe = node.declist1.get(i).getClass();

                if (classe == FunDecl.class) {
                    FunDecl nodo = (FunDecl) node.declist1.get(i);
                    this.content += nodo.accept(this);
                }
            }
        }
        if(node.declist2.size() >= 1) {


            for (int i = 0; i < node.declist2.size(); i++) {
                Class classe = node.declist2.get(i).getClass();

                if (classe == FunDecl.class) {
                    FunDecl nodo = (FunDecl) node.declist2.get(i);
                    this.content += nodo.accept(this);
                }
            }
        }
        this.content +=node.mainFun.fundecl.accept(this);
        this.content +=node.mainFun.accept(this);

        return content;
    }

    @Override
    public Object visit(ElseStat node) {
        this.content ="";
        this.content +="else";
        this.content+= node.body.accept(this);
        return content;
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

    public String saveFileC(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output"+File.separator+"main.c"), "utf-8"));
            writer.write(this.content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Errore nella scrittura del file");
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("Errore durante la chiusura del file");
            }
        }
     return this.content;
    }
}


