package com.example.client;

public abstract class AppController<T> {

    T mainApp;

    public abstract void startAppInternal(Object object);
    public abstract void startApp();
    void setMainApp(T app){
        mainApp = app;
    }
}
