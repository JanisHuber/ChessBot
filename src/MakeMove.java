public class MakeMove {
    static void MakeMove (int y, int x, int nextY, int nextX, char piece, int situation)
    {
        char targetPiece = ChessBoard.chessBoard[nextY][nextX];

        if (situation == 1)
        {
            switch (targetPiece)
            {
                case 'P':
                    Init.pawnPositions.remove(nextY + "," + nextX);
                    break;
                case 'R':
                    Init.rookPositions.remove(nextY + "," + nextX);
                    break;
                case 'N':
                    Init.knightPositions.remove(nextY + "," + nextX);
                    break;
                case 'B':
                    Init.bishopPositions.remove(nextY + "," + nextX);
                    break;
                case 'Q':
                    Init.queenPositions.remove(nextY + "," + nextX);
                    break;
                case 'K':
                    Init.kingPositions.remove(nextY + "," + nextX);
                    break;
            }
        }

        switch (piece)
        {
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
        Init.printBoard(ChessBoard.chessBoard);


    }
}
