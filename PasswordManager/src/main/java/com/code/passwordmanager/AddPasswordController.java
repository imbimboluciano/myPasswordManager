package com.code.passwordmanager;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @FXML
    private Button btGenera;

    private Credentials newCredential;
    private boolean choosen = false;

    private String logo = "default_image.png";

    public void imageChooser(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile  = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());
        if (selectedFile != null) {
            System.out.println(selectedFile.getAbsolutePath());

            final InputStream targetStream;
            try
            {
                targetStream = new DataInputStream(new FileInputStream(selectedFile));
                Image image = new Image(targetStream);
                imgChooser.setImage(image);
                imgChooser.setFitWidth(200);
                imgChooser.setFitHeight(80);
                Path source = Paths.get(selectedFile.getAbsolutePath());
                Path targetDir = Paths.get("C:\\Users\\lucia\\IdeaProjects\\MyNewPasswordManager\\PasswordManager\\src\\main\\resources\\com\\code\\passwordmanager\\images\\");

                Files.createDirectories(targetDir);
                Path target = targetDir.resolve(selectedFile.getName());
                System.out.println("copying into " + target);
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                logo = selectedFile.getName();




            } catch (FileNotFoundException fileNotFoundException)
            {
                fileNotFoundException.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            choosen = true;

        }


    }

    public Credentials getNewCredential() throws Exception {

        newCredential = new Credentials();
        newCredential.setNome(tfNewName.getText());
        newCredential.setNomeUtente(tfNewEmail.getText());
        newCredential.setPassword(tfNewPassword.getText());
        newCredential.setUrl(tfNewUrl.getText());
        newCredential.setLogo(logo);

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
                imageChooser();
            }
        });

        imgChooser.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!choosen){
                    imgChooser.setImage(new Image((Objects.requireNonNull(getClass().getResourceAsStream("images/default_image_hover.png")))));
                }

            }
        });

        imgChooser.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!choosen) {
                    imgChooser.setImage(new Image((Objects.requireNonNull(getClass().getResourceAsStream("images/default_image.png")))));
                }
            }
        });

        btGenera.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tfNewPassword.setText(Util.generateSecurePassword(14, true,true,true));
            }
        });
    }

}
