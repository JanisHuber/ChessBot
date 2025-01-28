module org.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports org.example.chess.figures; //custom export JUnit-Tests

    opens org.example.chess to javafx.fxml;
    exports org.example.chess;
}