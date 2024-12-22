package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginSceneController {
    @FXML
    private Label myTitleLabel, myForgetPasswordLabel, myLabel1, myRegisterLabel;

    @FXML
    private TextField myNicknameTextField, myPasswordTextField;

    @FXML
    private Button myLoginButton, myBackButton;

    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void login(ActionEvent actionEvent) throws NullPointerException {
        boolean isLogined = false;
        try{
            AdminUser adminUser = new AdminUser();
            String nickname = myNicknameTextField.getText();
            String password = myPasswordTextField.getText();
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas){
                if(data instanceof IndividualUser){
                    if(((User) data).getNickname().equals(nickname) && ((User) data).getPassword().equals(password)){
                        CurrentUser currentUser = new CurrentUser(((IndividualUser) data).getName(), ((IndividualUser) data).getSurname(),
                                Integer.toString(((IndividualUser) data).getTelephonenumber()),((IndividualUser) data).getNickname(),
                                ((IndividualUser) data).getEmail(), ((IndividualUser) data).getPassword(), ((IndividualUser) data).getDataType());
                        d1.Save_to_data(currentUser);
                        switchtomainscene2(actionEvent);
                        isLogined = true;
                    }
                }
                else if(data instanceof InstutionalUser){
                    if(((User) data).getNickname().equals(nickname) && ((User) data).getPassword().equals(password)){
                        CurrentUser currentUser = new CurrentUser(((InstutionalUser) data).getInstitutionName(), ((InstutionalUser) data).getIBANNumber(),
                                ((InstutionalUser) data).getExplanation(),((InstutionalUser) data).getNickname(),
                                ((InstutionalUser) data).getEmail(), ((InstutionalUser) data).getPassword(), ((InstutionalUser) data).getDataType());
                        d1.Save_to_data(currentUser);
                        switchtomainscene2(actionEvent);
                        isLogined = true;
                    }
                }
                else if(data instanceof AdminUser){
                    if(((AdminUser) data).getNickname().equals(nickname) && ((AdminUser) data).getPassword().equals(password)){
                        CurrentUser currentUser = new CurrentUser("0", "0", "0", ((AdminUser) data).getNickname(),
                                "0", ((AdminUser) data).getPassword(), ((AdminUser) data).getDataType());
                        d1.Save_to_data(currentUser);
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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtomainscene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtoforgetpasswordscene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/ForgetPasswordScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
