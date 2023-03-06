package com.code.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PageLoader pl = new PageLoader();

        Scene scene = new Scene(pl.getPage("Main"));

        String css = this.getClass().getResource("css/Menu.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.getIcons().add(new Image("C:/Users/lucia/IdeaProjects/MyNewPasswordManager/PasswordManager/src/main/resources/com/code/passwordmanager/images/icon.png"));
        stage.setTitle("MyPasswordManager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}