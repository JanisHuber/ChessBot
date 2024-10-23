import java.util.HashMap;

//P White= 1-8 Black = 9-16
//R White= 1-2 Black = 3-4
//N White= 1-2 Black = 3-4
//B White= 1-2 Black = 3-4
//Q White= 1 Black = 2
//K White= 1 Black = 2

public class Init {
    static HashMap<String, Integer> pawnPositions = new HashMap<>();
    static int pawnCounter = 1;
    static HashMap<String, Integer> rookPositions = new HashMap<>();
    static int rookCounter = 1;
    static HashMap<String, Integer> knightPositions = new HashMap<>();
    static int knightCounter = 1;
    static HashMap<String, Integer> bishopPositions = new HashMap<>();
    static int bishopCounter = 1;
    static HashMap<String, Integer> queenPositions = new HashMap<>();
    static int queenCounter = 1;
    static HashMap<String, Integer> kingPositions = new HashMap<>();
    static int kingCounter = 1;

    public static void Init() {
        for (int y = 0; y < 8; y++) {
            ChessBoard.chessBoard[1][y] = 'P';
            pawnPositions.put("1," + y, pawnCounter++);
        }
        for (int y = 0; y < 8; y++) {
            ChessBoard.chessBoard[6][y] = 'P';
            pawnPositions.put("6," + y, pawnCounter++);
        }
        for (int y = 0; y < 8; y++) {
            ChessBoard.chessBoard[0][y] = 'R';
            rookPositions.put("0," + y, rookCounter++);
        }
        for (int y = 0; y < 8; y++) {
            ChessBoard.chessBoard[7][y] = 'R';
            rookPositions.put("7," + y, rookCounter++);
        }
        for (int y = 1; y < 7; y += 5) {
            ChessBoard.chessBoard[0][y] = 'N';
            knightPositions.put("0," + y, knightCounter++);
        }
        for (int y = 1; y < 7; y += 5) {
            ChessBoard.chessBoard[7][y] = 'N';
            knightPositions.put("7," + y, knightCounter++);
        }
        for (int y = 2; y < 6; y += 3) {
            ChessBoard.chessBoard[0][y] = 'B';
            bishopPositions.put("0," + y, bishopCounter++);
        }
        for (int y = 2; y < 6; y += 3) {
            ChessBoard.chessBoard[7][y] = 'B';
            bishopPositions.put("7," + y, bishopCounter++);
        }

        ChessBoard.chessBoard[0][3] = 'Q';
        queenPositions.put("0,3", queenCounter++);
        ChessBoard.chessBoard[7][3] = 'Q';
        queenPositions.put("7,3", queenCounter++);
        ChessBoard.chessBoard[0][4] = 'K';
        kingPositions.put("0,4", kingCounter++);
        ChessBoard.chessBoard[7][4] = 'K';
        kingPositions.put("7,4", kingCounter++);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (ChessBoard.chessBoard[x][y] == '\u0000') {
                    ChessBoard.chessBoard[x][y] = '-';
                }
            }
        }

        printBoard(ChessBoard.chessBoard);
    }

    public static void printBoard(char[][] board) {
        //System.out.println("  0 1 2 3 4 5 6 7");
        for (int y = 0; y < 8; y++) {
            System.out.print(" ");
            for (int x = 0; x < 8; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println((y));
        }
        System.out.println(" 0 1 2 3 4 5 6 7");
    }
}