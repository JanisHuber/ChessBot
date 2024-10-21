import java.util.ArrayList;

public class ChessMain {
    public static void main(String[] args) {
        Init.Init();
        ArrayList<int[]> moves = ChessPossibleMoves.Moves('Q', 7, 3);
        for (int[] move : moves) {
            System.out.println("Possible Moves: (" + move[0] + ", " + move[1] + ")");
        }
    }
}