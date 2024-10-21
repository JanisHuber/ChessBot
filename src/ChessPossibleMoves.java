import java.util.ArrayList;

public class ChessPossibleMoves {
    public static ArrayList<int[]> Moves(char piece, int y, int x) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        char[][] chessBoard = ChessBoard.chessBoard;
        switch (piece) {
            case 'P':
                Integer pawnId = Init.pawnPositions.get(y + "," + x);
                if (pawnId != null) {
                    if (y == 1 && chessBoard[y + 2][x] == '-' && chessBoard[y + 1][x] == '-') {
                        possibleMoves.add(new int[]{y + 2, x});
                    }
                    if (chessBoard[y + 1][x] == '-') {
                        possibleMoves.add(new int[]{y + 1, x});
                    }
                }
                break;

            case 'R':
                Integer rookId = Init.rookPositions.get(y + "," + x);
                if (rookId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x] == '-') {
                            possibleMoves.add(new int[]{y + i, x});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i});
                        } else {
                            break;
                        }
                    }
                }
                break;

            case 'Q':
                Integer queenId = Init.queenPositions.get(y + "," + x);
                if (queenId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x] == '-') {
                            possibleMoves.add(new int[]{y + i, x});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i});
                        } else {
                            break;
                        }
                    }
                }
                break;

            case 'N':
                Integer knightId = Init.knightPositions.get(y + "," + x);
                if (knightId != null) {
                    if (y + 2 < 8 && x + 1 < 8 && chessBoard[y + 2][x + 1] == '-') {
                        possibleMoves.add(new int[]{y + 2, x + 1});
                    }
                    if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y + 2, x - 1});
                    }
                    if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x + 1});
                    }
                    if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x - 1});
                    }
                    if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x + 2});
                    }
                    if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x - 2});
                    }
                    if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x + 2});
                    }
                    if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x - 2});
                    }
                }
                break;

            case 'B':
                Integer bishopId = Init.bishopPositions.get(y + "," + x);
                if (bishopId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x + i] == '-') {
                            possibleMoves.add(new int[]{y + i, x + i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x - i] == '-') {
                            possibleMoves.add(new int[]{y + i, x - i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x + i] == '-') {
                            possibleMoves.add(new int[]{y - i, x + i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x - i] == '-') {
                            possibleMoves.add(new int[]{y - i, x - i});
                        } else {
                            break;
                        }
                    }
                }
                case 'K':
                    Integer kingId = Init.kingPositions.get(y + "," + x);
                    if (kingId != null)
                    {
                        if (y + 1 < 8 && chessBoard[y + 1][x] == '-')
                        {
                            possibleMoves.add(new int[]{y + 1, x});
                        }
                        if (y - 1 >= 0 && chessBoard[y - 1][x] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x});
                        }
                        if (x + 1 < 8 && chessBoard[y][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y, x + 1});
                        }
                        if (x - 1 >= 0 && chessBoard[y][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y, x - 1});
                        }
                        if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y + 1, x + 1});
                        }
                        if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y + 1, x - 1});
                        }
                        if (y - 1 >= 0 && x + 1 < 8 && chessBoard[y - 1][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x + 1});
                        }
                        if (y - 1 >= 0 && x - 1 >= 0 && chessBoard[y - 1][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x - 1});
                        }
                    }
                break;
            }
                return possibleMoves;
        }
    }
