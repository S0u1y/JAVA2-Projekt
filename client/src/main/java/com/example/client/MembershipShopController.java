package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import java.text.NumberFormat;


public class MembershipShopController extends AppController<HelloApplication> {

    private Money basePrice = new Money(Money.czk, 317);

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

    @FXML
    private void onLengthPick(){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(mainApp.getLocale());

        MonetaryAmount amount = basePrice.convert(mainApp.getLocale());

        switch (lengthPick.getValue()) {
            case 1 -> price.setText(numberFormat.format(amount.getNumber()));
            case 2 -> price.setText((numberFormat.format(amount.add(amount).multiply(0.7).getNumber())));
            case 6 -> price.setText((numberFormat.format(amount.multiply(6).multiply(0.7).getNumber())));
            case 12 -> price.setText((numberFormat.format(amount.multiply(12).multiply(0.65).getNumber())));
        }
    }

}
