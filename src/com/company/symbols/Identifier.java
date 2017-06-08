package com.company.symbols;

/**
 * Created by Ljx on 2017/5/5.
 */
public class Identifier extends Token {
    private static int TAG = 2;

    @Override
    public int getTAG() {
        return TAG;
    }

    @Override
    public String getDetail() {
        return "标识符";
    }

}
