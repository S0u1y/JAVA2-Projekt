package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.ResourceBundle;

public class MembershipShopController extends AppController<HelloApplication> {

    @FXML
    private ComboBox<Integer> lengthPick;

    @FXML
    private Label membershipLabel;
    @FXML
    private Label membershipUntilText;
    @FXML
    private Label membershipUntilDate;
    @FXML
    private Label priceText;
    @FXML
    private Label price;

    @FXML
    private Button buyButton;
    @FXML
    private Button cancelButton;

    @Override
    public void startAppInternal(Object object) {
    }

    @Override
    public void startApp() {
        lengthPick.getItems().addAll(1, 2, 6, 12);

    }

    @FXML
    private void onBuyClicked(){

    }
    @FXML
    private void onCancelClicked(ActionEvent e){
        ((Stage)((Button)e.getSource()).getScene().getWindow()).close();
    }

}
