module fi.metropolia.mongodblocalization {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;


    opens fi.metropolia.mongodblocalization to javafx.fxml;
    exports fi.metropolia.mongodblocalization.controller;
    opens fi.metropolia.mongodblocalization.controller to javafx.fxml;
    exports fi.metropolia.mongodblocalization.view;
    opens fi.metropolia.mongodblocalization.view to javafx.fxml;
    opens fi.metropolia.mongodblocalization.model;
}
