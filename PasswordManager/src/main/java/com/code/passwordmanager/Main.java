package com.code.passwordmanager;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    // Da cambiare https://stackoverflow.com/questions/33094981/javafx-8-open-a-link-in-a-browser-without-reference-to-application
    private static HostServices hostServices ;

    public static HostServices getHost(){
        return hostServices;
    }
    @Override
    public void start(Stage stage) throws IOException {

        hostServices = getHostServices();
        PageLoader pl = new PageLoader();

        Scene scene = new Scene(pl.getPage("Main"));

        stage.getIcons().add(new Image("/com/code/passwordmanager/images/icon.png"));
        stage.setTitle("MyPasswordManager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}