package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminManageSceneController implements Initializable {
    @FXML
    private Label myTitleLabel;

    @FXML
    private ListView<String> myListView;

    @FXML
    private Button myButton1, myButton2;

    private String[] str = {"1", "2", "3"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(str);
    }

    public void fonk(ActionEvent actionEvent) {
        System.out.println("fonk");
    }

    public void switchtoMainScene1(ActionEvent actionEvent) {

    }
}
