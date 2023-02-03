package Visitor;

import java.util.ArrayList;

/*
* In questa classe vengono implementate le regole fornite sugli operandi delle operazioni
* */
public class OpTypeTable {
    public ArrayList<OpType> listaOpType;

    public OpTypeTable() {
        listaOpType = new ArrayList<>();

        listaOpType.add(new OpType("AddOp", "INTEGER", "INTEGER", "INTEGER"));
        listaOpType.add(new OpType("MinusOp", "INTEGER", "INTEGER", "INTEGER"));
        listaOpType.add(new OpType("MulOp", "INTEGER", "INTEGER", "INTEGER"));
        listaOpType.add(new OpType("DivOp", "INTEGER", "INTEGER", "INTEGER"));
        listaOpType.add(new OpType("PowOp", "INTEGER", "INTEGER", "INTEGER"));

        listaOpType.add(new OpType("AddOp", "INTEGER", "REAL", "REAL"));
        listaOpType.add(new OpType("MinusOp", "INTEGER", "REAL", "REAL"));
        listaOpType.add(new OpType("MulOp", "INTEGER", "REAL", "REAL"));
        listaOpType.add(new OpType("DivOp", "INTEGER", "REAL", "REAL"));
        listaOpType.add(new OpType("PowOp", "INTEGER", "REAL", "REAL"));

        listaOpType.add(new OpType("AddOp", "REAL", "INTEGER", "REAL"));
        listaOpType.add(new OpType("MinusOp", "REAL", "INTEGER", "REAL"));
        listaOpType.add(new OpType("MulOp", "REAL", "INTEGER", "REAL"));
        listaOpType.add(new OpType("DivOp", "REAL", "INTEGER", "REAL"));
        listaOpType.add(new OpType("PowOp", "REAL", "INTEGER", "REAL"));

        listaOpType.add(new OpType("AddOp", "REAL", "REAL", "REAL"));
        listaOpType.add(new OpType("MinusOp", "REAL", "REAL", "REAL"));
        listaOpType.add(new OpType("MulOp", "REAL", "REAL", "REAL"));
        listaOpType.add(new OpType("DivOp", "REAL", "REAL", "REAL"));
        listaOpType.add(new OpType("PowOp", "REAL", "REAL", "REAL"));

        listaOpType.add(new OpType("AndOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("OrOp", "BOOL", "BOOL", "BOOL"));

        listaOpType.add(new OpType("LtOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("LeOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("EqOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("NeOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("GtOp", "BOOL", "BOOL", "BOOL"));
        listaOpType.add(new OpType("GeOp", "BOOL", "BOOL", "BOOL"));

        listaOpType.add(new OpType("LtOp", "INTEGER", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("LeOp", "INTEGER", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("EqOp", "INTEGER", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("NeOp", "INTEGER", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("GtOp", "INTEGER", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("GeOp", "INTEGER", "INTEGER", "BOOL"));

        listaOpType.add(new OpType("LtOp", "INTEGER", "REAL", "BOOL"));
        listaOpType.add(new OpType("LeOp", "INTEGER", "REAL", "BOOL"));
        listaOpType.add(new OpType("EqOp", "INTEGER", "REAL", "BOOL"));
        listaOpType.add(new OpType("NeOp", "INTEGER", "REAL", "BOOL"));
        listaOpType.add(new OpType("GtOp", "INTEGER", "REAL", "BOOL"));
        listaOpType.add(new OpType("GeOp", "INTEGER", "REAL", "BOOL"));

        listaOpType.add(new OpType("LtOp", "REAL", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("LeOp", "REAL", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("EqOp", "REAL", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("NeOp", "REAL", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("GtOp", "REAL", "INTEGER", "BOOL"));
        listaOpType.add(new OpType("GeOp", "REAL", "INTEGER", "BOOL"));

        listaOpType.add(new OpType("LtOp", "REAL", "REAL", "BOOL"));
        listaOpType.add(new OpType("LeOp", "REAL", "REAL", "BOOL"));
        listaOpType.add(new OpType("EqOp", "REAL", "REAL", "BOOL"));
        listaOpType.add(new OpType("NeOp", "REAL", "REAL", "BOOL"));
        listaOpType.add(new OpType("GtOp", "REAL", "REAL", "BOOL"));
        listaOpType.add(new OpType("GeOp", "REAL", "REAL", "BOOL"));



        listaOpType.add(new OpType("StrConcatOp", "STRING", "STRING", "STRING"));
        listaOpType.add(new OpType("StrConcatOp", "STRING", "INTEGER", "STRING"));
        listaOpType.add(new OpType("StrConcatOp", "STRING", "REAL", "STRING"));
        listaOpType.add(new OpType("StrConcatOp", "INTEGER", "STRING", "STRING"));
        listaOpType.add(new OpType("StrConcatOp", "REAL", "STRING", "STRING"));

        listaOpType.add(new OpType("LtOp", "STRING", "STRING", "BOOL"));
        listaOpType.add(new OpType("LeOp", "STRING", "STRING", "BOOL"));
        listaOpType.add(new OpType("EqOp", "STRING", "STRING", "BOOL"));
        listaOpType.add(new OpType("NeOp", "STRING", "STRING", "BOOL"));
        listaOpType.add(new OpType("GtOp", "STRING", "STRING", "BOOL"));
        listaOpType.add(new OpType("GeOp", "STRING", "STRING", "BOOL"));






        //operazioni unarie
        listaOpType.add(new OpType("UminusOp", "INTEGER", "", "INTEGER"));
        listaOpType.add(new OpType("UminusOp", "REAL", "", "REAL"));

        listaOpType.add(new OpType("NotOp", "BOOL", "", "BOOL"));

//        listaOpType.add(new OpType("InparOp", "BOOL", "", "BOOL"));
//        listaOpType.add(new OpType("InparOp", "INTEGER", "", "INTEGER"));
//        listaOpType.add(new OpType("InparOp", "REAL", "", "REAL"));
//        listaOpType.add(new OpType("InparOp", "STRING", "", "STRING"));
//        listaOpType.add(new OpType("InparOp", "CHAR", "", "CHAR"));
    }

    public String searchOp(String operation, String fOp, String sOp) {
        //System.out.println("op: " + operation + " fOp " + fOp + " sOp " + sOp);
        for (int i = 0; i < listaOpType.size(); i++) {
            if(listaOpType.get(i).op.equals(operation) && listaOpType.get(i).firstOperand.equals(fOp) && listaOpType.get(i).secondOperand.equals(sOp)) {
                return listaOpType.get(i).result;
            }
        }
        return "error";
    }

}
