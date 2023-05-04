package com.example.client;

import com.example.client.ClientEndPoints.DocumentClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private HelloController controller;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        scene = new Scene(fxmlLoader.load());
        this.stage = stage;

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();
        controller.startApp();
        controller.setMainApp(this);
    }

    public void openWindow(String name) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name));

        Scene scene1 = new Scene(fxmlLoader.load());
        Stage stage1 = new Stage();

        stage1.setScene(scene1);

        stage1.show();
    }
    public void changeWindow(String name){

    }

    public static void main(String[] args) {



        launch();
    }



}