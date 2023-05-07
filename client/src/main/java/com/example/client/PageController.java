package com.example.client;

import com.example.client.ClientEndPoints.EndPointProvider;
import com.example.client.ClientEndPoints.PageClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PageController extends AppController<HelloApplication>{

    private Page page;
    private int maxPages;

    @FXML
    private Text pageText;

    @FXML
    private Button next;
    @FXML
    private Button previous;
    @FXML
    private Button back;

    @Override
    public void startAppInternal(Object object) {

    }

    @Override
    public void startApp() {

    }

    public void setPage(Page page) {
        this.page = page;
        this.pageText.setText(page.getContent());
        this.maxPages = EndPointProvider.getClient(PageClient.class).getNumberOfPages(page.getDocument().getId());
        handleswitchButtonsVisibility();
    }

    @FXML
    private void onBackClicked(){

    }
    @FXML
    private void onPreviousClicked(){
        this.page = EndPointProvider.getClient(PageClient.class).getPage(page.getDocument().getId(), page.getPage_number() - 1);
        this.pageText.setText(page.getContent());
        handleswitchButtonsVisibility();
    }
    @FXML
    private void onNextClicked(){
        this.page = EndPointProvider.getClient(PageClient.class).getPage(page.getDocument().getId(), page.getPage_number() + 1);
        this.pageText.setText(page.getContent());
        handleswitchButtonsVisibility();
    }

    private void handleswitchButtonsVisibility(){
        next.setVisible(this.page.getPage_number() != this.maxPages);
        previous.setVisible(this.page.getPage_number() != 1);
    }

}
