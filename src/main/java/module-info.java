module io.myjournal {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.myjournal to javafx.fxml;
    exports io.myjournal to javafx.graphics;
    exports io.myjournal.controllers to javafx.graphics;
    opens io.myjournal.controllers to javafx.fxml;
}