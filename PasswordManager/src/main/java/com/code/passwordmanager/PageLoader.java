package com.code.passwordmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PageLoader {

    private Pane view;

    public Pane getPage(String filename){
        try{

            URL fileUrl = MenuController.class.getResource(filename + ".fxml");
            if(fileUrl == null){
                throw new FileNotFoundException("File FXML non trovato");
            }

            view = new FXMLLoader().load(fileUrl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}
