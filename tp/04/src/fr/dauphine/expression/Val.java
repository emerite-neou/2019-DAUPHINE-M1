package fr.dauphine.expression;

public class Val implements ValOrOp {
    private final int val;

    public Val(int val) {
        this.val = val;
    }

    @Override
    public int eval() {
        return val;
    }

    @Override
    public String toString() {
        return ""+val;
    }
}
