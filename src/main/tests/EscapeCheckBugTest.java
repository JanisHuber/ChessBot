import javafx.application.Platform;
import org.example.chess.ChessController;
import org.example.chess.Field;
import org.example.chess.FigureColor;
import org.example.chess.Move;
import org.example.chess.figures.King;
import org.example.chess.figures.Queen;
import org.example.chess.figures.Rook;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EscapeCheckBugTest {
    @Test
    void canEscape() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        //start Javafx-Thread
        Platform.startup(() -> {
            try {
                ChessController chessController = new ChessController(false);
                chessController.newGame();
                clearField(chessController);

                Field f = chessController.chessBoard.getField("E", 2);
                f.setFigure(new Rook());
                f.getFigure().figureColor = FigureColor.BLACK;
                f.getFigure().position = f;
                f.figure.chessBoard = chessController.chessBoard;

                Field f3 = chessController.chessBoard.getField("D", 2);
                f3.setFigure(new Rook());
                f3.getFigure().figureColor = FigureColor.BLACK;
                f3.getFigure().position = f3;
                f3.figure.chessBoard = chessController.chessBoard;

                Field f4 = chessController.chessBoard.getField("F", 2);
                f4.setFigure(new Rook());
                f4.getFigure().figureColor = FigureColor.BLACK;
                f4.getFigure().position = f4;
                f4.figure.chessBoard = chessController.chessBoard;


                Field f2 = chessController.chessBoard.getField("E", 8);
                f2.setFigure(new King());
                f2.getFigure().figureColor = FigureColor.WHITE;
                f2.getFigure().position = f2;
                f2.figure.chessBoard = chessController.chessBoard;

                chessController.currentTurn = FigureColor.WHITE;

                assertEquals(null, chessController.getCheckedMove(f2.getFigure()));
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    public void clearField(ChessController chessController) {
        for (Field field : chessController.chessBoard.getFields()) {
            if (field.figure != null) {
                field.figure.position = null;
                field.figure = null;
                field.setFigure(null);
            }
        }
    }
}
