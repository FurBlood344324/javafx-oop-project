package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class Donate1_option_SceneController {
    @FXML
    private ImageView GeneralImageView, EducationImageView, HealthImageView, SacrificeImageView, FoodImageView, WaterWellImageView,
            DisasterImageView, NatureImageView, ProjectImageView;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final String Data_path = Paths.get(
            System.getProperty("user.dir"), "src", "main", "resources", "DonateTypeData.txt"
    ).toString();

    // Seçilen bağış türünü geçici olan bir veritabanına kaydeder ve Bağış1 sahnesine geçer
    @FXML
    public void DonateSelection(Event event, String type) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path, true));
            writer.write(type);
            writer.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bagistakipsistemi/Donate1Scene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e) {
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // ana Sayfa 2'ye geçer
    @FXML
    public void switchToMainScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

    @FXML
    public void generalaction(MouseEvent event) {
        DonateSelection(event, "Genel");
    }

    @FXML
    public void educationaction(MouseEvent event) {
        DonateSelection(event, "Egitim");
    }

    @FXML
    public void healthaction(MouseEvent event) {
        DonateSelection(event, "Saglik");
    }

    @FXML
    public void sacrificeaction(MouseEvent event) {
        DonateSelection(event, "Kurban");
    }

    @FXML
    public void foodaction(MouseEvent event) {
        DonateSelection(event, "Gida");
    }

    @FXML
    public void waterwellaction(MouseEvent event) {
        DonateSelection(event, "Su Kuyusu");
    }

    @FXML
    public void disasteraction(MouseEvent event) {
        DonateSelection(event, "Afet");
    }

    @FXML
    public void natureaction(MouseEvent event) {
        DonateSelection(event, "Doga");
    }

    @FXML
    public void projectaction(MouseEvent event) {
        DonateSelection(event, "Proje");
    }

    @FXML
    public void opacityincrease1(MouseEvent event) {
        GeneralImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease1(MouseEvent event) {
        GeneralImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease2(MouseEvent event) {
        EducationImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease2(MouseEvent event) {
        EducationImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease3(MouseEvent event) {
        HealthImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease3(MouseEvent event) {
        HealthImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease4(MouseEvent event) {
        SacrificeImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease4(MouseEvent event) {
        SacrificeImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease5(MouseEvent event) {
        FoodImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease5(MouseEvent event) {
        FoodImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease6(MouseEvent event) {
        WaterWellImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease6(MouseEvent event) {
        WaterWellImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease7(MouseEvent event) {
        DisasterImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease7(MouseEvent event) {
        DisasterImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease8(MouseEvent event) {
        NatureImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease8(MouseEvent event) {
        NatureImageView.setStyle("-fx-opacity: 0.6;");
    }

    @FXML
    public void opacityincrease9(MouseEvent event) {
        ProjectImageView.setStyle("-fx-opacity: 1;");
    }

    @FXML
    public void opacitydecrease9(MouseEvent event) {
        ProjectImageView.setStyle("-fx-opacity: 0.6;");
    }
}
