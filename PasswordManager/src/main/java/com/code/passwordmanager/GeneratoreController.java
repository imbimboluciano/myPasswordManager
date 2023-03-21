package com.code.passwordmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;



public class GeneratoreController implements Initializable {

    @FXML
    private CheckBox cbMinuscole;
    @FXML
    private CheckBox cbMaiuscole;
    @FXML
    private CheckBox cbNumeri;
    @FXML
    private CheckBox cbSimboli;
    @FXML
    private Slider sLunghezza;

    @FXML
    private TextField tfPassword;

    @FXML
    private Label lLunghezza;

    @FXML
    private ImageView copy;

    private final String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
    private final String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private final String special = "!@#$%&*()_-?";

    private final String number = "1234567890";


    public void updateLabel(){
        lLunghezza.setText(String.valueOf(sLunghezza.getValue()));
    }

    public void genPassword(){

        String password = Util.generateSecurePassword((int) sLunghezza.getValue(), cbMaiuscole.isSelected(),cbNumeri.isSelected(),cbSimboli.isSelected());
        tfPassword.setText(password);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sLunghezza.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if((int) sLunghezza.getValue() < 8){
                    String css = "-fx-text-fill: #ff471a";
                    lLunghezza.setStyle(css);
                }else{
                    String css = "-fx-text-fill: #009900";
                    lLunghezza.setStyle(css);
                }

                lLunghezza.setText(String.valueOf((int)sLunghezza.getValue()));
            }
        });

        copy.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Util.copyStringToClipboard(tfPassword.getText());
        });

        genPassword();
    }



}
