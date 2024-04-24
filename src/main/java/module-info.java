module io.myjournal.myjournal {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.myjournal to javafx.fxml;
    exports io.myjournal;
}