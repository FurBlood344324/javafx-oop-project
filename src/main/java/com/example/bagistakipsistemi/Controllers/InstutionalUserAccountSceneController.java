package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.DatabaseManage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InstutionalUserAccountSceneController {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myInstituionalNameTextField, myIBANnoTextField, myExplanationTextField,
            myNicknameTextField ,myEmailTextField, myPasswordTextField;

    @FXML
    private Button myEditButton, myDeleteAccountButton ,myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void edit(ActionEvent event) {

    }
}
