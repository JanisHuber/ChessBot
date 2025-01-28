import javafx.application.Platform;
import org.example.chess.ChessBoard;
import org.example.chess.ChessController;
import org.example.chess.Field;
import org.example.chess.FigureColor;
import org.example.chess.figures.Bishop;
import org.example.chess.figures.Pawn;
import org.example.chess.figures.Queen;
import org.example.chess.figures.Rook;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;


public class PossibleMovesTest {
    @Test
    void allPossibleMoves() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        //start Javafx-Thread
        Platform.startup(() -> {
            try {
                ChessController chessController = new ChessController();

                Field f = chessController.chessBoard.getField("D", 4);
                f.setFigure(new Queen());
                f.getFigure().figureColor = FigureColor.WHITE;
                f.getFigure().position = f;
                f.figure.chessBoard = chessController.chessBoard;

                List<Field> possibleMoves = f.getFigure().getPossibleMoves();
                assertEquals(19, possibleMoves.size(), "Queen should have 19 possible moves");

                f.setFigure(new Rook());
                f.getFigure().figureColor = FigureColor.WHITE;
                f.getFigure().position = f;
                f.figure.chessBoard = chessController.chessBoard;

                List<Field> possibleMoves2 = f.getFigure().getPossibleMoves();
                assertEquals(11, possibleMoves2.size(), "Rook should have 11 possible moves");

                f.setFigure(new Bishop());
                f.getFigure().figureColor = FigureColor.WHITE;
                f.getFigure().position = f;
                f.figure.chessBoard = chessController.chessBoard;

                List<Field> possibleMoves3 = f.getFigure().getPossibleMoves();
                assertEquals(8, possibleMoves3.size(), "Bishop should have 8 possible moves");

            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}
