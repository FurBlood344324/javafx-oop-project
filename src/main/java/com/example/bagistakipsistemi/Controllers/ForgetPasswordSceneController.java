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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ForgetPasswordSceneController {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myEmailTextField, myPasswordTextField, myPasswordAgainTextField;

    @FXML
    private Button myChangeButton, myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void changepassword(ActionEvent event) {
        boolean isBlank = false;
        boolean isEqual = false;
        boolean isexist = false;
        textfields.add(myEmailTextField);
        textfields.add(myPasswordTextField);
        textfields.add(myPasswordAgainTextField);
        try{

            for (TextField field : textfields) {
                if(field.getText().isEmpty()){
                    isBlank = true;
                    break;
                }
            }
            if(!isBlank){
                String email = myEmailTextField.getText();
                String password = myPasswordTextField.getText();
                String passwordAgain = myPasswordAgainTextField.getText();
                ArrayList<Data> datas = d1.Read_data();
                d1.Clear_data();
                for(Data data : datas){
                    if(data instanceof IndividualUser){
                        if(((IndividualUser) data).getEmail().equals(email)){
                            if(password.equals(passwordAgain)){
                                ((IndividualUser) data).setPassword(password);
                                isEqual = true;
                            }
                            isexist = true;
                        }
                    }
                    d1.Save_to_data(data);
                }
            }
            if(isBlank){
                throw new NullPointerException();
            }
            else if(!isexist){
                throw new IllegalArgumentException();
            }
            else if(!isEqual){
                throw new RuntimeException();
            }
            switchtologinscene(event);
        }
        catch(NullPointerException e){
            System.out.println("Alanlari doldurunuz!");
        }
        catch(IllegalArgumentException e){
            System.out.println("Kullanici bulunamadi!");
        }
        catch (RuntimeException e) {
            System.out.println("Sifreler ayni degil!");
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public void switchtologinscene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/LoginScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
