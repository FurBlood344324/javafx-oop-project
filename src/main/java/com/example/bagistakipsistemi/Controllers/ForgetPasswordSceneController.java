package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    // Kullanıcıdan alınan bilgileri doğrulayıp ona göre şifreyi günceller
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
            showAlert("Başarılı", "Şirenizi başarılı bir şekilde değiştirdiniz", Alert.AlertType.CONFIRMATION);
            switchtologinscene(event);
        }
        catch(NullPointerException e){
            showAlert("Hata", "Alanları doldurunuz!", Alert.AlertType.ERROR);
        }
        catch(IllegalArgumentException e){
            showAlert("Hata", "Kullanıcı bulunamadı!", Alert.AlertType.ERROR);
        }
        catch (RuntimeException e) {
            showAlert("Hata", "Sifreler ayni degil!", Alert.AlertType.ERROR);
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Giriş yap sahnesine gönderir
    public void switchtologinscene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/com/example/bagistakipsistemi/LoginScene.fxml")));
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
}
