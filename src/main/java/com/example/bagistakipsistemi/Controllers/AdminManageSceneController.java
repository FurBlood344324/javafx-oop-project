package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminManageSceneController implements Initializable {
    @FXML
    private Label titleLabel;

    @FXML
    private TableView<String[]> myTableView;

    @FXML
    private TableColumn<String[], String> myNameTableColumn, myNicknameTableColumn;

    @FXML
    private Button myDeleteButton, myBackButton;

    private DatabaseManage dbManager = new DatabaseManage();
    private ObservableList<String[]> userdata;
    private Scene scene;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        myNicknameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        userdata = FXCollections.observableArrayList();
        loadUsers();
    }

    private void loadUsers() {
        try{
            ArrayList<Data> datas = dbManager.Read_data();
            ArrayList<String[]> temp_userdata = new ArrayList<>();
            for (Data data : datas) {
                if (data instanceof IndividualUser) {
                    String[] user = new String[]{
                        ((IndividualUser) data).getName(),
                        ((IndividualUser) data).getNickname()
                    };
                    temp_userdata.add(user);
                    userdata = FXCollections.observableArrayList(temp_userdata);
                }
                 else if (data instanceof InstutionalUser) {
                    String[] user = new String[]{
                            ((InstutionalUser) data).getInstitutionName(),
                            ((InstutionalUser) data).getNickname()
                    };
                    temp_userdata.add(user);
                    userdata = FXCollections.observableArrayList(temp_userdata);
                }
            }
            myTableView.setItems(userdata);
        }
        catch (Exception e) {
            showAlert("Hata", "Error!", AlertType.ERROR);
        }
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        String[] selectedUser = myTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Uyarı", "Lütfen bir kullanıcı seçin!", AlertType.WARNING);
            return;
        }
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Kullanıcı Silme");
        confirmAlert.setHeaderText("Kullanıcıyı silmek istediğinizden emin misiniz?");
        confirmAlert.setContentText(selectedUser[0]);

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try{
                    deleteSelectedUser(selectedUser);
                    ArrayList<Data> datas = dbManager.Read_data();
                    for (Data data : datas) {
                        if (data instanceof IndividualUser) {
                            if(selectedUser[1].equals(((IndividualUser) data).getNickname())) {
                                dbManager.Delete_data(data, datas);
                            }
                        }
                        else if (data instanceof InstutionalUser) {
                            if(selectedUser[1].equals(((InstutionalUser) data).getNickname())) {
                                dbManager.Delete_data(data, datas);
                            }
                        }
                    }
                }
                catch (Exception e) {
                    showAlert("Hata", "Error!", AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            switchToMainScene2(event);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    private void deleteSelectedUser(String[] userInfo) {
        try {
            userdata.remove(userInfo);
            showAlert("Başarılı", "Kullanıcı başarıyla silindi.", AlertType.INFORMATION);

        } catch (Exception e) {
            showAlert("Hata", "Error!", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void switchToMainScene2(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                    ("/com/example/bagistakipsistemi/MainScene2.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
