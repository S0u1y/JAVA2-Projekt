package com.example.client;

import com.example.client.ClientEndPoints.DocumentClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class HelloController implements AppController{

    private HelloApplication mainApp;

    @FXML
    private GridPane documentsGrid;
    @FXML
    private Button tempButton;

    @Override
    public void startAppInternal(Object object) {

    }

    @Override
    public void startApp() {

        DocumentClient documentClient = EndPointProvider.getClient(DocumentClient.class);
        List<Document> documents = documentClient.getDocuments();

        documents.forEach(document -> {
            Button newBtn = new Button(document.getTitle());

            GridPane.setMargin(newBtn, GridPane.getMargin(tempButton));//quite interesting...

            newBtn.setMaxWidth(tempButton.getMaxWidth());
            newBtn.setMaxHeight(tempButton.getMaxHeight());
            newBtn.setId(String.valueOf(document.getId()));
            documentsGrid.add(newBtn, documentsGrid.getChildren().size()-1, ((documentsGrid.getChildren().size()-1)/4)%4);
            newBtn.setVisible(true);
            newBtn.setOnAction(this::onItemSelected);
        });

    }

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    private void onItemSelected(ActionEvent e){
        int id = Integer.parseInt(((Button)(e.getTarget())).getId());
        System.out.println(id);

    }

    @FXML
    private void onBuyMembershipClicked(){
        System.out.println("Open Membership shop");
    }

    @FXML
    private void onSettingsClicked(){
        System.out.println("Open Settings menu");
        try {
            mainApp.openWindow("settings-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


