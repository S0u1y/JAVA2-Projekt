package com.example.client;

import com.example.client.ClientEndPoints.DocumentClient;
import com.example.client.ClientEndPoints.EndPointProvider;
import com.example.client.ClientEndPoints.PageClient;
import jakarta.ws.rs.InternalServerErrorException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HelloController extends AppController<HelloApplication>{

    private static final Logger log = LogManager.getLogger(HelloController.class);

    @FXML
    private GridPane documentsGrid;
    @FXML
    private Button tempButton;

    @Override
    public void startAppInternal(Object object) {

    }

    @Override
    public void startApp() {

        if(mainApp.documentMap.isEmpty()){
            CompletableFuture<Void> getDocuments = CompletableFuture.runAsync(() -> {
                DocumentClient documentClient = EndPointProvider.getClient(DocumentClient.class);
                List<Document> documents = documentClient.getDocuments();
                mainApp.documentMap.putAll(documents.stream().collect(Collectors.toMap(Document::getId, o->o)));
            });
            getDocuments.join();
        }


        populateGrid();


    }

    private void populateGrid(){
        mainApp.documentMap.forEach((id, document) -> {
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

    private void onItemSelected(ActionEvent e){
        Button selected = (Button)(e.getTarget());
        Long id = Long.parseLong(selected.getId());
        System.out.println(id);
        try{
            if(mainApp.page != null) return;

            HelloApplication.WindowValues objects = mainApp.openWindow("page-view.fxml");
            mainApp.page = objects;

            if(objects == null) return;

            objects.stage().setTitle(selected.getText());
            Page firstPage = EndPointProvider.getClient(PageClient.class).getPage(id, 1);
            ((PageController)objects.controller()).setPage(firstPage);
            objects.controller().setMainApp(mainApp);

            objects.stage().setOnCloseRequest(windowEvent -> {
                mainApp.page = null; //doesn't this cause memory leaks?
            });



        }catch (InternalServerErrorException ex){
            if(ex.getMessage().contains("HTTP 5")){
                if(mainApp.page != null){
                    ((PageController)mainApp.page.controller()).setPageText("Internal Server error!\nPage might have not been found.");
                }
            }else{
                log.info(ex.getMessage());
                throw new InternalServerErrorException(ex);
            }
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if(mainApp.page != null){
            mainApp.page.stage().setOnCloseRequest(windowEvent -> {
                mainApp.page = null; //doesn't this cause memory leaks?
            });
        }
    }

    @FXML
    private void onBuyMembershipClicked(){
        System.out.println("Open Membership shop");
        try {
            HelloApplication.WindowValues objects = mainApp.openWindow("membership-shop-view.fxml");
            if(objects == null) return;

            objects.stage().setTitle("Membership Shop");
            objects.controller().setMainApp(mainApp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onSettingsClicked(){
        System.out.println("Open Settings menu");
        try {
            if(mainApp.SettingsWindow != null) return; //only one settings window should be open..

            HelloApplication.WindowValues objects = mainApp.openWindow("settings-view.fxml");
            mainApp.SettingsWindow = objects;

            if(objects == null) return;

            objects.stage().setTitle("Settings");
            objects.controller().setMainApp(mainApp);

            objects.stage().setOnCloseRequest(windowEvent -> {
                mainApp.SettingsWindow = null; //doesn't this cause memory leaks?
            });



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


