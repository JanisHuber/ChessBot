public class MakeMove {
    static void MakeMove (int y, int x, int nextY, int nextX, char piece, int situation)
    {
        switch (piece)
        {
            case 'P':
                Init.pawnPositions.put(nextY + "," + nextX, Init.pawnPositions.get(y + "," + x));
                break;
            case 'R':
                Init.rookPositions.put(nextY + "," + nextX, Init.rookPositions.get(y + "," + x));
                break;
            case 'N':
                Init.knightPositions.put(nextY + "," + nextX, Init.knightPositions.get(y + "," + x));
                break;
            case 'B':
                Init.bishopPositions.put(nextY + "," + nextX, Init.bishopPositions.get(y + "," + x));
                break;
            case 'Q':
                Init.queenPositions.put(nextY + "," + nextX, Init.queenPositions.get(y + "," + x));
                break;
            case 'K':
                Init.kingPositions.put(nextY + "," + nextX, Init.kingPositions.get(y + "," + x));
                break;
        }
        ChessBoard.chessBoard[nextY][nextX] = piece;
        ChessBoard.chessBoard[y][x] = '-';
        Init.printBoard(ChessBoard.chessBoard);
    }
}
