package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {

    public Map<Long, Document> documentMap = new HashMap<>();

    private HelloController controller;
    Scene scene;
    Stage stage;

    Locale locale;
    ResourceBundle resourceBundle;

    FXMLLoader fxmlLoader;

    public WindowValues SettingsWindow;
    public WindowValues page;


    @Override
    public void start(Stage stage) throws IOException {
        locale = new Locale("cs", "CZ");
        resourceBundle = ResourceBundle.getBundle("Texts", locale);

        fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("hello-view.fxml")), resourceBundle);

        scene = new Scene(fxmlLoader.load());
        this.stage = stage;

        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();
        controller.setMainApp(this);
        controller.startApp();

    }
    public static void main(String[] args) {
        launch();
    }

    public WindowValues openWindow(String name) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name), resourceBundle);

        Scene scene1 = new Scene(fxmlLoader.load());
        Stage stage1 = new Stage();

        stage1.setScene(scene1);

        stage1.show();

        AppController controller1 = fxmlLoader.getController();
        controller1.startApp();

        return new WindowValues(stage1, scene1, controller1);
    }
    public void changeWindow(Stage stage, String name){

    }

    public void changeLanguage(Locale locale){
        this.locale = locale;
        resourceBundle = ResourceBundle.getBundle("Texts", locale);

        fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("hello-view.fxml")), resourceBundle);
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.startApp();

            System.gc();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Locale getLocale() {
        return locale;
    }

    public record WindowValues(Stage stage, Scene scene, AppController controller) {}
}