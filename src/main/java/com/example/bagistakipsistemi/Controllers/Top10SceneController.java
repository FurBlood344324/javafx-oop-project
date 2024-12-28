package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Top10SceneController implements Initializable {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TableView<String[]> myTableView;

    @FXML
    private TableColumn<String[], String> myNameSurnameColumn, myInstituionNameColumn, myDonateTypeColumn,
            mySpecialDonateTypeColumn, myDonateAmountColumn;

    @FXML
    private Button myBackButton;

    private DatabaseManage db = new DatabaseManage();
    private ObservableList<String[]> items;
    private Scene scene;
    private Stage stage;
    private Parent root;

    // TableView'in sütunlarının bilgilerini doldurur ve load metodunu çağırır
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            myNameSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
            myInstituionNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
            myDonateTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
            mySpecialDonateTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));
            myDonateAmountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[4]));

            items = FXCollections.observableArrayList();
            loadData();
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Veri Tabanından çekilen bilgilere göre en yüksek 10 bağış miktarı olan bağışı sıralayıp TableView'de gösterir
    private void loadData() {
        try {
            ArrayList<Data> datas = db.Read_data();
            ArrayList<String[]> temp_items = new ArrayList<>();
            ArrayList<String[]> temp_items2 = new ArrayList<>();
            for (Data data : datas) {
                if(data instanceof Donate){
                    if(((Donate) data).getGoalDonateAmount() == 0){
                        if(!((Donate) data).getIsAnonym()){
                            String[] donate = new String[]{
                                    ((Donate) data).getSenderName() + " " + ((Donate) data).getSenderSurname(),
                                    ((Donate) data).getInstutionName(),
                                    ((Donate) data).getDonateType(),
                                    ((Donate) data).getSpecialDonateType(),
                                    Integer.toString(((Donate) data).getDonateAmount())
                            };
                            temp_items.add(donate);
                        }
                        else{
                            String[] donate = new String[]{
                                    "Anonim",
                                    ((Donate) data).getInstutionName(),
                                    ((Donate) data).getDonateType(),
                                    ((Donate) data).getSpecialDonateType(),
                                    Integer.toString(((Donate) data).getDonateAmount())
                            };
                            temp_items.add(donate);
                        }
                    }
                }
            }
            for (int i = 0; i < temp_items.size(); i++) {
                for (int j = i + 1; j < temp_items.size(); j++) {
                    if(Integer.parseInt(temp_items.get(i)[4]) < Integer.parseInt(temp_items.get(j)[4])){
                        String[] temp = temp_items.get(i);
                        temp_items.set(i, temp_items.get(j));
                        temp_items.set(j, temp);
                    }
                }
            }
            for (int i = 0; i < 10; ++i){
                temp_items2.add(temp_items.get(i));
            }
            items = FXCollections.observableArrayList(temp_items2);
            myTableView.setItems(items);
        }
        catch (Exception e) {
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
