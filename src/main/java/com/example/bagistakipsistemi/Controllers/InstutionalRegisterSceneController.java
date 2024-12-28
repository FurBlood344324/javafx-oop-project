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

public class InstutionalRegisterSceneController {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myInstitutionNameTextField, myIbannoTextField, myExplanationTextField, myNicknameTextField,
            myEmailTextField, myPasswordTextField;

    @FXML
    private Button myRegisterButton, myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    // Kullanıcıdan alınan bilgilere göre yeni bir hesap oluşturur ve bunu veritabanına kaydeder
    public void onRegisterButtonClicked(ActionEvent actionEvent) {
        boolean isBlank = false;
        boolean isexist = false;
        textfields.add(myInstitutionNameTextField);
        textfields.add(myIbannoTextField);
        textfields.add(myExplanationTextField);
        textfields.add(myNicknameTextField);
        textfields.add(myEmailTextField);
        textfields.add(myPasswordTextField);
        try{
            InstutionalUser user = new InstutionalUser(myInstitutionNameTextField.getText(), myIbannoTextField.getText(),
                    myExplanationTextField.getText(), myNicknameTextField.getText(), myEmailTextField.getText(),
                    myPasswordTextField.getText());
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
                        if(user.getEmail().equals(((IndividualUser) data).getEmail())
                                || user.getNickname().equals(((IndividualUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof InstutionalUser){
                        if(user.getEmail().equals(((InstutionalUser) data).getEmail())
                                || user.getNickname().equals(((InstutionalUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof AdminUser){
                        if(user.getNickname().equals(((AdminUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                }
            }
            else
                throw new NullPointerException();
            if(isexist){
                throw new IllegalArgumentException();
            }
            d1.Save_to_data(user);
            showAlert("Başarılı", "Başarılı bir şekilde kaydoldunuz", Alert.AlertType.CONFIRMATION);
            switchtologinscene(actionEvent);
        }
        catch (NullPointerException e){
            showAlert("Hata", "Alanlari bos birakmayiniz!", Alert.AlertType.ERROR);
        }
        catch (IllegalArgumentException e){
            showAlert("Hata", "Bu bilgilere sahip bir hesap var!", Alert.AlertType.ERROR);
        }
        catch (Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Ana Sayfa 1'e gönderir
    public void switchtomainscene1(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Giriş Yap sahnesine gönderir
    public void switchtologinscene(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/LoginScene.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
