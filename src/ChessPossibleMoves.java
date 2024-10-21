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
                    if (chessBoard[y + 1][x] == '-') {
                        possibleMoves.add(new int[]{y + 1, x, 0});
                    }
                }
                if (pawnId != null) {
                    if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] != '-' && chessBoard[y + 1][x + 1] != 'K' && chessBoard[y + 1][x + 1] != 'P' && isOpponent(y, x, y + 1, x + 1, piece, chessBoard[y + 1][x + 1])) {
                        possibleMoves.add(new int[]{y + 1, x + 1, 1});
                    } else if (y + 1 < 8 && x + 1 < 8 && chessBoard[y + 1][x + 1] == 'K') {
                        possibleMoves.add(new int[]{y + 1, x + 1, 2});
                    }

                    if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] != '-' && chessBoard[y + 1][x - 1] != 'K' && chessBoard[y + 1][x - 1] != 'P' && isOpponent(y, x, y + 1, x - 1, piece, chessBoard[y + 1][x - 1])) {
                        possibleMoves.add(new int[]{y + 1, x - 1, 1});
                    } else if (y + 1 < 8 && x - 1 >= 0 && chessBoard[y + 1][x - 1] == 'K') {
                        possibleMoves.add(new int[]{y + 1, x - 1, 2});
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
                        } else if (y + i < 8 && chessBoard[y + i][x] == 'K') {
                            possibleMoves.add(new int[]{y + i, x, 2});
                        } else {
                            break;
                        }
                    }

                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x] != '-' && chessBoard[y - i][x] != 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x] == 'K') {
                            possibleMoves.add(new int[]{y - i, x, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i, 0});
                        } else if (x + i < 8 && chessBoard[y][x + i] != '-' && chessBoard[y][x + i] != 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 1});
                        } else if (x + i < 8 && chessBoard[y][x + i] == 'K') {
                            possibleMoves.add(new int[]{y, x + i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i, 0});
                        } else if (x - i >= 0 && chessBoard[y][x - i] != '-' && chessBoard[y][x - i] != 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i, 1});
                        } else if (x - i >= 0 && chessBoard[y][x - i] == 'K') {
                            possibleMoves.add(new int[]{y, x - i, 2});
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
                        } else if (y + i < 8 && chessBoard[y + i][x] == 'K') {
                            possibleMoves.add(new int[]{y + i, x, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x] == '-') {
                            possibleMoves.add(new int[]{y - i, x, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x] != '-' && chessBoard[y - i][x] != 'K' && isOpponent(y, x, y - i, x, piece, chessBoard[y - i][x])) {
                            possibleMoves.add(new int[]{y - i, x, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x] == 'K') {
                            possibleMoves.add(new int[]{y - i, x, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i < 8 && chessBoard[y][x + i] == '-') {
                            possibleMoves.add(new int[]{y, x + i, 0});
                        } else if (x + i < 8 && chessBoard[y][x + i] != '-' && chessBoard[y][x + i] != 'K' && isOpponent(y, x, y, x + i, piece, chessBoard[y][x + i])) {
                            possibleMoves.add(new int[]{y, x + i, 1});
                        } else if (x + i < 8 && chessBoard[y][x + i] == 'K') {
                            possibleMoves.add(new int[]{y, x + i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && chessBoard[y][x - i] == '-') {
                            possibleMoves.add(new int[]{y, x - i, 0});
                        } else if (x - i >= 0 && chessBoard[y][x - i] != '-' && chessBoard[y][x - i] != 'K' && isOpponent(y, x, y, x - i, piece, chessBoard[y][x - i])) {
                            possibleMoves.add(new int[]{y, x - i});
                        } else if (x - i >= 0 && chessBoard[y][x - i] == 'K') {
                            possibleMoves.add(new int[]{y, x - i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) { //Diagonal
                        if (y + i < 8 && chessBoard[y + i][x + i] == '-') {
                            possibleMoves.add(new int[]{y + i, x + i, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x + i] != '-' && chessBoard[y + i][x + i] != 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i});
                        } else if (y + i < 8 && chessBoard[y + i][x + i] == 'K') {
                            possibleMoves.add(new int[]{y + i, x + i});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x - i] == '-') {
                            possibleMoves.add(new int[]{y + i, x - i, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x - i] != '-' && chessBoard[y + i][x - i] != 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 1});
                        } else if (y + i < 8 && chessBoard[y + i][x - i] == 'K') {
                            possibleMoves.add(new int[]{y + i, x - i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x + i] == '-') {
                            possibleMoves.add(new int[]{y - i, x + i, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x + i] != '-' && chessBoard[y - i][x + i] != 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x + i] == 'K') {
                            possibleMoves.add(new int[]{y - i, x + i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x - i] == '-') {
                            possibleMoves.add(new int[]{y - i, x - i, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x - i] != '-' && chessBoard[y - i][x - i] != 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x - i] == 'K') {
                            possibleMoves.add(new int[]{y - i, x - i, 2});
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
                    else if (y + 2 < 8 && x + 1 < 8 && chessBoard[y + 2][x + 1] == 'K') {
                        possibleMoves.add(new int[]{y + 2, x + 1, 2});
                    }

                    if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y + 2, x - 1, 0});
                    } else if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] != '-' && chessBoard[y + 2][x - 1] != 'K' && isOpponent(y, x, y + 2, x - 1, piece, chessBoard[y + 2][x - 1])) {
                        possibleMoves.add(new int[]{y + 2, x - 1, 1});
                    } else if (y + 2 < 8 && x - 1 >= 0 && chessBoard[y + 2][x - 1] == 'K') {
                        possibleMoves.add(new int[]{y + 2, x - 1, 2});
                    }

                    if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x + 1, 0});
                    }
                    else if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] != '-' && chessBoard[y - 2][x + 1] != 'K' && isOpponent(y, x, y - 2, x + 1, piece, chessBoard[y - 2][x + 1])) {
                        possibleMoves.add(new int[]{y - 2, x + 1, 1, 1});
                    } else if (y - 2 >= 0 && x + 1 < 8 && chessBoard[y - 2][x + 1] == 'K') {
                        possibleMoves.add(new int[]{y - 2, x + 1, 2, 2});
                    }

                    if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] == '-') {
                        possibleMoves.add(new int[]{y - 2, x - 1, 0});
                    }
                    else if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] != '-' && chessBoard[y - 2][x - 1] != 'K' && isOpponent(y, x, y - 2, x - 1, piece, chessBoard[y - 2][x - 1])) {
                        possibleMoves.add(new int[]{y - 2, x - 1, 1});
                    } else if (y - 2 >= 0 && x - 1 >= 0 && chessBoard[y - 2][x - 1] == 'K') {
                        possibleMoves.add(new int[]{y - 2, x - 1, 2});
                    }

                    if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x + 2, 0});
                    }
                    else if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] != '-' && chessBoard[y + 1][x + 2] != 'K' && isOpponent(y, x, y + 1, x + 2, piece, chessBoard[y + 1][x + 2])) {
                        possibleMoves.add(new int[]{y + 1, x + 2, 1});
                    } else if (y + 1 < 8 && x + 2 < 8 && chessBoard[y + 1][x + 2] == 'K') {
                        possibleMoves.add(new int[]{y + 1, x + 2, 2});
                    }

                    if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y + 1, x - 2, 0});
                    } else if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] != '-' && chessBoard[y + 1][x - 2] != 'K' && isOpponent(y, x, y + 1, x - 2, piece, chessBoard[y + 1][x - 2])) {
                        possibleMoves.add(new int[]{y + 1, x - 2, 1});
                    } else if (y + 1 < 8 && x - 2 >= 0 && chessBoard[y + 1][x - 2] == 'K') {
                        possibleMoves.add(new int[]{y + 1, x - 2, 2});
                    }

                    if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x + 2, 0});
                    } else if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] != '-' && chessBoard[y - 1][x + 2] != 'K' && isOpponent(y, x, y - 1, x + 2, piece, chessBoard[y - 1][x + 2])) {
                        possibleMoves.add(new int[]{y - 1, x + 2, 1});
                    } else if (y - 1 >= 0 && x + 2 < 8 && chessBoard[y - 1][x + 2] == 'K') {
                        possibleMoves.add(new int[]{y - 1, x + 2, 2});
                    }

                    if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] == '-') {
                        possibleMoves.add(new int[]{y - 1, x - 2, 0});
                    }
                    else if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] != '-' && chessBoard[y - 1][x - 2] != 'K' && isOpponent(y, x, y - 1, x - 2, piece, chessBoard[y - 1][x - 2])) {
                        possibleMoves.add(new int[]{y - 1, x - 2, 1});
                    } else if (y - 1 >= 0 && x - 2 >= 0 && chessBoard[y - 1][x - 2] == 'K') {
                        possibleMoves.add(new int[]{y - 1, x - 2, 2});
                    }
                }
                break;

            case 'B':
                Integer bishopId = Init.bishopPositions.get(y + "," + x);
                if (bishopId != null) {
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x + i] == '-') {
                            possibleMoves.add(new int[]{y + i, x + i, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x + i] != '-' && chessBoard[y + i][x + i] != 'K' && isOpponent(y, x, y + i, x + i, piece, chessBoard[y + i][x + i])) {
                            possibleMoves.add(new int[]{y + i, x + i, 1});
                        } else if (y + i < 8 && chessBoard[y + i][x + i] == 'K') {
                            possibleMoves.add(new int[]{y + i, x + i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y + i < 8 && chessBoard[y + i][x - i] == '-') {
                            possibleMoves.add(new int[]{y + i, x - i, 0});
                        } else if (y + i < 8 && chessBoard[y + i][x - i] != '-' && chessBoard[y + i][x - i] != 'K' && isOpponent(y, x, y + i, x - i, piece, chessBoard[y + i][x - i])) {
                            possibleMoves.add(new int[]{y + i, x - i, 1});
                        } else if (y + i < 8 && chessBoard[y + i][x - i] == 'K') {
                            possibleMoves.add(new int[]{y + i, x - i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x + i] == '-') {
                            possibleMoves.add(new int[]{y - i, x + i, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x + i] != '-' && chessBoard[y - i][x + i] != 'K' && isOpponent(y, x, y - i, x + i, piece, chessBoard[y - i][x + i])) {
                            possibleMoves.add(new int[]{y - i, x + i, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x + i] == 'K') {
                            possibleMoves.add(new int[]{y - i, x + i, 2});
                        } else {
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (y - i >= 0 && chessBoard[y - i][x - i] == '-') {
                            possibleMoves.add(new int[]{y - i, x - i, 0});
                        } else if (y - i >= 0 && chessBoard[y - i][x - i] != '-' && chessBoard[y - i][x - i] != 'K' && isOpponent(y, x, y - i, x - i, piece, chessBoard[y - i][x - i])) {
                            possibleMoves.add(new int[]{y - i, x - i, 1});
                        } else if (y - i >= 0 && chessBoard[y - i][x - i] == 'K') {
                            possibleMoves.add(new int[]{y - i, x - i, 2});
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


    static boolean isOpponent(int y, int x, int targetY, int targetX, char piece, char targetPiece) {
        int targetLimit = 0;
        int targetPieceNum = 0;
        switch (targetPiece) {
            case 'P':
                targetLimit = 8;
                targetPieceNum = Init.pawnPositions.get(targetX + "," + targetY);
                break;
            case 'R':
                targetLimit = 2;
                targetPieceNum = Init.rookPositions.get(targetX + "," + targetY);
                break;
            case 'N':
                targetLimit = 2;
                targetPieceNum = Init.knightPositions.get(targetX + "," + targetY);
                break;
            case 'B':
                targetLimit = 2;
                targetPieceNum = Init.bishopPositions.get(targetX + "," + targetY);
                break;
            case 'Q':
                targetLimit = 1;
                targetPieceNum = Init.queenPositions.get(targetX + "," + targetY);
                break;
            case 'K':
                targetLimit = 1;
                targetPieceNum = Init.kingPositions.get(targetX + "," + targetY);
                break;
        }

        if (piece == 'P')
        {
            int currentPiece = Init.pawnPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        else if (piece == 'R')
        {
            int currentPiece = Init.rookPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        else if (piece == 'N')
        {
            int currentPiece = Init.knightPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        else if (piece == 'B')
        {
            int currentPiece = Init.bishopPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        else if (piece == 'Q')
        {
            int currentPiece = Init.queenPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        else if (piece == 'K') {
            int currentPiece = Init.kingPositions.get(x + "," + y);
            if (currentPiece <= targetLimit && targetPieceNum > targetLimit) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

