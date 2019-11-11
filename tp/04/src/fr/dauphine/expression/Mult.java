package fr.dauphine.expression;

public class Mult extends BinOp {

    protected Mult(ValOrOp fg, ValOrOp fd) {
        super(fg, fd);
    }

    @Override
    protected int eval(int n, int m) {
        return n*m;
    }

    @Override
    protected String getSymbol() {
        return "*";
    }
}
