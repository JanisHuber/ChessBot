import java.util.ArrayList;
import java.util.Iterator;

public class getBotMove {
    public static ArrayList<Move> allMoves = new ArrayList<>();
    Move bestMove = null;
    ArrayList<Move> firstMoves = new ArrayList<>();
    static Move getBotMove(int depth) {
        getAllMoves(depth, "black");
        
        for (int i = 0; i < depth - 1; i++) {
            //For Moves in Moves
                //Get First Layer of Moves
                    //--For every Move (which score > 1) * depth
                        //Get all next Moves
                        //Save Moves
        }
        return bestMove;
    }

    public static Move getBestMove(String color) {
        Move bestMove = null;
        int bestScore = -10000;
        ArrayList<int[]> possiblePieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String currentColor = ChessPossibleMoves.getColor(i, j, ChessBoard.chessBoard[i][j]);
                if (ChessBoard.chessBoard[i][j] != '-' && currentColor.equals(color)) {
                    possiblePieces.add(new int[]{ChessBoard.chessBoard[i][j], i, j});
                }
            }
        }

        for (int[] piece : possiblePieces) {
            char chessPiece = (char) piece[0];
            int startY = piece[1];
            int startX = piece[2];
            ArrayList<int[]> pieceMoves = ChessPossibleMoves.Moves(chessPiece, startY, startX);

            for (int[] moveCords : pieceMoves) {
                int endY = moveCords[0];
                int endX = moveCords[1];
                int situation = moveCords[2];
                MakeMove.MakeMove(startY, startX, endY, endX, chessPiece, moveCords[2]);

                Move newMove = new Move(startY, startX, endY, endX, 0, situation);
                newMove.score = evalBoard.evalBoard(ChessBoard.chessBoard);
                MakeMove.restoreMove(1);

                if (newMove.score > bestScore) {
                    bestScore = newMove.score;
                    bestMove = newMove;
                }

                allMoves.add(newMove);
            }
        }

        System.out.println("Alle möglichen Züge:");
        for (Move move : allMoves) {
            System.out.println("Zug von (" + move.startY + ", " + move.startX + ") nach ("
                    + move.endY + ", " + move.endX + ") - Score: " + move.score);
        }
        return bestMove;
    }

    private static Move checkMoves(int startY, int startX, int endY, int endX, int score) {
        if (score > 2) {
            return new Move(startY, startX, endY, endX, score, 0);
        } else {
            return null;
        }
    }

    public static Move tryMoves(int depth) {
        ArrayList<Move> currentTriedMoves = new ArrayList<>();
        ArrayList<Move> backtrackingMoves = new ArrayList<>();
        Move bestMove = new Move (0, 0, 0, 0, 0, 0);

        //Get Possible Pieces
        ArrayList<int[]> possiblePieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String currentColor = ChessPossibleMoves.getColor(i, j, ChessBoard.chessBoard[i][j]);
                if (ChessBoard.chessBoard[i][j] != '-' && currentColor.equals("black")) {
                    possiblePieces.add(new int[]{ChessBoard.chessBoard[i][j], i, j});
                }
            }
        }

        for (int[] piece : possiblePieces) {
            char chessPiece = (char) piece[0];
            int startY = piece[1];
            int startX = piece[2];
            ArrayList<int[]> pieceMoves = ChessPossibleMoves.Moves(chessPiece, startY, startX);

            for (int[] moveCords : pieceMoves) {
                int endY = moveCords[0];
                int endX = moveCords[1];
                int situation = moveCords[2];
                backtrackingMoves.add(new Move(startY, startX, endY, endX, 0, situation));
            }
        }
        Init.printBoard(ChessBoard.chessBoard);
        Iterator<Move> iterator = backtrackingMoves.iterator();
        while (iterator.hasNext()) {
            Move currentMove = iterator.next();
            iterator.remove();
            MakeMove.MakeMove(currentMove.startY, currentMove.startX, currentMove.endY, currentMove.endX, ChessBoard.chessBoard[currentMove.startY][currentMove.startX], currentMove.situation);
            currentMove.score = evalBoard.evalBoard(ChessBoard.chessBoard);
            System.out.println("Zug von (" + currentMove.startY + ", " + currentMove.startX + ") nach ("
                    + currentMove.endY + ", " + currentMove.endX + ") - Score: " + currentMove.score);
            if (currentMove.score > bestMove.score) {
                bestMove = currentMove;
            }
            MakeMove.restoreMove(1);
        }
        MakeMove.MakeMove(bestMove.startY, bestMove.startX, bestMove.endY, bestMove.endX, ChessBoard.chessBoard[bestMove.startY][bestMove.startX], bestMove.situation);
        return bestMove;
    }

    public static void getAllMoves(int depth, String color) {
        ArrayList<int[]> possiblePieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String currentColor = ChessPossibleMoves.getColor(i, j, ChessBoard.chessBoard[i][j]);
                if (ChessBoard.chessBoard[i][j] != '-' && currentColor.equals(color)) {
                    possiblePieces.add(new int[]{ChessBoard.chessBoard[i][j], i, j});
                }
            }
        }

        for (int[] piece : possiblePieces) {
            char chessPiece = (char) piece[0];
            int startY = piece[1];
            int startX = piece[2];
            ArrayList<int[]> pieceMoves = ChessPossibleMoves.Moves(chessPiece, startY, startX);

            for (int[] moveCords : pieceMoves) {
                int endY = moveCords[0];
                int endX = moveCords[1];
                int situation = moveCords[2];
                MakeMove.MakeMove(startY, startX, endY, endX, chessPiece, moveCords[2]);

                Move newMove = new Move(startY, startX, endY, endX, 0, situation);
                newMove.score = evalBoard.evalBoard(ChessBoard.chessBoard);
                MakeMove.restoreMove(1);

                allMoves.add(newMove);
            }
        }
    }
}
