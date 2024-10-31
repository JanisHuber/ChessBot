import java.util.ArrayList;
public class CheckKing {
    public static boolean isInCheck(int kingY, int kingX) {
        char[][] chessBoard = ChessBoard.chessBoard;
        ArrayList<int[]> opponentMoves = new ArrayList<>();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char piece = chessBoard[y][x];
                boolean isOpponent = ChessPossibleMoves.isOpponent(kingY, kingX, y, x, 'K', piece);
                if (piece != '-' && piece != 'K' && isOpponent) {
                    opponentMoves.addAll(ChessPossibleMoves.Moves(piece, y, x));
                }
            }
        }

        for (int[] move : opponentMoves) {
            if (move[0] == kingY && move[1] == kingX) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCheckmate(int kingY, int kingX) {
        if (!isInCheck(kingY, kingX)) {
            return false;
        }

        char[][] chessBoard = ChessBoard.chessBoard;
        int[] directions = {-1, 0, 1};

        for (int dy : directions) {
            for (int dx : directions) {
                if (dy == 0 && dx == 0) continue;
                int newY = kingY + dy;
                int newX = kingX + dx;
                if (newY >= 0 && newY < 8 && newX >= 0 && newX < 8 && chessBoard[newY][newX] == '-') {
                    chessBoard[kingY][kingX] = '-';
                    chessBoard[newY][newX] = 'K';
                    boolean inCheck = isInCheck(newY, newX);
                    chessBoard[newY][newX] = '-';
                    chessBoard[kingY][kingX] = 'K';
                    if (!inCheck) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}