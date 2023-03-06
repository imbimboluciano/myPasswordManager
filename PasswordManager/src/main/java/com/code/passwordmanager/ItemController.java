package com.code.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ItemController {

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

    private Credentials credential;

    public void setData(Credentials credential){
        this.credential = credential;
        nomeItem.setText(credential.getNome());
        nomeUtenteItem.setText(credential.getNomeUtente());
        passwordItem.setText(credential.getPassword());
        urlItem.setText(credential.getUrl());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(credential.getLogo())));
        logoItem.setImage(image);
    }
}
