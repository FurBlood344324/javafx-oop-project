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

    // Kullanıcının bireysel kullanıcı olup olmadığını test edip ona göre Bağış Yap Seçenekleri sahnesine gönderir
    @FXML
    private void handleDonateButtonAction(ActionEvent event) {
        boolean isIndUser = false;
        try {
            ArrayList<Data> datas = d1.Read_data();
            for (Data data : datas) {
                if(data instanceof CurrentUser){
                    if(((CurrentUser) data).getUserDataType().equals("individualuser")){
                        isIndUser = true;
                    }
                }
            }
            if(isIndUser)
                switchToScene("/com/example/bagistakipsistemi/Donate1_option_Scene.fxml");
            else
                throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException e) {
            showAlert("Hata", "Bireysel kullanici değilsiniz!", Alert.AlertType.ERROR);
        }
        catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kullanıcının kurumsal kullanıcı olup olmadığını test edip ona göre Bağış Oluştur sahnesine gönderir
    @FXML
    private void handleCreateDonationButtonAction(ActionEvent event) {
        boolean isInsUser = false;
        try {
            ArrayList<Data> datas = d1.Read_data();
            for (Data data : datas) {
                if(data instanceof CurrentUser){
                    if(((CurrentUser) data).getUserDataType().equals("instutionaluser")){
                        isInsUser = true;
                    }
                }
            }
            if(isInsUser)
                switchToScene("/com/example/bagistakipsistemi/Donate2Scene.fxml");
            else
                throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException e) {
            showAlert("Hata", "Kurumsal kullanici değilsiniz!", Alert.AlertType.ERROR);
        }
        catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Bağış Detayları sahnesine gönderir
    @FXML
    private void handleDonationDetailsButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/DonateDetailsScene.fxml");
        }
        catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Top 10 sahnesine gönderir
    @FXML
    private void handleTop10ButtonAction(ActionEvent event) {
        try {
            switchToScene("/com/example/bagistakipsistemi/Top10Scene.fxml");
        }
        catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kullanıcının kullanıcı türünü belirleyip onun hesap ayarları sahnesine gönderir
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
                switchToScene("/com/example/bagistakipsistemi/InstutionalUserAccountSettingsScene.fxml");
            }
            else if(usertype.equals("adminuser")){
                switchToScene("/com/example/bagistakipsistemi/AdminUserAccountSettingsScene.fxml");
            }
        }
        catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
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
        catch (IllegalArgumentException e) {
            showAlert("Hata", "Admin değilsiniz!", Alert.AlertType.ERROR);
        }
        catch (Exception e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kullanının hesabından çıkış yapmasını sağlar
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
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // İletişim butonun basıldığında çalışır ve İletişim bilgilerini içeren bir messagebox oluşturur
    @FXML
    private void handleContactButtonAction(ActionEvent event) {
        try {
            showAlert("İletişim", "İletişim Bilgilerimiz : bagistakipsistemi@gmail.com", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Parametreye göre sahnelere gönderir
    private void switchToScene(String fxmlFile) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                (fxmlFile)));
        stage = (Stage) donateButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // MessageBox penceresi oluşturur ve girilen parametrelere göre onu şekillendirir
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}