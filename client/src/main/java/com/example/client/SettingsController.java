package com.example.client;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class SettingsController extends AppController<HelloApplication> {

    @FXML
    private ComboBox<String> languagePick;

    @FXML
    private Label languageText;

    @FXML
    private Label languageDesc;

    @Override
    public void startAppInternal(Object object) {

    }

    @Override
    public void startApp() {
        languagePick.getItems().addAll("Czech", "Russian", "English");
    }

    @FXML
    private void setLanguagePick(ActionEvent e){
        switch(languagePick.getValue()){
            case "Czech" -> mainApp.changeLanguage(Locale.of("cs", "CZ"));
            case "Russian" -> mainApp.changeLanguage(Locale.of("ru", "RU"));
            case "English" -> mainApp.changeLanguage(Locale.of("en", "US"));
        }
    }

    @FXML
    private void onOkClicked(ActionEvent e){
        ((Stage)((Button)e.getSource()).getScene().getWindow()).close();
    }

}
