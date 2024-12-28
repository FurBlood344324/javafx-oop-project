package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Donate2SceneController {
    @FXML
    private TextField targetAmountText, descriptionText;

    @FXML
    private ChoiceBox<String> myDonateTypeChoiceBox, mySpDonateTypeChoiceBox;

    @FXML
    private Button myBackButton;

    private DatabaseManage db = new DatabaseManage();
    private ObservableList<String> items;
    private Scene scene;
    private Stage stage;
    private Parent root;

    // Load methodları ve onların listenerlarını çağırır
    @FXML
    public void initialize() {
        try{
            items = FXCollections.observableArrayList();
            loadDonateTypeData();
            loadSpDonateTypeData();
            myDonateTypeChoiceBox.setOnShowing(event -> loadDonateTypeData());
            mySpDonateTypeChoiceBox.setOnShowing(event -> loadSpDonateTypeData());
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Bağış türü choicebox'ının bilgilerini yükler
    public void loadDonateTypeData(){
        try{
            ArrayList<String> temp_items = new ArrayList<>();
            temp_items.add("Bagis Turu Sec");
            temp_items.add("Genel");
            temp_items.add("Eğitim");
            temp_items.add("Sağlık");
            temp_items.add("Kurban");
            temp_items.add("Gıda");
            temp_items.add("Su Kuyusu");
            temp_items.add("Afet");
            temp_items.add("Doğa");
            temp_items.add("Proje");
            items = FXCollections.observableArrayList(temp_items);
            myDonateTypeChoiceBox.setItems(items);
            if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null){
                myDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
            if(mySpDonateTypeChoiceBox.getSelectionModel().getSelectedItem() != null){
                mySpDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Özel ağış türü choicebox'ının bilgilerini yükler
    public void loadSpDonateTypeData(){
        try{
            ArrayList<String> temp_items = new ArrayList<>();
            temp_items.add("Ozel Bagis Turu Sec");
            if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Genel")){
                temp_items.add("Para");
                temp_items.add("Sadaka");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Eğitim")){
                temp_items.add("Yurt İçi Okul");
                temp_items.add("Yurt Dışı Okul");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Sağlık")){
                temp_items.add("Kanser");
                temp_items.add("SMA");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Kurban")){
                temp_items.add("Adak Kurban");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Gıda")){
                temp_items.add("Yemek Yardımı");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Su Kuyusu")){
                temp_items.add("Özel Su Kuyusu");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Afet")){
                temp_items.add("A Yeri İçin Afet Yardımı");
                temp_items.add("B Yeri İçin Afet Yardımı");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Doğa")){
                temp_items.add("Ağaç");
            }
            else if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Proje")){
                temp_items.add("A Yeri İçin X Projesi");
                temp_items.add("B Yeri İçin Y Projesi");
            }
            items = FXCollections.observableArrayList(temp_items);
            mySpDonateTypeChoiceBox.setItems(items);
            if(mySpDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null){
                mySpDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Girilen bilgiler bir bağış nesnesinde toplar ve onu veritabanına kaydeder
    public void saveDonationCollection(ActionEvent event) {
        try{
            ArrayList<Data> datas = db.Read_data();
            CurrentUser currentUser = new CurrentUser();
            for(Data data : datas){
                if(data instanceof CurrentUser){
                    currentUser = (CurrentUser) data;
                }
            }

            String selectedType = myDonateTypeChoiceBox.getValue();
            String selectedSpType = mySpDonateTypeChoiceBox.getValue();
            String description = descriptionText.getText();
            String targetamount = targetAmountText.getText();

            if(selectedType == null || selectedType.equals("Bagis Turu Sec") || selectedSpType.equals("Ozel Bagis Turu Sec") ||
                    selectedSpType == null || description == null || description.isEmpty() || targetamount == null ||
                    targetamount.isEmpty() || targetamount.equals("0")){
                throw new NullPointerException();
            }

            Donate collectingDonate = new Donate("0", "0", currentUser.getDatavar1(), selectedType, selectedSpType,
                    0, Integer.parseInt(targetamount), description, false);

            db.Save_to_data(collectingDonate);
            showAlert("Başarılı", "Başarılı bir şekilde bağış eklediniz", Alert.AlertType.CONFIRMATION);
            switchToMainScene2(event);
        }
        catch (NullPointerException e){
            showAlert("Hata", "Boş bir alan bırakmayınız!", Alert.AlertType.ERROR);
        }
        catch (NumberFormatException e){
            showAlert("Hata", "Hatalı bir miktar girdiniz!", Alert.AlertType.ERROR);
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Ana Sayfa 2'ye gönderir
    @FXML
    public void switchToMainScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/MainScene2.fxml")));
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
}
