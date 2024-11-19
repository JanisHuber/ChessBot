import java.lang.reflect.Array;
import java.util.ArrayList;

public class MakeMove {
    public static ArrayList<Character> oldPiece = new ArrayList<>();
    public static ArrayList<Integer> oldId = new ArrayList<>();
    public static ArrayList<int[]> oldPosition = new ArrayList<>();
    public static ArrayList<Integer> oldSituation = new ArrayList<>();
    public static ArrayList<Character> oldTargetPiece = new ArrayList<>();

    static void MakeMove(int y, int x, int nextY, int nextX, char piece, int situation) {
        char targetPiece = ChessBoard.chessBoard[nextY][nextX];
        oldTargetPiece.add(targetPiece);
        oldPiece.add(piece);
        oldPosition.add(new int[]{nextY, nextX, y, x});
        oldSituation.add(situation);

        if (situation == 1) {
            switch (targetPiece) {
                case 'P':
                    oldId.add(Init.pawnPositions.get(nextY + "," + nextX));
                    Init.pawnPositions.remove(nextY + "," + nextX);
                    break;
                case 'R':
                    oldId.add(Init.rookPositions.get(nextY + "," + nextX));
                    Init.rookPositions.remove(nextY + "," + nextX);
                    break;
                case 'N':
                    oldId.add(Init.knightPositions.get(nextY + "," + nextX));
                    Init.knightPositions.remove(nextY + "," + nextX);
                    break;
                case 'B':
                    oldId.add(Init.bishopPositions.get(nextY + "," + nextX));
                    Init.bishopPositions.remove(nextY + "," + nextX);
                    break;
                case 'Q':
                    oldId.add(Init.queenPositions.get(nextY + "," + nextX));
                    Init.queenPositions.remove(nextY + "," + nextX);
                    break;
                case 'K':
                    oldId.add(Init.kingPositions.get(nextY + "," + nextX));
                    Init.kingPositions.remove(nextY + "," + nextX);
                    break;
            }
        }

        switch (piece) {
            case 'P':

                Init.pawnPositions.put(nextY + "," + nextX, Init.pawnPositions.get(y + "," + x));
                Init.pawnPositions.remove(y + "," + x);
                break;
            case 'R':
                Init.rookPositions.put(nextY + "," + nextX, Init.rookPositions.get(y + "," + x));
                Init.rookPositions.remove(y + "," + x);
                break;
            case 'N':
                Init.knightPositions.put(nextY + "," + nextX, Init.knightPositions.get(y + "," + x));
                Init.knightPositions.remove(y + "," + x);
                break;
            case 'B':
                Init.bishopPositions.put(nextY + "," + nextX, Init.bishopPositions.get(y + "," + x));
                Init.bishopPositions.remove(y + "," + x);
                break;
            case 'Q':
                Init.queenPositions.put(nextY + "," + nextX, Init.queenPositions.get(y + "," + x));
                Init.queenPositions.remove(y + "," + x);
                break;
            case 'K':
                Init.kingPositions.put(nextY + "," + nextX, Init.kingPositions.get(y + "," + x));
                Init.kingPositions.remove(y + "," + x);
                break;
        }
        ChessBoard.chessBoard[nextY][nextX] = piece;
        ChessBoard.chessBoard[y][x] = '-';
        //Init.printBoard(ChessBoard.chessBoard);
    }


    static void restoreMove(int restoreDepth) {
        for (int i = 0; i < restoreDepth; i++) {
            char piece = oldPiece.remove(oldPiece.size() - 1);
            char targetPiece = oldTargetPiece.remove(oldTargetPiece.size() - 1);
            int[] oldPos = oldPosition.remove(oldPosition.size() - 1);
            int oldSituation2 = oldSituation.remove(oldSituation.size() - 1);

                if (oldSituation2 == 1) {
                    Integer oldId2 = oldId.remove(oldId.size() - 1);
                    switch (targetPiece) {
                        case 'P':
                            Init.pawnPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                        case 'R':
                            Init.rookPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                        case 'N':
                            Init.knightPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                        case 'B':
                            Init.bishopPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                        case 'Q':
                            Init.queenPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                        case 'K':
                            Init.kingPositions.put(oldPos[0] + "," + oldPos[1], oldId2);
                            break;
                    }
                }

            switch (piece) {
                case 'P':
                    Init.pawnPositions.put(oldPos[2] + "," + oldPos[3], Init.pawnPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.pawnPositions.remove((oldPos[0] + "," + oldPos[1]));
                    break;
                case 'R':
                    Init.rookPositions.put(oldPos[2] + "," + oldPos[3], Init.rookPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.rookPositions.remove(oldPos[0] + "," + oldPos[1]);
                    break;
                case 'N':
                    Init.knightPositions.put(oldPos[2] + "," + oldPos[3], Init.knightPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.knightPositions.remove(oldPos[0] + "," + oldPos[1]);
                    break;
                case 'B':
                    Init.bishopPositions.put(oldPos[2] + "," + oldPos[3], Init.bishopPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.bishopPositions.remove(oldPos[0] + "," + oldPos[1]);
                    break;
                case 'Q':
                    Init.queenPositions.put(oldPos[2] + "," + oldPos[3], Init.queenPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.queenPositions.remove(oldPos[0] + "," + oldPos[1]);
                    break;
                case 'K':
                    Init.kingPositions.put(oldPos[2] + "," + oldPos[3], Init.kingPositions.get(oldPos[0] + "," + oldPos[1]));
                    Init.kingPositions.remove(oldPos[0] + "," + oldPos[1]);
                    break;
            }
            ChessBoard.chessBoard[oldPos[2]][oldPos[3]] = piece;
            ChessBoard.chessBoard[oldPos[0]][oldPos[1]] = targetPiece;
        }
    }
}
