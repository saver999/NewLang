package Visitor;

/*
* questa classe rappresenta un'operazione verrà poi usata dalla classe OpTypeTable
*
* */

public class OpType {
    public String op;
    public String firstOperand;
    public String secondOperand;
    public String result;

    public OpType(String op, String firstOperand, String secondOperand, String result) {
        this.op = op;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.result = result;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OpType{" +
                "op='" + op + '\'' +
                ", firstOperand='" + firstOperand + '\'' +
                ", secondOperand='" + secondOperand + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
