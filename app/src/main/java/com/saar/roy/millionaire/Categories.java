package com.saar.roy.millionaire;

/**
 * Created by Roy-PC on 2/8/2017.
 */
public enum Categories {
    multiply("multiply"),
    divide("divide"),
    plus("plus"),
    minus("minus")
    ;

    private final String category;

    Categories(String category) {
        this.category = category;
    }
}
