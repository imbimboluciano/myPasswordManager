package com.code.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.Objects;

public class AddPasswordController {


    @FXML
    private TextField tfNewEmail;

    @FXML
    private TextField tfNewName;

    @FXML
    private TextField tfNewPassword;

    @FXML
    private TextField tfNewUrl;

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
}
