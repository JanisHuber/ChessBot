import java.util.ArrayList;

public class ChessPossibleMoves {
    public static ArrayList<int[]> Moves(char piece, int y, int x) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        char[][] chessBoard = ChessBoard.chessBoard;
        switch (piece) {
            case 'P': // Pawn
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

            case 'R': // Rook
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

            case 'Q': // Rook
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

            case 'N': // Knight
                break;

            case 'B': // Bishop
                Integer queenId = Init.queenPositions.get(y + "," + x);
                if (queenId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x] == '-') {
                            possibleMoves.add(new int[]{y + i, x});
                        } else {
                            break;
                        }
                    }
                break;
        }
        return possibleMoves;
    }
}