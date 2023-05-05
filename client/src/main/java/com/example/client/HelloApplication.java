package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    private ArrayList openedWindows = new ArrayList<String>();

    private HelloController controller;
    Scene scene;
    Stage stage;

    Locale locale;
    ResourceBundle resourceBundle;

    FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        locale = new Locale("cs");
        resourceBundle = ResourceBundle.getBundle("Texts", locale);

        fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("hello-view.fxml")), resourceBundle);

        scene = new Scene(fxmlLoader.load());
        this.stage = stage;

        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();
        controller.startApp();
        controller.setMainApp(this);
    }
    public static void main(String[] args) {
        launch();
    }

    public WindowValues openWindow(String name) throws IOException{
        if(openedWindows.contains(name)){
            return null;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name), resourceBundle);

        Scene scene1 = new Scene(fxmlLoader.load());
        Stage stage1 = new Stage();

        stage1.setScene(scene1);

        stage1.show();

        AppController controller1 = fxmlLoader.getController();
        controller1.startApp();



        return new WindowValues(stage1, scene1, controller1);
    }
    public void changeWindow(String name){

    }

    public void changeLanguage(String language){
        locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("Texts", locale);

        fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("hello-view.fxml")), resourceBundle);
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            controller = fxmlLoader.getController();
            controller.startApp();
            controller.setMainApp(this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public record WindowValues(Stage stage, Scene scene, AppController controller) {}
}