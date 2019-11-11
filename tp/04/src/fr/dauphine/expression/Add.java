package fr.dauphine.expression;

public class Add extends BinOp {

    protected Add(ValOrOp fg, ValOrOp fd) {
        super(fg, fd);
    }

    @Override
    protected int eval(int n, int m) {
        return n+m;
    }

    @Override
    protected String getSymbol() {
        return "+";
    }
}
