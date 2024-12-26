package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainScene2Controller {
    @FXML
    private Button donateButton, createDonationButton, donationDetailsButton, top10Button, contactButton;

    @FXML
    private MenuButton accountMenuButton;

    @FXML
    private MenuItem AccountSettingsMenuItem;

    DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private void handleDonateButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/Donate1_option_Scene.fxml");
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleCreateDonationButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/Donate2Scene.fxml");
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleDonationDetailsButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/DonateDetailsScene.fxml");
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleTop10ButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/Top10Scene.fxml");
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleAccountSettingsButtonAction(ActionEvent event) {
        try {
            String usertype = "";
            ArrayList<Data> datas = d1.Read_data();
            for (Data data : datas) {
                if(data instanceof CurrentUser){
                    usertype = ((CurrentUser) data).getUserDataType();
                }
            }
            if(usertype.equals("individualuser")){
                switchToScene("/com/example/bagistakipsistemi/IndividualUserAccountSettingsScene.fxml");
            }
            else if(usertype.equals("instutionaluser")){
                switchToScene("/com/example/bagistakipsistemi/InstituionalUserAccountSettingsScene.fxml");
            }
            else if(usertype.equals("adminuser")){
                switchToScene("/com/example/bagistakipsistemi/AdminUserAccountSettingsScene.fxml");
            }
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleAdminManageButtonAction(ActionEvent event) {
        try {
            String usertype = "";
            ArrayList<Data> datas = d1.Read_data();
            for (Data data : datas) {
                if(data instanceof CurrentUser){
                    usertype = ((CurrentUser) data).getUserDataType();
                }
            }
            if(usertype.equals("adminuser")){
                switchToScene("/com/example/bagistakipsistemi/AdminManageScene.fxml");
            }
            else
                throw new IllegalArgumentException();
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Admin değilsiniz!");
        }
    }

    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Çıkış Ekranı");
            alert.setHeaderText("Hesabından Çıkış Yapmak Uzeresin!");
            alert.setContentText("Emin Misin ?");

            if(alert.showAndWait().get() == ButtonType.OK){
                ArrayList<Data> datas = d1.Read_data();
                for(Data data : datas){
                    if(data instanceof CurrentUser){
                        d1.Delete_data(data, datas);
                        break;
                    }
                }
                switchToScene("/com/example/bagistakipsistemi/MainScene1.fxml");
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void handleContactButtonAction(ActionEvent event) {
        System.out.println("Contact");
    }

    private void switchToScene(String fxmlFile) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                (fxmlFile)));
        stage = (Stage) donateButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}