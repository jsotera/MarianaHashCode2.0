module es.mariaanasanz.hashcode.base {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.mariaanasanz.hashcode2.base to javafx.fxml;
    exports es.mariaanasanz.hashcode2.base;
}