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

public class InstutionalUserAccountSceneController implements Initializable {
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
    private CurrentUser currentuser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas){
                if(data instanceof CurrentUser){
                    currentuser = new CurrentUser(((CurrentUser) data).getDatavar1(), ((CurrentUser) data).getDatavar2(),
                            ((CurrentUser) data).getDatavar3(), ((CurrentUser) data).getNickname(),
                            ((CurrentUser) data).getEmail(), ((CurrentUser) data).getPassword(),
                            ((CurrentUser) data).getUserDataType());
                    break;
                }
            }
            myInstituionalNameTextField.setText(currentuser.getDatavar1());
            myIBANnoTextField.setText(currentuser.getDatavar2());
            myExplanationTextField.setText(currentuser.getDatavar3());
            myNicknameTextField.setText(currentuser.getNickname());
            myEmailTextField.setText(currentuser.getEmail());
            myPasswordTextField.setText(currentuser.getPassword());
            textfields.add(myInstituionalNameTextField);
            textfields.add(myIBANnoTextField);
            textfields.add(myExplanationTextField);
            textfields.add(myNicknameTextField);
            textfields.add(myEmailTextField);
            textfields.add(myPasswordTextField);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public void editaccount(ActionEvent event) {
        boolean isBlank = false;
        boolean isexist = false;
        try{
            for (TextField field : textfields) {
                if(field.getText().isEmpty()){
                    isBlank = true;
                    break;
                }
            }
            if(!isBlank){
                ArrayList<Data> datas = d1.Read_data();
                for(Data data : datas){
                    if(data instanceof IndividualUser){
                        if(myNicknameTextField.getText().equals(((IndividualUser) data).getNickname())
                                || myEmailTextField.getText().equals(((IndividualUser) data).getEmail())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof InstutionalUser){
                        if(myNicknameTextField.getText().equals(((InstutionalUser) data).getNickname())
                                || myEmailTextField.getText().equals(((InstutionalUser) data).getEmail())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof AdminUser){
                        if(myNicknameTextField.getText().equals(((AdminUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                }
                d1.Clear_data();
                if(isexist){
                    for(Data data : datas) {
                        d1.Save_to_data(data);
                    }
                    throw new IllegalArgumentException();
                }
                else{
                    for(Data data : datas) {
                        if (data instanceof InstutionalUser) {
                            if (currentuser.getEmail().equals(((InstutionalUser) data).getEmail())) {
                                ((InstutionalUser) data).setInstitutionName(myInstituionalNameTextField.getText());
                                ((InstutionalUser) data).setIBANNumber(myIBANnoTextField.getText());
                                ((InstutionalUser) data).setExplanation(myExplanationTextField.getText());
                                ((InstutionalUser) data).setNickname(myNicknameTextField.getText());
                                ((InstutionalUser) data).setEmail(myEmailTextField.getText());
                                ((InstutionalUser) data).setPassword(myPasswordTextField.getText());
                            }
                        }
                        else if(data instanceof CurrentUser){
                            if(currentuser.getEmail().equals(((CurrentUser) data).getEmail())){
                                ((CurrentUser) data).setDatavar1(myInstituionalNameTextField.getText());
                                ((CurrentUser) data).setDatavar2(myIBANnoTextField.getText());
                                ((CurrentUser) data).setDatavar3(myExplanationTextField.getText());
                                ((CurrentUser) data).setNickname(myNicknameTextField.getText());
                                ((CurrentUser) data).setEmail(myEmailTextField.getText());
                                ((CurrentUser) data).setPassword(myPasswordTextField.getText());
                            }
                        }
                        d1.Save_to_data(data);
                    }
                    switchtomainscene2(event);
                }
            }
            else
                throw new NullPointerException();
        }
        catch(NullPointerException e){
            System.out.println("Alanlari bos birakmayiniz!");
        }
        catch(IllegalArgumentException e){
            System.out.println("Bu bilgilere sahip bir hesap var!");
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public void deleteaccount(ActionEvent event) {
        try{
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas) {
                if (data instanceof InstutionalUser) {
                    if(currentuser.getEmail().equals(((InstutionalUser) data).getEmail())){
                        d1.Delete_data(data, datas);
                        datas = d1.Read_data();
                        d1.Delete_data(currentuser, datas);
                        break;
                    }
                }
            }
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