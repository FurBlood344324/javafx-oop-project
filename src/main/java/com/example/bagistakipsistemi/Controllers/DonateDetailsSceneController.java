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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class DonateDetailsSceneController implements Initializable {
    @FXML
    private TableView<String[]> donateTableView;

    @FXML
    private TableColumn<String[], String> instutionColumn, donateTypeColumn, spDonateTypeColumn, donatorColumn;

    @FXML
    private ChoiceBox<String> myInstituionNameChoiceBox, myDonateTypeChoiceBox, mySpecialDonateTypeChoiceBox;

    @FXML
    private Button myBackButton;

    private DatabaseManage db = new DatabaseManage();
    private ObservableList<String[]> donations = FXCollections.observableArrayList();
    private Scene scene;
    private Stage stage;
    private Parent root;
    private ObservableList<String> items;

    // TableView'in sütunlarının bilgilerini girip load methodların ve onların listenerlarını çağırır
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            instutionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
            donateTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
            spDonateTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
            donatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));

            items = FXCollections.observableArrayList();
            loadInstituionNameData();
            loadDonateTypeData();
            loadSpDonateTypeData();
            loadData();
            myInstituionNameChoiceBox.setOnShowing(event -> loadInstituionNameData());
            myDonateTypeChoiceBox.setOnShowing(event -> loadDonateTypeData());
            mySpecialDonateTypeChoiceBox.setOnShowing(event -> loadSpDonateTypeData());
            myInstituionNameChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loadData());
            myDonateTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loadData());
            mySpecialDonateTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loadData());
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Girilen bilgilere göre anlık olarak Table View'e filtrelenmiş veriyi yükler
    public void loadData(){
        try{
            ArrayList<Data> datas = db.Read_data();
            ArrayList<String[]> temp_donations = new ArrayList<>();
            for (Data data : datas) {
                if (data instanceof Donate) {
                    if(((Donate) data).getGoalDonateAmount() != 0 && (myInstituionNameChoiceBox.getSelectionModel().getSelectedItem() == null ||
                            myInstituionNameChoiceBox.getSelectionModel().getSelectedItem().equals("Kurum Adi Sec")
                            || myInstituionNameChoiceBox.getSelectionModel().getSelectedItem().equals(((Donate) data).getInstutionName()))
                            && (myDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null ||
                            myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Bagis Turu Sec") ||
                            myDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals(((Donate) data).getDonateType())
                            && (mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null ||
                            mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals("Ozel Bagis Turu Sec") ||
                            mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem().equals(((Donate) data).getSpecialDonateType())))) {
                        String[] donates = new String[]{
                                ((Donate) data).getInstutionName(),
                                ((Donate) data).getDonateType(),
                                ((Donate) data).getSpecialDonateType(),
                                Integer.toString(((Donate) data).getDonateAmount())
                        };
                        temp_donations.add(donates);
                        donations = FXCollections.observableArrayList(temp_donations);
                        donateTableView.setItems(donations);
                    }
                }
            }
        } catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kurumsal isim bilgilerini yükler
    public void loadInstituionNameData(){
        boolean isContain = false;
        try{
            ArrayList<Data> datas = db.Read_data();
            ArrayList<String> instituionnames = new ArrayList<>();
            instituionnames.add("Kurum Adi Sec");
            for (Data data : datas) {
                if (data instanceof Donate) {
                    if(((Donate) data).getGoalDonateAmount() != 0){
                        for(String name : instituionnames){
                            if(((Donate) data).getInstutionName().equals(name)){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            instituionnames.add(((Donate) data).getInstutionName());
                        }
                        isContain = false;
                    }
                }
            }
            items.setAll(instituionnames.stream().filter(line -> !line.isBlank()).collect(Collectors.toList()));
            myInstituionNameChoiceBox.setItems(items);
            if(myInstituionNameChoiceBox.getSelectionModel().getSelectedItem() == null){
                myInstituionNameChoiceBox.getSelectionModel().selectFirst();
            }
            if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem() != null){
                myDonateTypeChoiceBox.getSelectionModel().select("Bagis Turu Sec");
            }
            if(mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem() != null){
                mySpecialDonateTypeChoiceBox.getSelectionModel().select("Ozel Bagis Turu Sec");
            }
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Bağış türlerini yükler
    public void loadDonateTypeData(){
        boolean isContain = false;
        try{
            ArrayList<Data> datas = db.Read_data();
            ArrayList<String> donatetypes = new ArrayList<>();
            donatetypes.add("Bagis Turu Sec");
            for (Data data : datas) {
                if (data instanceof Donate) {
                    if(((Donate) data).getGoalDonateAmount() != 0 && ((Donate) data).getInstutionName().
                            equals(myInstituionNameChoiceBox.getSelectionModel().getSelectedItem())){
                        for(String name : donatetypes){
                            if(((Donate) data).getDonateType().equals(name)){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            donatetypes.add(((Donate) data).getDonateType());
                        }
                        isContain = false;
                    }
                }
            }
            items.setAll(donatetypes.stream().filter(line -> !line.isBlank()).collect(Collectors.toList()));
            myDonateTypeChoiceBox.setItems(items);
            if(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null){
                myDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
            if(mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem() != null){
                mySpecialDonateTypeChoiceBox.getSelectionModel().select("Ozel Bagis Turu Sec");
            }
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Özel bağış türlerini yükler
    public void loadSpDonateTypeData(){
        boolean isContain = false;
        try{
            ArrayList<Data> datas = db.Read_data();
            ArrayList<String> spdonatetypes = new ArrayList<>();
            spdonatetypes.add("Ozel Bagis Turu Sec");
            for (Data data : datas) {
                if (data instanceof Donate) {
                    if(((Donate) data).getGoalDonateAmount() != 0 && ((Donate) data).getInstutionName().
                            equals(myInstituionNameChoiceBox.getSelectionModel().getSelectedItem()) && ((Donate) data).getDonateType().
                            equals(myDonateTypeChoiceBox.getSelectionModel().getSelectedItem())){
                        for(String name : spdonatetypes){
                            if(((Donate) data).getSpecialDonateType().equals(name)){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            spdonatetypes.add(((Donate) data).getSpecialDonateType());
                        }
                        isContain = false;
                    }
                }
            }
            items.setAll(spdonatetypes.stream().filter(line -> !line.isBlank()).collect(Collectors.toList()));
            mySpecialDonateTypeChoiceBox.setItems(items);
            if(mySpecialDonateTypeChoiceBox.getSelectionModel().getSelectedItem() == null){
                mySpecialDonateTypeChoiceBox.getSelectionModel().selectFirst();
            }
        }
        catch (Exception e){
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
