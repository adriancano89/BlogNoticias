module org.mongo.blognoticias {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.desktop;

    opens org.mongo.blognoticias to javafx.fxml;
    opens org.mongo.blognoticias.Controllers to javafx.fxml;
    exports org.mongo.blognoticias;
}