import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ChessMain {
    public static void main(String[] args) {
        Init.Init();
        Scanner scanner = new Scanner(System.in);
        boolean test = true;
        while (test) {
            System.out.println("Choose a piece (piece, y, x): ");
            char piece = scanner.next().charAt(0);
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            ArrayList<int[]> moves = ChessPossibleMoves.Moves(piece, y, x);
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
                }
                if (situation == 1) {
                    tempChessBoard[move[0]][move[1]] = '-';
                    tempChessBoard[move[0]][move[1]] = 'X';
                }
                if (situation == 2) {
                    tempChessBoard[move[0]][move[1]] = '#';
                }
            }
            Init.printBoard(tempChessBoard);
            System.out.println("Choose a tile (y, x): ");
            int nextY = scanner.nextInt();
            int nextX = scanner.nextInt();
            boolean validMove = false;
            for (int[] move : moves) {
                if (nextY == move[0] && nextX == move[1]) {
                    int situation = 0;
                    if (tempChessBoard[nextY][nextX] == 'X') {
                        situation = 1;
                    }
                    MakeMove.MakeMove(y, x, nextY, nextX, piece, situation);
                    validMove = true;
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (ChessBoard.chessBoard[i][j] == 'K') {
                         if (CheckKing.isInCheck(i, j)) {
                            System.out.println("Mate!");
                        }
                    }
                }
            }
            if (!validMove) {
                System.out.println("Invalid Move!");
            }
        }
        scanner.close();
    }
}