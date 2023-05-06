module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.cxf.rs.client;
//    pain.
    requires com.fasterxml.jackson.module.jakarta.xmlbind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.jakarta.rs.json;
    requires jakarta.xml.bind;

    requires jakarta.ws.rs;
    requires lombok;

    requires java.money;

    requires org.javamoney.moneta;
    requires org.javamoney.moneta.convert;

    requires java.xml;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
}