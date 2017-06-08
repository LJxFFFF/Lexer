package com.company.utils;

import com.company.lexer.LexResult;
import com.company.symbols.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ljx on 2017/5/5.
 *
 * 词法分析工具类
 *
 */
public class LexerUtil {
    private final static String KEYWORD_PATH = "symbols\\keywords.txt";
    private final static String OPERATOR_PATH = "symbols\\operators.txt";
    private final static String SEPARATOR_PATH = "symbols\\separators.txt";
    private final static KeyWord keyWord = new KeyWord(KEYWORD_PATH);
    private final static Operator operator = new Operator(OPERATOR_PATH);
    private final static Separator separator = new Separator(SEPARATOR_PATH);
    private final static UnsignedNumber unsignedNumber = new UnsignedNumber();
    private final static Identifier identifier = new Identifier();

    public static List<LexResult> LexicalAnalysis(File file) {
        int currentLine = 1;
        List<LexResult> lexResultList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s;
            List<String> list;
            while ((s = in.readLine()) != null) {
                list = division(s);
                for (String symbol : list) {
                    Token currentType;
                    if      (isKeyWord(symbol))     currentType = keyWord;
                    else if (isOperator(symbol))    currentType = operator;
                    else if (isSeparator(symbol))   currentType = separator;
                    else {
                        try {
                            Integer.valueOf(symbol);
                            currentType = unsignedNumber;
                        } catch (NumberFormatException e) {
                            currentType = identifier;
                        }
                    }
                    LexResult lexResult = new LexResult(String.valueOf(currentLine), symbol, String.valueOf(currentType.getTAG()), currentType.getDetail());
                    lexResultList.add(lexResult);
                }
                currentLine++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lexResultList;
    }

    //得到一行中分割后的字符列表
    private static List<String> division(String s) {
        char[] chars = s.trim().toCharArray();  //去除首尾空格并转化为字符数组
        List<String> list = new ArrayList<>();  //保存组合出的单词和字符
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (isOperator(String.valueOf(chars[i])) || isSeparator(String.valueOf(chars[i])) || chars[i] == ' ') {
                if (sb.length() != 0) list.add(sb.toString().replaceAll(" ", ""));
                if (chars[i] != ' ') list.add(String.valueOf(chars[i]));
                sb.delete(0, sb.length());  //清空StringBuilder
                continue;
            }
            sb.append(chars[i]);
        }
        return list;
    }

    private static boolean isKeyWord(String s) {
        return keyWord.getKeywordList().contains(s);
    }
    private static boolean isOperator(String s) {
        return operator.getOperatorList().contains(s);
    }
    private static boolean isSeparator(String s) {
        return separator.getSeparatorList().contains(s);
    }
}
