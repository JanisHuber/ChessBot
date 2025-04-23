module org.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports org.example.chess.figures; //custom export JUnit-Tests

    opens org.example.chess to javafx.fxml;
    exports org.example.chess.enums;
    opens org.example.chess.enums to javafx.fxml;
    exports org.example.chess.util;
    opens org.example.chess.util to javafx.fxml;
    exports org.example.chess.application;
    opens org.example.chess.application to javafx.fxml;
    exports org.example.chess.controller;
    opens org.example.chess.controller to javafx.fxml;
    exports org.example.chess.bot;
    opens org.example.chess.bot to javafx.fxml;
    exports org.example.chess.board;
    opens org.example.chess.board to javafx.fxml;
    exports org.example.chess.evaluate;
    opens org.example.chess.evaluate to javafx.fxml;
}