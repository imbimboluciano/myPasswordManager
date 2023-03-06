package com.code.passwordmanager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private BorderPane mainPane;

    public void toArchivio(){
         PageLoader pl = new PageLoader();
         Pane view = pl.getPage("Archivio");
         mainPane.setCenter(view);
    }

    public void toGeneratore(){
        PageLoader pl = new PageLoader();
        Pane view = pl.getPage("Generatore");
        mainPane.setCenter(view);
    }

    public void close(){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toArchivio();
    }
}
