package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class IndividualUserAccountSceneController implements Initializable {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myNameTextField, mySurnameTextField, myTelnoTextField,
            myNicknameTextField ,myEmailTextField, myPasswordTextField;

    @FXML
    private Button myEditButton, myDeleteAccountButton ,myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;
    private Data user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/com/example/bagistakipsistemi/LoginScene.fxml"));
        LoginSceneController loginSceneController = loader.getController();
        user = loginSceneController.getUser();
        /*myNameTextField.setText(user.getName());
        mySurnameTextField.setText(user.getSurname());
        myNicknameTextField.setText(user.getNickname());
        myEmailTextField.setText(user.getEmail());
        myPasswordTextField.setText(user.getPassword());*/
        textfields.add(myNameTextField);
        textfields.add(mySurnameTextField);
        textfields.add(myTelnoTextField);
        textfields.add(myNicknameTextField);
        textfields.add(myEmailTextField);
        textfields.add(myPasswordTextField);
    }

    public void editaccount(ActionEvent event) {
        boolean isBlank = false;
        try{
            for (TextField field : textfields) {
                if(field.getText().isEmpty()){
                    isBlank = true;
                    break;
                }
            }
            if(!isBlank){
                ArrayList<Data> datas = d1.Read_data();
                d1.Clear_data();
                for(Data data : datas) {
                    if (data instanceof IndividualUser) {
                        if (myEmailTextField.getText().equals(((IndividualUser) data).getEmail())) {
                            ((IndividualUser) data).setName(myNameTextField.getText());
                            ((IndividualUser) data).setSurname(mySurnameTextField.getText());
                            ((IndividualUser) data).setTelephonenumber(Integer.parseInt(myTelnoTextField.getText()));
                            ((IndividualUser) data).setNickname(myNicknameTextField.getText());
                            ((IndividualUser) data).setEmail(myEmailTextField.getText());
                            ((IndividualUser) data).setPassword(myPasswordTextField.getText());
                        }
                    }
                    d1.Save_to_data(data);
                }
                switchtomainscene2(event);
            }
            else
                throw new NullPointerException();
        }
        catch(NumberFormatException e){
            System.out.println("Telefon alanina sayi giriniz!");
        }
        catch(NullPointerException e){
            System.out.println("Alanlari bos birakmayiniz!");
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public void deleteaccount(ActionEvent event) {
        try{
            ArrayList<Data> datas = d1.Read_data();
            d1.Delete_data(user, datas);
            switchtomainscene1(event);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public void switchtomainscene1(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtomainscene2(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
