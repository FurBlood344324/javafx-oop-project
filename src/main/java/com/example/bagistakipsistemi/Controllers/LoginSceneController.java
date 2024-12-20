package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginSceneController {
    @FXML
    private Label myTitleLabel, myForgetPasswordLabel, myLabel1, myRegisterLabel;

    @FXML
    private TextField myNicknameTextField, myPasswordTextField;

    @FXML
    private Button myLoginButton, myBackButton;

    private DatabaseManage d1 = new DatabaseManage();
    private User user;
    private Scene scene;
    private Stage stage;
    private Parent root;

    public User getUser() throws NullPointerException {
        if(user != null) {
            return user;
        }
        throw new NullPointerException();
    }

    public void login(ActionEvent actionEvent) throws NullPointerException {
        boolean isLogined = false;
        try{
            String nickname = myNicknameTextField.getText();
            String password = myPasswordTextField.getText();
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas){
                if(data instanceof IndividualUser || data instanceof InstutionalUser){
                    if(((User) data).getNickname().equals(nickname) && ((User) data).getPassword().equals(password)){
                        user = (User) data;
                        switchtomainscene2(actionEvent);
                        isLogined = true;
                    }
                }
            }
            if(!isLogined)
                throw new NullPointerException();
        }
        catch (NullPointerException e){
            System.out.println("Bilgileriniz yanlis");
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    public void switchtomainscene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtomainscene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtoforgetpasswordscene(MouseEvent event){
        System.out.println("switchtoforgetpasswordscene");
    }

    @FXML
    public void switchtoregisterscene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/IndividualRegisterScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onMouseEntered1(MouseEvent event){
        myForgetPasswordLabel.setStyle("-fx-underline: true;");
    }

    @FXML
    public void onMouseExited1(MouseEvent event){
        myForgetPasswordLabel.setStyle("-fx-underline: false;");
    }

    @FXML
    public void onMouseEntered2(MouseEvent event){
        myRegisterLabel.setStyle("-fx-underline: true;");
    }

    @FXML
    public void onMouseExited2(MouseEvent event){
        myRegisterLabel.setStyle("-fx-underline: false;");
    }
}
