import java.util.ArrayList;
import java.util.Scanner;

public class playerMove {
    static byte getPlayerMove(char piece, int y, int x, boolean isInCheck, boolean isPlayer) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<int[]> moves = ChessPossibleMoves.Moves(piece, y, x);
        printPossibleMoves(moves);

        System.out.println("Choose a tile (y, x): ");
        int nextY = scanner.nextInt();
        int nextX = scanner.nextInt();

        if (isValidMove(moves, nextY, nextX, isInCheck, isPlayer, piece, y, x)) {
            MakeMove.MakeMove(y, x, nextY, nextX, piece, getMoveSituation(nextY, nextX));
            if (isCheckmate()) {
                System.out.println("Checkmate!");
                return 1;
            } else if (isInCheck()) {
                System.out.println("Check!");
            }
        } else {
            System.out.println("Invalid Move or King under Check!");
            return 2;
        }
        return 0;
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

    private static boolean isValidMove(ArrayList<int[]> moves, int nextY, int nextX, boolean isInCheck, boolean isPlayer, char piece, int y, int x) {
        for (int[] move : moves) {
            if (nextY == move[0] && nextX == move[1]) {
                if (ChessMain.isInCheck(isPlayer))
                {
                    char original = ChessBoard.chessBoard[nextY][nextX];
                    ChessBoard.chessBoard[nextY][nextX] = piece;
                    ChessBoard.chessBoard[y][x] = '-';
                    boolean inCheck = ChessMain.isInCheck(isPlayer);
                    ChessBoard.chessBoard[nextY][nextX] = original;
                    ChessBoard.chessBoard[y][x] = piece;
                    if (!inCheck) {
                        return true;
                    }
                }
                else {
                    return true;
                }
            }
        }
        return false;
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

    private static int getMoveSituation(int nextY, int nextX) {
        char[][] tempChessBoard = ChessBoard.chessBoard;
        if (tempChessBoard[nextY][nextX] == 'X') {
            return 1;
        }
        return 0;
    }
}
