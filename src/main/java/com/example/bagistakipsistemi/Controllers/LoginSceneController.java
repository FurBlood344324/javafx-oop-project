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

    // Girilen bilgileri doğrulayıp ona göre kullanıcının giriş yapmasını sağlar
    public void login(ActionEvent actionEvent) throws NullPointerException {
        boolean isLogined = false;
        boolean isBlank = false;
        try{
            AdminUser adminUser = new AdminUser();
            String nickname = myNicknameTextField.getText();
            String password = myPasswordTextField.getText();
            if(nickname == null || nickname.isEmpty() || password == null || password.isEmpty()){
                isBlank = true;
            }
            if(!isBlank){
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
                            isLogined = true;
                            showAlert("Başarılı", "Başarılı bir şekilde giriş yaptınız", Alert.AlertType.CONFIRMATION);
                            switchtomainscene2(actionEvent);
                        }
                    }
                }
            }
            else
                throw new NullPointerException();
            if(!isLogined)
                throw new IllegalArgumentException();
        }
        catch (NullPointerException e){
            showAlert("Hata", "Alanları doldurunuz!", Alert.AlertType.ERROR);
        }
        catch (IllegalArgumentException e){
            showAlert("Hata", "Bilgileriniz yanlış!", Alert.AlertType.ERROR);
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Ana Sayfa 1'e gönderir
    public void switchtomainscene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Ana Sayfa 2'ye gönderir
    public void switchtomainscene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Şifremi Unuttum sahnesine gönderir
    @FXML
    public void switchtoforgetpasswordscene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/ForgetPasswordScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Kayıt ol sahnesine gönderir
    @FXML
    public void switchtoregisterscene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/IndividualRegisterScene.fxml")));
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
