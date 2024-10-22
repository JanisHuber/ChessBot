import java.util.ArrayList;
import java.util.Scanner;

public class ChessMain {
    public static void main(String[] args) {
        Init.Init();
        Scanner scanner = new Scanner(System.in);
        boolean play = true;
        while (play) {
            System.out.println("Wähle eine Figur (piece, y, x): ");
            char piece = scanner.next().charAt(0);
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            ArrayList<int[]> moves = ChessPossibleMoves.Moves(piece, y, x);
            char[][] tempChessBoard = new char[8][8];
            for (int i = 0; i < ChessBoard.chessBoard.length; i++) {
                tempChessBoard[i] = ChessBoard.chessBoard[i].clone();
            }
            for (int[] move : moves) {
                int situation = move[2];
                if (situation == 0) {
                    tempChessBoard[move[0]][move[1]] = 'M';
                }
                if (situation == 1) {
                    tempChessBoard[move[0]][move[1]] = 'X';
                }
            }
            Init.printBoard(tempChessBoard);
            System.out.println("Wähle ein Feld (y, x): ");
            int nextY = scanner.nextInt();
            int nextX = scanner.nextInt();
            int situation = 0; // Fix This
            MakeMove.MakeMove(y, x, nextY, nextX, piece, situation);
        }
        scanner.close();
    }
}