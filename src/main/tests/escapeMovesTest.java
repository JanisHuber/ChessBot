import javafx.application.Platform;

import org.example.chess.backend.board.Field;
import org.example.chess.backend.controller.ChessController;
import org.example.chess.backend.enums.FigureColor;
import org.example.chess.backend.figures.Pawn;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class escapeMovesTest {

    @Test
    void canEscape() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        //start Javafx-Thread
        Platform.startup(() -> {
            try {
                ChessController chessController = new ChessController(false);

                Field f = chessController.chessBoard.getField("E", 2);
                f.setFigure(new Pawn());
                f.getFigure().figureColor = FigureColor.BLACK;
                f.getFigure().position = f;
                f.figure.chessBoard = chessController.chessBoard;

                Field targetField = chessController.chessBoard.getField("D", 1);
                int counter = 1;

                assertEquals(1, counter, "King should be checked");

                List<Field> moves = chessController.getCheckedMove(targetField.figure);

                assertEquals(1, moves.size(), "King should have 1 escape move");
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}
