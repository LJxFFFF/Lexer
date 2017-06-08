package com.company.utils;

import java.util.*;

/**
 * Created by Ljx on 2017/5/12.
 * <p>
 * 语法分析工具类(递归分析)
 * <p>
 * 算法表达式文法如下：
 * E -> E+T | E-T | T
 * T -> T*F | T/F | F
 * F -> (E) | i
 * <p>
 * 消除左递归后的文法如下：
 * E -> TE'
 * E'-> +TE'|-TE'|空
 * T -> FT'
 * T'-> *FT'|/FT'|空
 * F -> (E) | i
 */
public class ParserUtil {
    private final ArrayList<String> tokens = new ArrayList<>();
    private final ListIterator<String> iterator;
    private boolean isSyntaxError = false;
    private int syntaxErrorIndex = -1;

    public ParserUtil(String str) {
        for (int i = 0; i < str.length(); i++) {
            tokens.add(String.valueOf(str.charAt(i)));
        }
        iterator = tokens.listIterator();
    }

    public void parser() {
        processF();//程序入口
        while (iterator.hasNext()) {
            String str = iterator.next();
            iterator.previous();
            if (str.equals("+") || str.equals("-")) {
                processEChild();
            } else if (str.equals("*") || str.equals("/")) {
                processTChild();
            } else if (str.equals("(") || str.equals("i")) {
                processF();
            } else {
                error(iterator.nextIndex());
                break;
            }
        }
        if (isSyntaxError) {
            System.out.println("有语法错误, 位置为： " + syntaxErrorIndex);
        } else {
            System.out.println("没有语法错误");
        }
    }

    //子程序P(E)
    private void processE() {
        processT();
        processEChild();
    }

    //子程序P(T)
    private void processT() {
        processF();
        processTChild();
    }

    //子程序P(F)
    private void processF() {
        if (!iterator.hasNext()) {
            error(iterator.nextIndex());
            return;
        }
        String str = iterator.next();
        if (str.equals("(")) {
            processE();
            if (!iterator.hasNext()) {
                error(iterator.nextIndex());
                return;
            }
            else if (iterator.next().equals(")")) {
                return;
            } else {
                iterator.previous();
                error(iterator.nextIndex());
            }
        } else if (str.equals("i")) {
            return;
        } else {
            error(iterator.nextIndex());
        }
    }

    //子程序P(E')
    private void processEChild() {
        if (!iterator.hasNext()) return;
        String str = iterator.next();
        if (str.equals("+") || str.equals("-")) {
            processT();
            processEChild();
        } else {
            iterator.previous();
            return;
        }
    }

    //子程序P(T')
    private void processTChild() {
        if (!iterator.hasNext()) return;
        String str = iterator.next();
        if (str.equals("*") || str.equals("/")) {
            processF();
            processTChild();
        } else {
            iterator.previous();
            return;
        }
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    private void error(int errorIndex) {
        isSyntaxError = true;
        if (syntaxErrorIndex == -1) {
            syntaxErrorIndex = errorIndex;
        }   //确保最终保存的错误索引是第一个得到的错误索引
//        System.exit(1);
    }

    public static void main(String[] args) {
        ParserUtil p = new ParserUtil("(i+i)/i*i++");
        p.parser();

    }
}
