import java.util.ArrayList;
public class evalBoard {
    static int evalBoard(char[][] board)
    {
        ArrayList<int[]> possibleMovesOnCurrent = new ArrayList<int[]>();
        int score = 0;
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 8; j ++)
            {
                if (ChessPossibleMoves.getColor(i, j, board[i][j]).equals("black"))
                {
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            char piece = board[y][x];
                            boolean isOpponent = ChessPossibleMoves.isOpponent(i, j, y, x, board[i][j], piece);
                            if (piece != '-' && piece != 'K' && isOpponent) {
                                possibleMovesOnCurrent.addAll(ChessPossibleMoves.Moves(piece, y, x));
                            }
                        }
                    }

                    for (int[] move : possibleMovesOnCurrent) {
                        if (move[0] == i && move[1] == j) {
                            score -= getPieceValue(board[i][j]);
                            break;
                        }
                    }
                    score += getPieceValue(board[i][j]);
                }
                else if (ChessPossibleMoves.getColor(i, j, board[i][j]).equals("white"))
                {
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            char piece = board[y][x];
                            boolean isOpponent = ChessPossibleMoves.isOpponent(i, j, y, x, board[i][j], piece);
                            if (piece != '-' && piece != 'K' && isOpponent) {
                                possibleMovesOnCurrent.addAll(ChessPossibleMoves.Moves(piece, y, x));
                            }
                        }
                    }

                    for (int[] move : possibleMovesOnCurrent) {
                        if (move[0] == i && move[1] == j) {
                            score += getPieceValue(board[i][j]);
                            break;
                        }
                    }
                    score -= getPieceValue(board[i][j]);
                }
            }
        }
        return score;
    }

    static int getPieceValue(char piece)
    {
        if (piece == 'P')
        {
            return 1;
        }
        else if (piece == 'R')
        {
            return 5;
        }
        else if (piece == 'N')
        {
            return 3;
        }
        else if (piece == 'B')
        {
            return 3;
        }
        else if (piece == 'Q')
        {
            return 9;
        }
        else if (piece == 'K')
        {
            return 1000;
        }
        return 0;
    }
}
