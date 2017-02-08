package com.saar.roy.millionaire;

/**
 * Created by Roy-PC on 2/8/2017.
 */
public enum Types {
    multiply("multiply",'*'),
    divide("divide",'/'),
    plus("plus",'+'),
    minus("minus",'-')
    ;

    public String getType() {
        return type;
    }

    public char getSymbol() {
        return symbol;
    }

    private final String type;
    private final char symbol;

    Types(String type,char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

}
