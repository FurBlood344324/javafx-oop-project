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

public class AdminUserAccountSceneController implements Initializable {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myNicknameTextField, myPasswordTextField;

    @FXML
    private Button myEditButton, myDeleteButton, myBackButton;

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
            myNicknameTextField.setText(currentuser.getNickname());
            myPasswordTextField.setText(currentuser.getPassword());
            textfields.add(myNicknameTextField);
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
                        if(myNicknameTextField.getText().equals(((IndividualUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof InstutionalUser){
                        if(myNicknameTextField.getText().equals(((InstutionalUser) data).getNickname())){
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
                        if (data instanceof AdminUser) {
                            if (currentuser.getNickname().equals(((AdminUser) data).getNickname())) {
                                ((AdminUser) data).setNickname(myNicknameTextField.getText());
                                ((AdminUser) data).setPassword(myPasswordTextField.getText());
                            }
                        }
                        else if(data instanceof CurrentUser){
                            if(currentuser.getNickname().equals(((CurrentUser) data).getNickname())){
                                ((CurrentUser) data).setNickname(myNicknameTextField.getText());
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
                if (data instanceof AdminUser) {
                    if(currentuser.getNickname().equals(((AdminUser) data).getNickname())){
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
}