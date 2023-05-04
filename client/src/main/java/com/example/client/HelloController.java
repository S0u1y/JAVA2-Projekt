package com.example.client;

import com.example.client.ClientEndPoints.DocumentClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class HelloController implements AppController{

    @FXML
    private GridPane documentsGrid;

    @Override
    public void startAppInternal(Object object) {

    }

    @Override
    public void startApp() {

        DocumentClient documentClient = EndPointProvider.getClient(DocumentClient.class);
        List<Document> documents = documentClient.getDocuments();

        documents.forEach(document -> {
            Button newBtn = new Button(document.getTitle());
            documentsGrid.add(newBtn, documentsGrid.getChildren().size()%4, documentsGrid.getChildren().size());
        });

    }
}