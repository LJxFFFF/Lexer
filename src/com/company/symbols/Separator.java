package com.company.symbols;

import java.util.List;

/**
 * Created by Ljx on 2017/5/5.
 */
public class Separator extends Token {
    private static List<String> separator;
    private static int TAG = 5;

    public Separator(String fileName) {
        super(fileName);
        separator = super.getTokens();
    }

    public List<String> getSeparatorList() {
        return separator;
    }

    @Override
    public int getTAG() {
        return TAG;
    }

    @Override
    public String getDetail() {
        return "分隔符";
    }
}
