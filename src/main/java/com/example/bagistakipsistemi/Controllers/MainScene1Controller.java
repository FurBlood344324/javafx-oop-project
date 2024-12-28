package com.example.bagistakipsistemi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import java.io.IOException;

public class MainScene1Controller {
    @FXML
    private Button donateButton , createDonationButton, donationDetailsButton, top10Button, loginButton
            ,contactButton;
    @FXML
    private MenuButton registerButton;

    // Girilen parametreye göre sahneyi değiştirir
    private void switchToScene(String fxmlFile, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Bağış Yap butonuna basıldığında çalışır ve messagebox oluşturur
    @FXML
    void handleDonateButtonAction(ActionEvent event) {
        showAlert("Hata", "Hesabınıza giriş yapmalısınız!", Alert.AlertType.ERROR);
    }

    // Bağış Oluştur butonuna basıldığında çalışır ve messagebox oluşturur
    @FXML
    void handleCreateDonationButtonAction(ActionEvent event) {
        showAlert("Hata", "Hesabınıza giriş yapmalısınız!", Alert.AlertType.ERROR);
    }

    // Bağış Detayları butonuna basıldığında çalışır ve messagebox oluşturur
    @FXML
    void handleDonationDetailsButtonAction(ActionEvent event) {
        showAlert("Hata", "Hesabınıza giriş yapmalısınız!", Alert.AlertType.ERROR);
    }

    // Top 10 butonuna basıldığında çalışır ve messagebox oluşturur
    @FXML
    void handleTop10ButtonAction(ActionEvent event) {
        showAlert("Hata", "Hesabınıza giriş yapmalısınız!", Alert.AlertType.ERROR);
    }

    // Bireysel Kayıt ol butonuna basıldığında çalışır ve Bireysel Kayıt Ol sahnesine gönderir
    @FXML
    void handleRegisterMenuItemBireysel(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/IndividualRegisterScene.fxml", stage);
        } catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kurumsal Kayıt ol butonuna basıldığında çalışır ve Kurumsal Kayıt Ol sahnesine gönderir
    @FXML
    void handleRegisterMenuItemKurumsal(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/InstutionalRegisterScene.fxml", stage);
        } catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Giriş Yap butonuna basıldığında çalışır ve Giriş Yap sahnesine gönderir
    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/LoginScene.fxml", stage);
        } catch (IOException e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // İletişim butonuna basıldığında çalışır ve İletişim bilgilerini gösteren bir messagebox oluşturur
    @FXML
    void handleContactButtonAction(ActionEvent event) {
        try {
            showAlert("İletişim", "İletişim Bilgilerimiz : bagistakipsistemi@gmail.com", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
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
