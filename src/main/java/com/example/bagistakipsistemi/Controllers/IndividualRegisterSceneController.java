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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class IndividualRegisterSceneController {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myNameTextField, mySurnameTextField, myTelnoTextField,
            myNicknameTextField, myEmailTextField, myPasswordTextField;

    @FXML
    private Button myRegisterButton, myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void onRegisterButtonClicked(ActionEvent actionEvent) {
        boolean isBlank = false;
        textfields.add(myNameTextField);
        textfields.add(mySurnameTextField);
        textfields.add(myTelnoTextField);
        textfields.add(myNicknameTextField);
        textfields.add(myEmailTextField);
        textfields.add(myPasswordTextField);
        try{
            IndividualUser user = new IndividualUser(myNameTextField.getText(), mySurnameTextField.getText(),
                    Integer.parseInt(myTelnoTextField.getText()), myNicknameTextField.getText(), myEmailTextField.getText(),
                    myPasswordTextField.getText());
            for (TextField field : textfields) {
                if(field.getText().isEmpty()){
                    isBlank = true;
                    break;
                }
            }
            if(!isBlank){
                d1.Save_to_data(user);
                switchtomainscene1(actionEvent);
            }
            else
                throw new NullPointerException();
        }
        catch (NumberFormatException e){
            System.out.println("Telefon Numarasini dogru bir sekilde giriniz!");
        }
        catch (NullPointerException e){
            System.out.println("Alanlari bos birakmayiniz!");
        }
        catch (Exception e){
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
}
