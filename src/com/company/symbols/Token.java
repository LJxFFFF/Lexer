package com.company.symbols;

import com.company.utils.FileReadUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ljx on 2017/5/6.
 */
public abstract class Token {
    private static List<String> tokens = new ArrayList<>();

    public Token() {
    }

    public Token(String fileName) {
        try {
            tokens = FileReadUtil.ReadSymbols(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " NOT FOUND!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<String> getTokens() {
        return tokens;
    }

    public abstract int getTAG();

    public abstract String getDetail();
}
