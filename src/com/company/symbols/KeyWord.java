package com.company.symbols;

import java.util.List;


/**
 * Created by Ljx on 2017/5/5.
 */
public class KeyWord extends Token {
    private static List<String> keyWords;
    private static int TAG = 1;

    public KeyWord(String fileName) {
        super(fileName);
        keyWords = super.getTokens();
    }

    public List<String> getKeywordList() {
        return keyWords;
    }

    @Override
    public int getTAG() {
        return TAG;
    }

    @Override
    public String getDetail() {
        return "保留字";
    }
}
