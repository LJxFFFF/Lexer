package com.company.lexer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Ljx on 2017/5/7.
 */
public class LexResult {
    private final StringProperty line;
    private final StringProperty symbol;
    private final StringProperty TAG;
    private final StringProperty detail;

    public LexResult() {
        this(null, null, null, null);
    }

    public LexResult(String line, String symbol, String TAG, String detail) {
        this.line = new SimpleStringProperty(line);
        this.symbol = new SimpleStringProperty(symbol);
        this.TAG = new SimpleStringProperty(TAG);
        this.detail = new SimpleStringProperty(detail);
    }

    public String getLine() {
        return line.get();
    }

    public StringProperty lineProperty() {
        return line;
    }

    public void setLine(String line) {
        this.line.set(line);
    }

    public String getSymbol() {
        return symbol.get();
    }

    public StringProperty symbolProperty() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }

    public String getTAG() {
        return TAG.get();
    }

    public StringProperty TAGProperty() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG.set(TAG);
    }

    public String getDetail() {
        return detail.get();
    }

    public StringProperty detailProperty() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }

    @Override
    public String toString() {
        return "LexResult{" +
                "line=" + line +
                ", symbol=" + symbol +
                ", TAG=" + TAG +
                ", detail=" + detail +
                '}';
    }
}
