import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ChessMainTest {
    public void setUp() {
        Init.Init();
        boolean gameRunning = true;
        boolean isPlayer = false;
    }

    @Test
    public void Bot(){
        setUp();
        for (int i = 0; i < 5; i++){
            getBotMove.tryMoves(5);
            Init.printBoard(ChessBoard.chessBoard);
            Move bestMoveWhite = getBotMove.getBestMove("white");
            MakeMove.MakeMove(bestMoveWhite.startY, bestMoveWhite.startX, bestMoveWhite.endY, bestMoveWhite.endX, ChessBoard.chessBoard[bestMoveWhite.startY][bestMoveWhite.startX], 0);
            Init.printBoard(ChessBoard.chessBoard);
            assertTrue(evalBoard.evalBoard(ChessBoard.chessBoard) > 0, "Der Bot sollte nicht verlieren");
        }
    }

    @Test
    public void mate() {
        setUp();

        MakeMove.MakeMove(1, 4, 3, 4, 'P', 0);
        MakeMove.MakeMove(0, 3, 4, 7, 'Q', 0);
        MakeMove.MakeMove(4, 7, 6, 5, 'Q', 1);
        MakeMove.MakeMove(6, 5, 7, 5, 'Q', 0);

        assertTrue(ChessMain.isInCheck(true), "Der König sollte im Schach sein");
    }

    @Test
    public void notCheckmate() {
        setUp();

        MakeMove.MakeMove(1, 4, 3, 4, 'P', 0);
        MakeMove.MakeMove(0, 3, 4, 7, 'Q', 0);
        MakeMove.MakeMove(4, 7, 6, 5, 'Q', 1);
        MakeMove.MakeMove(6, 5, 7, 5, 'Q', 0);

        assertFalse(CheckKing.isCheckmate(7, 4), "Der König sollte nicht im Schachmatt sein");
    }

    @Test
    public void checkmate() {
        setUp();

        MakeMove.MakeMove(1, 4, 3, 4, 'P', 0);
        MakeMove.MakeMove(0, 3, 4, 7, 'Q', 0);
        MakeMove.MakeMove(1, 4, 3, 4, 'P', 0);
        MakeMove.MakeMove(0, 5, 3, 2, 'B', 0);
        MakeMove.MakeMove(4, 7, 6, 5, 'Q', 1);

        assertTrue(CheckKing.isCheckmate(7, 4), "Der König sollte im Schachmatt sein");
    }
}