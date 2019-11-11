package fr.dauphine.expression;

public abstract class BinOp implements ValOrOp{
    private final ValOrOp fg;
    private final ValOrOp fd;

    // pk protected?
    protected abstract int eval(int n, int m);
    protected abstract String getSymbol();

    // pk protected?
    protected BinOp(ValOrOp fg, ValOrOp fd) {
        this.fg = fg;
        this.fd = fd;
    }

    @Override
    public int eval() {
        return eval(fg.eval(), fd.eval());
    }

    @Override
    public String toString() {
        return "("+fg+" "+getSymbol()+" "+fd+")";
    }
}
