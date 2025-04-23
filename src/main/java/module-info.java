module org.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports org.example.chess.backend.figures; //custom export JUnit-Tests

    opens org.example.chess to javafx.fxml;
    exports org.example.chess.backend.enums;
    opens org.example.chess.backend.enums to javafx.fxml;
    exports org.example.chess.backend.util;
    opens org.example.chess.backend.util to javafx.fxml;
    exports org.example.chess.frontend.application;
    opens org.example.chess.frontend.application to javafx.fxml;
    exports org.example.chess.backend.controller;
    opens org.example.chess.backend.controller to javafx.fxml;
    exports org.example.chess.backend.bot;
    opens org.example.chess.backend.bot to javafx.fxml;
    exports org.example.chess.backend.board;
    opens org.example.chess.backend.board to javafx.fxml;
    exports org.example.chess.backend.evaluate;
    opens org.example.chess.backend.evaluate to javafx.fxml;
}