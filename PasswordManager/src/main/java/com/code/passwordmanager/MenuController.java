package com.code.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    @FXML
    private AnchorPane recordPane, generatorPane;

    public void toRecordPane(){
        recordPane.setVisible(true);
        generatorPane.setVisible(false);
    }

    public void toGeneratorPane(){
        recordPane.setVisible(false);
        generatorPane.setVisible(true);
    }
}
