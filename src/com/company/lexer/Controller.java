package com.company.lexer;

import com.company.Main;
import com.company.utils.FileReadUtil;
import com.company.utils.LexerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ljx on 2017/5/8.
 */
public class Controller {
    public TableView<LexResult> resultTable;
    public TableColumn<LexResult, String> LineColumn;
    public TableColumn<LexResult, String> SymbolColumn;
    public TableColumn<LexResult, String> TAGColumn;
    public TableColumn<LexResult, String> DetailColumn;
    public Label filePath;
    public TextArea code;


    private static ObservableList<LexResult> lexResults;
    private Main main;

    public void compile(ActionEvent actionEvent) {

        // Add observable list data to the table
        System.out.println(main.getLoadFile());
        if (main.getLoadFile() == null) {
            filePath.setText("未导入文件");
        } else {
            if (lexResults != null) lexResults.clear();
            lexResults = FXCollections.observableArrayList(LexerUtil.LexicalAnalysis(main.getLoadFile()));
            resultTable.setItems(lexResults);
        }
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    private void initialize() {
        // Initialize the table with the two columns.
        LineColumn.setCellValueFactory(
                cellData ->  cellData.getValue().lineProperty());
        SymbolColumn.setCellValueFactory(
                cellData -> cellData.getValue().symbolProperty());
        TAGColumn.setCellValueFactory(
                cellData -> cellData.getValue().TAGProperty());
        DetailColumn.setCellValueFactory(
                cellData -> cellData.getValue().detailProperty());

    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setCode(File file) {
        try {
            filePath.setText(file.getPath());
            code.setText(FileReadUtil.GetCode(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
