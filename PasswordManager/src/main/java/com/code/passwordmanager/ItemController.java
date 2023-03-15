package com.code.passwordmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private ImageView logoItem;

    @FXML
    private Label nomeItem;

    @FXML
    private Label nomeUtenteItem;

    @FXML
    private Label passwordItem;

    @FXML
    private Label urlItem;

    @FXML
    private ImageView trash;

    private Credentials credential;
    private MyListener myListener;

    public void setData(Credentials credential,MyListener myListener){
        this.myListener = myListener;
        this.credential = credential;
        nomeItem.setText(credential.getNome());
        nomeUtenteItem.setText(credential.getNomeUtente());
        passwordItem.setText(credential.getPassword());
        urlItem.setText(credential.getUrl());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(credential.getLogo())));
        logoItem.setImage(image);
        logoItem.setFitHeight(80);
        logoItem.setFitWidth(200);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trash.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                myListener.deleteListener(credential);
            }
        });
    }
}
