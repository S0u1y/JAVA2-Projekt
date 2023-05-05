package com.example.client;

public abstract class AppController<T> {

    T mainApp;

    abstract void startAppInternal(Object object);
    abstract void startApp();
    void setMainApp(T app){
        mainApp = app;
    }
}
