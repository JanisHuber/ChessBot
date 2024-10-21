import java.util.ArrayList;

public class ChessMain {
    public static void main(String[] args) {
        Init.Init();
        ArrayList<int[]> moves = ChessPossibleMoves.Moves('K', 0, 4);
        for (int[] move : moves) {
            System.out.println("Possible Moves: (" + move[0] + ", " + move[1] + ")");
        }
    }
}