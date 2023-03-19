package com.code.passwordmanager;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemController implements Initializable{

    @FXML
    private ImageView logoItem;

    @FXML
    private Label nomeItem;

    @FXML
    private TextField nomeUtenteItem;

    @FXML
    private TextField passwordItem;

    @FXML
    private TextField urlItem;

    @FXML
    private ImageView trash;

    @FXML
    private ImageView copyNome;
    @FXML
    private ImageView copyPassword;

    @FXML
    private ImageView viewPassword;

    @FXML
    private  ImageView link;

    private boolean modifica = false;

    @FXML
    private Button btModifica;

    @FXML
    private HBox hbNomeUtente;
    @FXML
    private HBox hbPassword;
    @FXML
    private HBox hbUrl;

    private Credentials credential;
    private MyListener myListener;


    public void setData(Credentials credential,MyListener myListener){
        this.myListener = myListener;
        this.credential = credential;
        nomeItem.setText(credential.getNome());
        nomeUtenteItem.setText(credential.getNomeUtente());
        passwordItem.setText("******");
        urlItem.setText(credential.getUrl());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(credential.getLogo())));
        logoItem.setImage(image);
        logoItem.setFitHeight(80);
        logoItem.setFitWidth(200);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        trash.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                trash.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/deletehover.png"))));
            }
        });

        trash.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                trash.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/delete.png"))));
            }
        });
        trash.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                myListener.deleteListener(credential);
            }
        });

        btModifica.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!modifica){
                    btModifica.setStyle(" -fx-background-color: linear-gradient(to bottom,#009900, #00e600);");
                    btModifica.setText("Conferma");
                    hbNomeUtente.setStyle("-fx-border-color: linear-gradient(to right,#346efe, #0099ff); -fx-border-radius:10px");
                    hbPassword.setStyle("-fx-border-color: linear-gradient(to right,#346efe, #0099ff); -fx-border-radius:10px ");
                    hbUrl.setStyle("-fx-border-color: linear-gradient(to right,#346efe, #0099ff); -fx-border-radius:10px");
                    nomeUtenteItem.setEditable(true);
                    passwordItem.setEditable(true);
                    try {
                        passwordItem.setText(credential.decryptPassword(credential.getPassword(), credential.generateKey()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    urlItem.setEditable(true);
                    modifica = true;
                }else{


                    nomeUtenteItem.setEditable(false);
                    passwordItem.setEditable(false);
                    urlItem.setEditable(false);

                    credential.setNomeUtente(nomeUtenteItem.getText());
                    try {
                        credential.setPassword(credential.encryptPassword(passwordItem.getText(), credential.generateKey()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    credential.setUrl(urlItem.getText());

                    try {
                        credential.checkParameters();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    myListener.updateListener(credential);
                    modifica = false;
                }
            }
        });





        copyNome.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Util.copyStringToClipboard(credential.getNomeUtente());
            }
        });


        copyPassword.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    Util.copyStringToClipboard(credential.decryptPassword(credential.getPassword(), credential.generateKey()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });


        viewPassword.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    passwordItem.setText(credential.decryptPassword(credential.getPassword(), credential.generateKey()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }




            }
        });

        viewPassword.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                passwordItem.setText("******");
            }
        });


        link.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.getHost().showDocument(credential.getUrl());
            }
        });

    }

}
