import java.util.ArrayList;
import java.util.Scanner;

public class ChessMain {
    public int i;
    public int j;

    public static void main(String[] args) {
        Init.Init();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        boolean isPlayer = false;

        while (gameRunning) {
            printBoard();
            char piece;
            if (isPlayer) {
                System.out.println("Player Move");
                if (isInCheck(true))
                {
                    System.out.println("King is under Check!");
                    piece = 'K';
                    ChessMain chessMain = new ChessMain();
                    System.out.println(chessMain.i + " " + chessMain.j);
                    playerMove.getPlayerMove(piece, chessMain.i, chessMain.j, true, true);
                }
                else { //PlayerMove
                    System.out.println("Choose a piece (piece, y, x): ");
                    piece = scanner.next().charAt(0);
                    int y = scanner.nextInt();
                    int x = scanner.nextInt();

                    byte gameRunningByte = playerMove.getPlayerMove(piece, y, x, false, true);
                    if (gameRunningByte == 2) {
                        isPlayer = true;
                        continue;
                    } else if (gameRunningByte == 1) {
                        System.out.println("Checkmate!");
                        gameRunning = false;
                    }
                    else {
                        //isPlayer = false;
                    }
                }
            } else { //getBotMove
                System.out.println("Bot Move");
                if (isInCheck(false))
                {
                    ChessMain chessMain = new ChessMain();
                    System.out.println(chessMain.i + " " + chessMain.j);
                    piece = 'K';
                    playerMove.getPlayerMove(piece, chessMain.i, chessMain.j, true, false);
                }
                else { //PlayerMove
                    System.out.println("Choose a piece (piece, y, x): ");
                    piece = scanner.next().charAt(0);
                    int y = scanner.nextInt();
                    int x = scanner.nextInt();

                    byte gameRunningByte = playerMove.getPlayerMove(piece, y, x, false, false);
                    if (gameRunningByte == 2) {
                        isPlayer = true;
                        continue;
                    } else if (gameRunningByte == 1) {
                        System.out.println("Checkmate!");
                        gameRunning = false;
                    }
                    else {
                        //isPlayer = false;
                    }
                }
            }
        }
        scanner.close();
    }

    private static void printBoard() {
        Init.printBoard(ChessBoard.chessBoard);
    }

    public static boolean isInCheck(boolean isPlayer) {
        int kingId;
        if (isPlayer) {
            kingId = 2;
        } else {
            kingId = 1;
        }
        ChessMain chessMain = new ChessMain();
        for (chessMain.i = 0; chessMain.i < 8; chessMain.i++) {
            for (chessMain.j = 0; chessMain.j < 8; chessMain.j++) {
                if (ChessBoard.chessBoard[chessMain.i][chessMain.j] == 'K' && Init.kingPositions.get(chessMain.i + "," + chessMain.j) == kingId) {
                    if (CheckKing.isInCheck(chessMain.i, chessMain.j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}