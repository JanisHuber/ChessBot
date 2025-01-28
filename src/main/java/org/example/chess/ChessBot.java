package org.example.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessBot {
    private int depth;
    private int branches = 0;
    private int prunedBranches = 0;

    public ChessBot(int depth) {
        this.depth = depth;
    }

    public Move getBestMove(ChessController controller) {
        Move bestMove = null;
        int maxEval = Integer.MIN_VALUE;
        this.branches = 0;
        this.prunedBranches = 0;

        List<Move> possibleMoves = sortMoves(getPossibleMoves(controller), controller.chessBoard);

        for (Move move : possibleMoves) {
            ChessController clonedController = SerializationUtil.deepClone(controller);

            clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
            clonedController.currentTurn = FigureColor.BLACK;

            int eval = alphaBeta(clonedController, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

            if (eval > maxEval) {
                maxEval = eval;
                bestMove = move;
            }
        }
        System.out.println("getestete Branches: " + branches);
        System.out.println("pruned Branches: " + prunedBranches);
        System.out.println("MaxEval: " + maxEval);
        return bestMove;
    }

    private int alphaBeta(ChessController controller, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        if (depth == 0) {

            return evaluateBoard.evaluateBoard(controller);
        }
        this.branches++;

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            List<Move> possibleMoves = sortMoves(getPossibleMoves(controller), controller.chessBoard);
            for (Move move : possibleMoves) {
                ChessController clonedController = SerializationUtil.deepClone(controller);
                clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
                clonedController.currentTurn = FigureColor.BLACK;

                int eval = alphaBeta(clonedController, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                System.out.println("Evaluierter Zug: " + move + " mit Wert: " + eval);


                if (beta <= alpha) {
                    this.prunedBranches++;
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            List<Move> possibleMoves = sortMoves(getPossibleMoves(controller), controller.chessBoard);
            for (Move move : possibleMoves) {
                ChessController clonedController = SerializationUtil.deepClone(controller);
                clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
                clonedController.currentTurn = FigureColor.WHITE;

                int eval = alphaBeta(clonedController, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                System.out.println("Evaluierter Zug: " + move + " mit Wert: " + eval);


                if (beta <= alpha) {
                    this.prunedBranches++;
                    break;
                }
            }
            return minEval;
        }
    }

    private List<Move> getPossibleMoves(ChessController controller) {
        List<Move> moves = new ArrayList<>();
        for (Field field : controller.chessBoard.getFields()) {
            if (field.figure != null && field.figure.figureColor == controller.currentTurn) {
                for (Field target : field.figure.getPossibleMoves()) {
                    moves.add(new Move(field, target));
                }
            }
        }
        return moves;
    }

    public List<Move> sortMoves(List<Move> moves, ChessBoard chessBoard) {
        List<Move> sortedMoves = new ArrayList<>();
        List<Move> remainingMoves = new ArrayList<>();

        for (Move move : moves) {
            if (move.getTarget(chessBoard).figure != null) {
                sortedMoves.add(move);
            } else {
                remainingMoves.add(move);
            }
        }

        sortedMoves.addAll(remainingMoves);
        return sortedMoves;
    }

}
