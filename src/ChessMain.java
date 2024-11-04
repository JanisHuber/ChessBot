import java.util.ArrayList;
import java.util.Scanner;

public class ChessMain {
    public static void main(String[] args) {
        Init.Init();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard();
            System.out.println("Choose a piece (piece, y, x): ");
            char piece = scanner.next().charAt(0);
            int y = scanner.nextInt();
            int x = scanner.nextInt();

            ArrayList<int[]> moves = ChessPossibleMoves.Moves(piece, y, x);
            printPossibleMoves(moves);

            System.out.println("Choose a tile (y, x): ");
            int nextY = scanner.nextInt();
            int nextX = scanner.nextInt();

            if (isValidMove(moves, nextY, nextX)) {
                MakeMove.MakeMove(y, x, nextY, nextX, piece, getMoveSituation(nextY, nextX));
                if (isCheckmate()) {
                    System.out.println("Checkmate!");
                    gameRunning = false;
                } else if (isInCheck()) {
                    System.out.println("Check!");
                }
            } else {
                System.out.println("Invalid Move!");
            }
        }
        scanner.close();
    }

    private static void printBoard() {
        Init.printBoard(ChessBoard.chessBoard);
    }

    private static void printPossibleMoves(ArrayList<int[]> moves) {
        char[][] tempChessBoard = new char[8][8];
        for (int i = 0; i < ChessBoard.chessBoard.length; i++) {
            tempChessBoard[i] = ChessBoard.chessBoard[i].clone();
        }

        for (int[] move : moves) {
            if (move.length < 3) {
                System.out.println("ERR");
                continue;
            }
            int situation = move[2];
            if (situation == 0) {
                tempChessBoard[move[0]][move[1]] = 'M';
            } else if (situation == 1) {
                tempChessBoard[move[0]][move[1]] = 'X';
            } else if (situation == 2) {
                tempChessBoard[move[0]][move[1]] = '#';
            }
        }
        Init.printBoard(tempChessBoard);
    }

    private static boolean isValidMove(ArrayList<int[]> moves, int nextY, int nextX) {
        for (int[] move : moves) {
            if (nextY == move[0] && nextX == move[1]) {
                return true;
            }
        }
        return false;
    }

    private static int getMoveSituation(int nextY, int nextX) {
        char[][] tempChessBoard = ChessBoard.chessBoard;
        if (tempChessBoard[nextY][nextX] == 'X') {
            return 1;
        }
        return 0;
    }

    private static boolean isInCheck() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard.chessBoard[i][j] == 'K') {
                    if (CheckKing.isInCheck(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoard.chessBoard[i][j] == 'K') {
                    if (CheckKing.isInCheck(i, j)) {
                        if (CheckKing.isCheckmate(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}