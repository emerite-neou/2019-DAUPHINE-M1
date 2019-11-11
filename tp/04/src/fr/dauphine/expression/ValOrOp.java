package fr.dauphine.expression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public interface ValOrOp {

    private static boolean isNumber(String s) {
        return s.matches("-?[0-9]+");
    }

    private static ValOrOp createBinOp(String symbol, ValOrOp fg, ValOrOp fd) {
        if ( symbol.equals("+") ) return new Add(fg, fd);
        if ( symbol.equals("*") ) return new Mult(fg, fd);
        else throw new IllegalArgumentException("Unexpected symbol");

    }

    private static ValOrOp _parse (List<String> ls) {
        String head = ls.remove(0);
        if (isNumber(head)) return new Val(Integer.parseInt(head));
        else return createBinOp(head, _parse(ls), _parse(ls));
    }

    public static ValOrOp  parse(String s) {
        // pk linkedlist?
        List<String> ls =  new LinkedList(Arrays.asList(s.split(" ")));
        return _parse (ls);
    }

    public int eval();

    public static void main(String[] args) {
        String s = "* + 20 -5 1";
        System.out.println(parse(s));
    }

}
