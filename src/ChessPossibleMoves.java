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
                        possibleMoves.add(new int[]{y + 2, x, 0});
                    }
                    if (chessBoard[y + 1][x] == '-' && y + 1 < 8 && pawnId < 9) {
                        possibleMoves.add(new int[]{y + 1, x, 0});
                    }
                    if (y == 6 && chessBoard[y -2][x] == '-' && chessBoard[y - 1][x] == '-')
                    {
                        possibleMoves.add(new int[]{y - 2, x, 0});
                    }
                    if (chessBoard[y - 1][x] == '-' && y - 1 >= 0 && pawnId > 8)
                    {
                        possibleMoves.add(new int[]{y - 1, x, 0});
                    }

                    if (pawnId < 9) {
                        if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] != '-' && chessBoard[y + 1][x + 1] != 'K' && isOpponent(y, x, y + 1, x + 1, piece, chessBoard[y + 1][x + 1])) {
                            possibleMoves.add(new int[]{y + 1, x + 1, 1});
                        } else if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] == 'K' && isOpponent(y, x, y + 1, x + 1, piece, chessBoard[y + 1][x + 1])) {
                            possibleMoves.add(new int[]{y + 1, x + 1, 2});
                        }
                        if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] != '-' && chessBoard[y + 1][x - 1] != 'K' && isOpponent(y, x, y + 1, x - 1, piece, chessBoard[y + 1][x - 1])) {
                            possibleMoves.add(new int[]{y + 1, x - 1, 1});
                        } else if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] == 'K' && isOpponent(y, x, y + 1, x - 1, piece, chessBoard[y + 1][x - 1])) {
                            possibleMoves.add(new int[]{y + 1, x - 1, 2});
                        }
                    }

                    if (pawnId > 8) {
                        if (y - 1 >= 0 && x + 1 < 8 && chessBoard[y - 1][x + 1] != '-' && chessBoard[y - 1][x + 1] != 'K' && isOpponent(y, x, y - 1, x + 1, piece, chessBoard[y - 1][x + 1])) {
                            possibleMoves.add(new int[]{y - 1, x + 1, 1});
                        } else if (y - 1 >= 0 && x + 1 < 8 && chessBoard[y - 1][x + 1] == 'K' && isOpponent(y, x, y - 1, x + 1, piece, chessBoard[y - 1][x + 1])) {
                            possibleMoves.add(new int[]{y - 1, x + 1, 2});
                        }
                        if (y - 1 >= 0 && x - 1 >= 0 && chessBoard[y - 1][x - 1] != '-' && chessBoard[y - 1][x - 1] != 'K' && isOpponent(y, x, y - 1, x - 1, piece, chessBoard[y - 1][x - 1])) {
                            possibleMoves.add(new int[]{y - 1, x - 1, 1});
                        } else if (y - 1 >= 0 && x - 1 >= 0 && chessBoard[y - 1][x - 1] == 'K' && isOpponent(y, x, y - 1, x - 1, piece, chessBoard[y - 1][x - 1])) {
                            possibleMoves.add(new int[]{y - 1, x - 1, 2});
                        }
                    }
                }

            case 'R':
                Integer rookId = Init.rookPositions.get(y + "," + x);
                if (rookId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x] == '-') {
                            possibleMoves.add(new int[]{y + i, x, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x] != '-' && chessBoard[y + i][x] != 'K' && isOpponent(y, x, y + i, x, piece, chessBoard[y + i][x])) {
                            possibleMoves.add(new int[]{y + i, x, 1});
                            break;
                        } else if (y + i < 8 && chessBoard[y + i][x] == 'K' && isOpponent(y, x, y + i, x, piece, chessBoard[y + i][x])) {
                            possibleMoves.add(new int[]{y + i, x, 2});
                            break;
                        } else {
                            break;
                        }
                    }

                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x] != '-' && chessBoard[y - i][x] != 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 1});
                            break;
                        } else if (y - i >= 0 && chessBoard[y - i][x] == 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i, 0});
                        } else if (x + i < 8 && chessBoard[y][x + i] != '-' && chessBoard[y][x + i] != 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 1});
                            break;
                        } else if (x + i < 8 && chessBoard[y][x + i] == 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i, 0});
                        } else if (x - i >= 0 && chessBoard[y][x - i] != '-' && chessBoard[y][x - i] != 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i, 1});
                            break;
                        } else if (x - i >= 0 && chessBoard[y][x - i] == 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i, 2});
                            break;
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
                            possibleMoves.add(new int[]{y + i, x, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x] != '-' && chessBoard[y + i][x] != 'K' && isOpponent(y, x, y + i, x, piece, chessBoard[y + i][x])) {
                            possibleMoves.add(new int[]{y + i, x, 1});
                            break;
                        } else if (y + i < 8 && chessBoard[y + i][x] == 'K' && isOpponent(y, x, y + i, x, piece, chessBoard[y + i][x])) {
                            possibleMoves.add(new int[]{y + i, x, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x] != '-' && chessBoard[y - i][x] != 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 1});
                            break;
                        } else if (y - i >= 0 && chessBoard[y - i][x] == 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i, 0});
                        } else if (x + i < 8 && chessBoard[y][x + i] != '-' && chessBoard[y][x + i] != 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 1});
                            break;
                        } else if (x + i < 8 && chessBoard[y][x + i] == 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i, 0});
                        } else if (x - i >= 0 && chessBoard[y][x - i] != '-' && chessBoard[y][x - i] != 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i, 1});
                            break;
                        } else if (x - i >= 0 && chessBoard[y][x - i] == 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) { //Diagonal
                        if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] == '-') {
                            possibleMoves.add(new int[]{y + i, x + i, 0});
                        } else if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] != '-' && chessBoard[y + i][x + i] != 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i, 1});
                            break;
                        } else if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] == 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] == '-') {
                            possibleMoves.add(new int[]{y + i, x - i, 0});
                        } else if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] != '-' && chessBoard[y + i][x - i] != 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 1});
                            break;
                        } else if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] == 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] == '-') {
                            possibleMoves.add(new int[]{y - i, x + i, 0});
                        } else if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] != '-' && chessBoard[y - i][x + i] != 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 1});
                            break;
                        } else if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] == 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] == '-') {
                            possibleMoves.add(new int[]{y - i, x - i, 0});
                        } else if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] != '-' && chessBoard[y - i][x - i] != 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 1});
                            break;
                        } else if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] == 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 2});
                            break;
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
                        possibleMoves.add(new int[]{y + 2, x + 1, 0});
                    }
                    else if (y + 2 < 8 && x + 1 < 8 && chessBoard[y + 2][x + 1] != '-' && chessBoard[y + 2][x + 1] != 'K' && isOpponent(y, x, y + 2, x + 1, piece, chessBoard[y + 2][x + 1])) {
                        possibleMoves.add(new int[]{y + 2, x + 1, 1});
                    }
                    else if (y + 2 < 8 && x + 1 < 8 && chessBoard[y + 2][x + 1] == 'K' && isOpponent(y, x, y + 2, x + 1, piece, chessBoard[y + 2][x + 1])) {
                        possibleMoves.add(new int[]{y + 2, x + 1, 2});
                    }

                    if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y + 2, x - 1, 0});
                    } else if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] != '-' && chessBoard[y + 2][x - 1] != 'K' && isOpponent(y, x, y + 2, x - 1, piece, chessBoard[y + 2][x - 1])) {
                        possibleMoves.add(new int[]{y + 2, x - 1, 1});
                    } else if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] == 'K' && isOpponent(y, x, y + 2, x - 1, piece, chessBoard[y + 2][x - 1])) {
                        possibleMoves.add(new int[]{y + 2, x - 1, 2});
                    }

                    if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x + 1, 0});
                    }
                    else if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] != '-' && chessBoard[y - 2][x + 1] != 'K' && isOpponent(y, x, y - 2, x + 1, piece, chessBoard[y - 2][x + 1])) {
                        possibleMoves.add(new int[]{y - 2, x + 1, 1, 1});
                    } else if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] == 'K' && isOpponent(y, x, y - 2, x + 1, piece, chessBoard[y - 2][x + 1])) {
                        possibleMoves.add(new int[]{y - 2, x + 1, 2, 2});
                    }

                    if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x - 1, 0});
                    }
                    else if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] != '-' && chessBoard[y - 2][x - 1] != 'K' && isOpponent(y, x, y - 2, x - 1, piece, chessBoard[y - 2][x - 1])) {
                        possibleMoves.add(new int[]{y - 2, x - 1, 1});
                    } else if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] == 'K' && isOpponent(y, x, y - 2, x - 1, piece, chessBoard[y - 2][x - 1])) {
                        possibleMoves.add(new int[]{y - 2, x - 1, 2});
                    }

                    if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x + 2, 0});
                    }
                    else if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] != '-' && chessBoard[y + 1][x + 2] != 'K' && isOpponent(y, x, y + 1, x + 2, piece, chessBoard[y + 1][x + 2])) {
                        possibleMoves.add(new int[]{y + 1, x + 2, 1});
                    } else if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] == 'K' && isOpponent(y, x, y + 1, x + 2, piece, chessBoard[y + 1][x + 2])) {
                        possibleMoves.add(new int[]{y + 1, x + 2, 2});
                    }

                    if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x - 2, 0});
                    } else if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] != '-' && chessBoard[y + 1][x - 2] != 'K' && isOpponent(y, x, y + 1, x - 2, piece, chessBoard[y + 1][x - 2])) {
                        possibleMoves.add(new int[]{y + 1, x - 2, 1});
                    } else if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] == 'K' && isOpponent(y, x, y + 1, x - 2, piece, chessBoard[y + 1][x - 2])) {
                        possibleMoves.add(new int[]{y + 1, x - 2, 2});
                    }

                    if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x + 2, 0});
                    } else if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] != '-' && chessBoard[y - 1][x + 2] != 'K' && isOpponent(y, x, y - 1, x + 2, piece, chessBoard[y - 1][x + 2])) {
                        possibleMoves.add(new int[]{y - 1, x + 2, 1});
                    } else if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] == 'K' && isOpponent(y, x, y - 1, x + 2, piece, chessBoard[y - 1][x + 2])) {
                        possibleMoves.add(new int[]{y - 1, x + 2, 2});
                    }

                    if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x - 2, 0});
                    }
                    else if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] != '-' && chessBoard[y - 1][x - 2] != 'K' && isOpponent(y, x, y - 1, x - 2, piece, chessBoard[y - 1][x - 2])) {
                        possibleMoves.add(new int[]{y - 1, x - 2, 1});
                    } else if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] == 'K' && isOpponent(y, x, y - 1, x - 2, piece, chessBoard[y - 1][x - 2])) {
                        possibleMoves.add(new int[]{y - 1, x - 2, 2});
                    }
                }
                break;

            case 'B':
                Integer bishopId = Init.bishopPositions.get(y + "," + x);
                if (bishopId != null) {
                    for (int i = 1; i < 8; i++) { //Diagonal
                        if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] == '-') {
                            possibleMoves.add(new int[]{y + i, x + i, 0});
                        } else if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] != '-' && chessBoard[y + i][x + i] != 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i, 1});
                            break;
                        } else if (y + i < 8 && x + i < 8 && chessBoard[y + i][x + i] == 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] == '-') {
                            possibleMoves.add(new int[]{y + i, x - i, 0});
                        } else if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] != '-' && chessBoard[y + i][x - i] != 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 1});
                            break;
                        } else if (y + i < 8 && x - i >= 0 && chessBoard[y + i][x - i] == 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] == '-') {
                            possibleMoves.add(new int[]{y - i, x + i, 0});
                        } else if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] != '-' && chessBoard[y - i][x + i] != 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 1});
                            break;
                        } else if (y - i >= 0 && x + i < 8 && chessBoard[y - i][x + i] == 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 2});
                            break;
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] == '-') {
                            possibleMoves.add(new int[]{y - i, x - i, 0});
                        } else if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] != '-' && chessBoard[y - i][x - i] != 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 1});
                            break;
                        } else if (y - i >= 0 && x - i >= 0 && chessBoard[y - i][x - i] == 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 2});
                            break;
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
                            possibleMoves.add(new int[]{y + 1, x, 0});
                        } else if (y + 1 < 8 && chessBoard[y + 1][x] != '-' && chessBoard[y + 1][x] != 'K' && isOpponent(y, x, y + 1, x, piece, chessBoard[y + 1][x]))
                        {
                            possibleMoves.add(new int[]{y + 1, x, 1});
                        }

                        if (y - 1 >= 0 && chessBoard[y - 1][x] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x, 0});
                        } else if (y - 1 >= 0 && chessBoard[y - 1][x] != '-' && chessBoard[y - 1][x] != 'K' && isOpponent(y, x, y - 1, x, piece, chessBoard[y - 1][x]))
                        {
                            possibleMoves.add(new int[]{y - 1, x, 1});
                        }

                        if (x + 1 < 8 && chessBoard[y][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y, x + 1, 0});
                        }else if (x + 1 < 8 && chessBoard[y][x + 1] != '-' && chessBoard[y][x + 1] != 'K' && isOpponent(y, x, y, x + 1, piece, chessBoard[y][x + 1]))
                        {
                            possibleMoves.add(new int[]{y, x + 1, 1});
                        }

                        if (x - 1 >= 0 && chessBoard[y][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y, x - 1, 0});
                        } else if (x - 1 >= 0 && chessBoard[y][x - 1] != '-' && chessBoard[y][x - 1] != 'K' && isOpponent(y, x, y, x - 1, piece, chessBoard[y][x - 1]))
                        {
                            possibleMoves.add(new int[]{y, x - 1, 1});
                        }

                        if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y + 1, x + 1, 0});
                        } else if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] != '-' && chessBoard[y + 1][x + 1] != 'K' && isOpponent(y, x, y + 1, x + 1, piece, chessBoard[y + 1][x + 1]))
                        {
                            possibleMoves.add(new int[]{y + 1, x + 1, 1});
                        }

                        if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y + 1, x - 1, 0});
                        } else if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] != '-' && chessBoard[y + 1][x - 1] != 'K' && isOpponent(y, x, y + 1, x - 1, piece, chessBoard[y + 1][x - 1]))
                        {
                            possibleMoves.add(new int[]{y + 1, x - 1, 1});
                        }

                        if (y - 1 >= 0 && x + 1 < 8 && chessBoard[y - 1][x + 1] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x + 1, 0});
                        } else if (y - 1 >= 0 && x + 1 < 8 && chessBoard[y - 1][x + 1] != '-' && chessBoard[y - 1][x + 1] != 'K' && isOpponent(y, x, y - 1, x + 1, piece, chessBoard[y - 1][x + 1]))
                        {
                            possibleMoves.add(new int[]{y - 1, x + 1, 1});
                        }

                        if (y - 1 >= 0 && x - 1 >= 0 && chessBoard[y - 1][x - 1] == '-')
                        {
                            possibleMoves.add(new int[]{y - 1, x - 1, 0});
                        } else if (y - 1 >= 0 && x - 1 >= 0 && chessBoard[y - 1][x - 1] != '-' && chessBoard[y - 1][x - 1] != 'K' && isOpponent(y, x, y - 1, x - 1, piece, chessBoard[y - 1][x - 1]))
                        {
                            possibleMoves.add(new int[]{y - 1, x - 1, 1});
                        }
                    }
                break;
            }
                return possibleMoves;
        }


    static boolean isOpponent(int y, int x, int targetY, int targetX, char piece, char targetPiece)
    {
        if (piece == '-' || targetPiece == '-'){
            return false;
        }
        String currentColor = getColor(y, x, piece);
        String targetColor = getColor(targetY, targetX, targetPiece);
        return !currentColor.equals(targetColor);
    }

    static String getColor(int y, int x, char piece) {
    switch(piece) {
        case 'P':
            Integer currentPieceId = Init.pawnPositions.get(y + "," + x);
            if (currentPieceId != null) {
                if (currentPieceId > 8) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
        case 'N':
            Integer currentKnightId = Init.knightPositions.get(y + "," + x);
            if (currentKnightId != null) {
                if (currentKnightId > 2) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
        case 'B':
            Integer currentBishopId = Init.bishopPositions.get(y + "," + x);
            if (currentBishopId != null) {
                if (currentBishopId > 2) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
        case 'R':
            Integer currentRookId = Init.rookPositions.get(y + "," + x);
            if (currentRookId != null) {
                if (currentRookId > 2) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
        case 'Q':
            Integer currentQueenId = Init.queenPositions.get(y + "," + x);
            if (currentQueenId != null) {
                if (currentQueenId > 1) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
        case 'K':
            Integer currentKingId = Init.kingPositions.get(y + "," + x);
            if (currentKingId != null) {
                if (currentKingId > 1) {
                    return "white";
                } else {
                    return "black";
                }
            }
            break;
    }
    return "idError";
}
}