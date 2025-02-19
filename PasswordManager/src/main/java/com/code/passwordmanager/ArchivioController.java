package com.code.passwordmanager;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ArchivioController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private TextField tfSearch;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label labelPasswordS;
    @FXML
    private Label labelPasswordNS;
    @FXML
    private Label labelStateSecurity;


    private List<Credentials> credentials;
    private ResultSet credentialRemote;
    private MyListener myListener;

    private List<Credentials> getData(){

        List<Credentials> credentials = new ArrayList<>();
        DBManager.setConnection(DBManager.JBDC_Driver_MariaDB,DBManager.JDBC_URL_MariaDB);
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(
                    "SELECT * FROM archivio", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            credentialRemote = statement.executeQuery();

            while(credentialRemote.next()){
                Credentials credential;
                credential = new Credentials();
                credential.setNome(credentialRemote.getString(2));
                credential.setLogo("images/" + credentialRemote.getString(6));
                credential.setNomeUtente(credentialRemote.getString(3));
                credential.setPassword(credentialRemote.getString(4));
                credential.setUrl(credentialRemote.getString(5));
                credentials.add(credential);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return credentials;
    }


    public void initSearch(){
        tfSearch.setText("");
    }

    public void searchInDB(){
        PreparedStatement statement = null;
        try {

            String sql = "SELECT * FROM archivio WHERE nome LIKE ?";
            statement = DBManager.getConnection().prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,"%" + tfSearch.getText() + "%");
            credentialRemote = statement.executeQuery();

            credentials = new ArrayList<>();
            while(credentialRemote.next()){
                Credentials credential;
                credential = new Credentials();
                credential.setNome(credentialRemote.getString(2));
                credential.setLogo("images/" + credentialRemote.getString(6));
                credential.setNomeUtente(credentialRemote.getString(3));
                credential.setPassword(credentialRemote.getString(4));
                credential.setUrl(credentialRemote.getString(5));
                credentials.add(credential);
            }

            showItem();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showItem(){
        grid.getChildren().clear();

        Collections.sort(credentials);


        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < credentials.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                anchorPane.setPrefSize(250,250);

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(credentials.get(i),myListener);

                if(column == 3){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane,column++,row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(6));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addPassword(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddPassword.fxml"));
            DialogPane dialogPane = fxmlLoader.load();

            AddPasswordController addPasswordController = fxmlLoader.getController();


            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Aggiungi Password");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if(clickedButton.get() == ButtonType.OK){

                Credentials newCredentials = addPasswordController.getNewCredential();

                PreparedStatement statement = null;
                String sql = "INSERT INTO archivio(nome,nomeUtente,password,url,logo) values (?,?,?,?,?)";
                statement = DBManager.getConnection().prepareStatement(
                        sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statement.setString(1,newCredentials.getNome());
                statement.setString(2,newCredentials.getNomeUtente());
                statement.setString(3,newCredentials.getPassword());
                statement.setString(4, newCredentials.getUrl());
                statement.setString(5, newCredentials.getLogo());
                statement.executeQuery();

                newCredentials.setLogo("images/" + newCredentials.getLogo());
                credentials.add(newCredentials);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        showItem();
        showPieChart();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        credentials = new ArrayList<>();
        credentials.addAll(getData());
        showPieChart();




        myListener = new MyListener() {
            @Override
            public void deleteListener(Credentials deleteCredentials) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("DeletePassword.fxml"));
                DialogPane dialogPane = null;
                try {
                    dialogPane = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                DeletePasswordController deletePasswordController = fxmlLoader.getController();


                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);
                dialog.setTitle("Rimuovere Password");

                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if(clickedButton.get() == ButtonType.OK) {
                    PreparedStatement statement = null;
                    String sql = "DELETE FROM archivio WHERE nome = ?";
                    try {
                        statement = DBManager.getConnection().prepareStatement(
                                sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        statement.setString(1,deleteCredentials.getNome());
                        statement.executeQuery();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    credentials.removeIf(credentials1 -> credentials1.equals(deleteCredentials));

                    showItem();
                    showPieChart();
                }
            }

            @Override
            public void updateListener(Credentials updateCredentials) {

                PreparedStatement statement = null;
                String sql = " UPDATE archivio SET nomeUtente = ?, password = ?, url = ? WHERE nome = ?";
                try {
                    statement = DBManager.getConnection().prepareStatement(
                            sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

                    statement.setString(1,updateCredentials.getNomeUtente());
                    statement.setString(2,updateCredentials.getPassword());
                    statement.setString(3,updateCredentials.getUrl());
                    statement.setString(4,updateCredentials.getNome());
                    statement.executeQuery();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                showItem();
                showPieChart();


            }
        };



        showItem();

    }


    private void showPieChart(){

        int numberSecurePassword = 0;

        for (Credentials tmp:
             credentials) {
            try {
                if(Util.checkSecurePassword(tmp.decryptPassword(tmp.getPassword(), tmp.generateKey()))){
                    numberSecurePassword++;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        labelPasswordNS.setText(String.valueOf(credentials.size() - numberSecurePassword));
        labelPasswordS.setText(String.valueOf(numberSecurePassword));

        labelStateSecurity.setText(setStateSecurity(numberSecurePassword, credentials.size() - numberSecurePassword));

        pieChart.getData().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Password sicure",numberSecurePassword),
                new PieChart.Data("Password non sicure", credentials.size() - numberSecurePassword)
        );
        pieChart.setData(pieChartData);
    }

    private String setStateSecurity(int securePassword, int nonSecurePassword){

        if(nonSecurePassword == 0){
            return "Tutte le password sono sicure";
        }else if(securePassword > nonSecurePassword){
            return "Problemi! Ci sono alcune password non sicure";
        }else {
            return "Problemi! Problemi! Problemi!";
        }


    }
}
