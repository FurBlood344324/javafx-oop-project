package com.example.bagistakipsistemi;

import com.example.bagistakipsistemi.Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {
    private DatabaseManage d1 = new DatabaseManage();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BagisTakipSistemi");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            try{
                logout(stage);
            }
            catch(Exception e){
                System.out.println("Error");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    public void logout(Stage stage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cikis Ekrani");
        alert.setHeaderText("Cikis Yapmak Uzeresin!");
        alert.setContentText("Emin Misin ?");

        if(alert.showAndWait().get() == ButtonType.OK){
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas){
                if(data instanceof CurrentUser){
                    d1.Delete_data(data, datas);
                    break;
                }
            }
            stage.close();
        }
    }
}