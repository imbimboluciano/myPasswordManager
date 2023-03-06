package com.code.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArchivioController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Credentials> credentials = new ArrayList<>();

    private List<Credentials> getData(){
        List<Credentials> credentials = new ArrayList<>();
        Credentials credential;

        for (int i = 0; i <20 ; i++) {
            credential = new Credentials();
            credential.setNome("Amazon");
            credential.setLogo("images/amazon_logo.jpg");
            credential.setNomeUtente("luciano");
            credential.setPassword("Abcdefgh");
            credential.setUrl("https://amazon.it");
            credentials.add(credential);
        }
        return credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        credentials.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < credentials.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(credentials.get(i));

                if(column == 3){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane,column++,row);

                GridPane.setMargin(anchorPane, new Insets(14));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
