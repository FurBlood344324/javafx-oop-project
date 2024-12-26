package com.example.bagistakipsistemi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    /**
     * Sahne değiştirme metodu.
     */
    private void switchToScene(String fxmlFile, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleDonateButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) donateButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/Donate1_option_Scene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleCreateDonationButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) createDonationButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/Donate2Scene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleDonationDetailsButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) donationDetailsButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/DonateDetailsScene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleTop10ButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) top10Button.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/Top10Scene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleRegisterMenuItemBireysel(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/IndividualRegisterScene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleRegisterMenuItemKurumsal(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/InstutionalRegisterScene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            switchToScene("/com/example/bagistakipsistemi/LoginScene.fxml", stage);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
