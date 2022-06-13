module com.group10.itahdeerx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.group10.itahdeerx to javafx.fxml;
    exports com.group10.itahdeerx;
}