package com.code.passwordmanager;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPasswordController implements Initializable {


    @FXML
    private DialogPane dialogPane;
    @FXML
    private TextField tfNewEmail;

    @FXML
    private TextField tfNewName;

    @FXML
    private TextField tfNewPassword;

    @FXML
    private TextField tfNewUrl;

    @FXML
    private ImageView imgChooser;

    private Credentials newCredential;



    public void imageChoser(){

    }

    public Credentials getNewCredential() throws Exception {

        newCredential = new Credentials();
        newCredential.setNome(tfNewName.getText());
        newCredential.setNomeUtente(tfNewEmail.getText());
        newCredential.setPassword(tfNewPassword.getText());
        newCredential.setUrl(tfNewUrl.getText());
        newCredential.setLogo("default_image.png");

        newCredential.checkParameters();

        try {
            newCredential.setPassword(newCredential.encryptPassword(newCredential.getPassword(), newCredential.generateKey()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newCredential;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        imgChooser.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());
                if(file != null){
                    try {
                        imgChooser.setImage(new Image(Objects.requireNonNull(getClass().getResource(file.getName())).openStream()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
