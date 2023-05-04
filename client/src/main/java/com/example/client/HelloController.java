package com.example.client;

import com.example.client.ClientEndPoints.DocumentClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class HelloController implements AppController{

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
        });

    }

    @FXML
    private void onItemSelected(ActionEvent e){
        int id = Integer.parseInt(((Button)(e.getTarget())).getId());
        System.out.println(id);

    }

}


