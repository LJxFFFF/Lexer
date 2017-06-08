package com.company.symbols;

/**
 * Created by Ljx on 2017/5/5.
 */
public class UnsignedNumber extends Token {
    private static int TAG = 3;

    @Override
    public int getTAG() {
        return TAG;
    }

    @Override
    public String getDetail() {
        return "无符号数字";
    }
}
