package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.sql.DatabaseMetaData;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Donate1SceneController implements Initializable {
    @FXML
    private ChoiceBox<String> myInstituionNameChoiceBox, mySpDonateTypeChoiceBox;

    @FXML
    private TextField aciklamaTextField, amountTextField;

    @FXML
    private CheckBox gondAdSoyad, checkAnonym;

    private DatabaseManage d1 = new DatabaseManage();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<String> items;
    private String donateType;
    private final String Data_path = Paths.get(
            System.getProperty("user.dir"), "src", "main", "resources", "DonateTypeData.txt"
    ).toString();

    // Geçici olan veritabanından veriyi çekip kaydeder ve load methodlarını ve onların listenerlarını çalıştırır
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(Data_path));
            donateType = reader.readLine();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path));
            writer.close();
            items = FXCollections.observableArrayList();
            loadInstituionNameData();
            loadSpDonateTypeData();
            myInstituionNameChoiceBox.setOnShowing(event -> loadInstituionNameData());
            mySpDonateTypeChoiceBox.setOnShowing(event -> loadSpDonateTypeData());
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Bağış türü choicebox'ının bilgilerini yükler
    private void loadInstituionNameData() {
        boolean isContain = false;
        try{
            ArrayList<Data> datas = d1.Read_data();
            ArrayList<String> temp_items = new ArrayList<>();
            temp_items.add("Kurum Adi Sec");
            for (Data data : datas) {
                if(data instanceof Donate){
                    if(((Donate) data).getGoalDonateAmount() != 0 && donateType.equals(((Donate) data).getDonateType())){
                        for(String item : temp_items){
                            if(((Donate) data).getInstutionName().equals(item)){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            temp_items.add(((Donate) data).getInstutionName());
                        }
                    }
                }
            }
            items = FXCollections.observableArrayList(temp_items);
            myInstituionNameChoiceBox.setItems(items);
            if(myInstituionNameChoiceBox.getSelectionModel().getSelectedItem() == null){
                myInstituionNameChoiceBox.getSelectionModel().selectFirst();
            }
            if(mySpDonateTypeChoiceBox.getSelectionModel().getSelectedItem() != null){
                mySpDonateTypeChoiceBox.getSelectionModel().select("Ozel Bagis Turu Sec");
            }
        } catch (IOException e){
            System.err.println("Dosya okunamadı: " + e.getMessage());
        }
    }

    // Özel bağış türü choicebox'ının bilgilerini yükler
    private void loadSpDonateTypeData() {
        boolean isContain = false;
        try{
            ArrayList<Data> datas = d1.Read_data();
            ArrayList<String> temp_items = new ArrayList<>();
            temp_items.add("Ozel Bagis Turu Sec");
            for (Data data : datas) {
                if(data instanceof Donate){
                    if(((Donate) data).getGoalDonateAmount() != 0 && donateType.equals(((Donate) data).getDonateType())
                            && ((Donate) data).getInstutionName().equals(myInstituionNameChoiceBox.getSelectionModel().getSelectedItem())){
                        for(String item : temp_items){
                            if(((Donate) data).getInstutionName().equals(item)){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            temp_items.add(((Donate) data).getSpecialDonateType());
                        }
                    }
                }
            }
            items = FXCollections.observableArrayList(temp_items);
            mySpDonateTypeChoiceBox.setItems(items);
            if(mySpDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null){
                mySpDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
        } catch (IOException e){
            System.err.println("Dosya okunamadı: " + e.getMessage());
        }
    }

    // Girilen bilgiler bir bağış nesnesinde toplar ve onu veritabanına kaydeder
    @FXML
    private void saveDonation(ActionEvent event) throws IOException {

        String selectedInst = myInstituionNameChoiceBox.getValue();
        String spdonatetype = mySpDonateTypeChoiceBox.getValue();
        String amountText = amountTextField.getText();
        String desc = aciklamaTextField.getText();
        Boolean isAnonym = checkAnonym.isSelected();
        ArrayList<Data> datas = new ArrayList<>();

        try {
            datas = d1.Read_data();
            CurrentUser user = new CurrentUser();
            Donate donate;
            for (Data data : datas) {
                if(data instanceof CurrentUser){
                    user = (CurrentUser) data;
                }
                else if(data instanceof Donate){
                    if(((Donate) data).getGoalDonateAmount() != 0 && selectedInst.equals(((Donate) data).getInstutionName())
                            && donateType.equals(((Donate) data).getDonateType()) && spdonatetype.equals(((Donate) data).getSpecialDonateType())){
                        d1.Delete_data(data, datas);
                        ((Donate) data).setDonateAmount(((Donate) data).getDonateAmount() + Integer.parseInt(amountText));
                        d1.Save_to_data(data);
                    }
                }
            }
            if(gondAdSoyad.isSelected()){
                donate = new Donate(user.getDatavar1(), user.getDatavar2(), selectedInst, donateType, spdonatetype,
                        Integer.parseInt(amountText), 0,
                        user.getDatavar1().toUpperCase() + " " + user.getDatavar2().toUpperCase() + " " + desc, isAnonym);
            }
            else{
                donate = new Donate(user.getDatavar1(), user.getDatavar2(), selectedInst, donateType, spdonatetype,
                        Integer.parseInt(amountText), 0, desc, isAnonym);
            }
            d1.Save_to_data(donate);
            switchToMainScene2(event);
        }
        catch (NumberFormatException e) {
            d1.Clear_data();
            for(Data data : datas){
                d1.Save_to_data(data);
            }
            showAlert("Hata", "Girdiğiniz Tutar Geçersiz!", Alert.AlertType.ERROR);
        } catch (Exception e) {
            d1.Clear_data();
            for(Data data : datas){
                d1.Save_to_data(data);
            }
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Ana Sayfa 1'e gönderir
    @FXML
    private void switchToDonate1_option_Scene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/Donate1_option_Scene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Ana Sayfa 2'ye gönderir
    private void switchToMainScene2(ActionEvent event) throws IOException {
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
}
