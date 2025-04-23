package org.example.chess.backend.bot;

import org.example.chess.backend.board.ChessBoard;
import org.example.chess.backend.board.Field;
import org.example.chess.backend.util.Move;
import org.example.chess.backend.controller.ChessController;
import org.example.chess.backend.enums.FigureColor;
import org.example.chess.backend.evaluate.evaluateBoard;
import org.example.chess.backend.util.SerializationUtil;

import java.util.ArrayList;
import java.util.List;

public class ChessBot {
    private final int depth;

    public ChessBot(int depth) {
        this.depth = depth;
    }

    public Move getBestMove(ChessController controller) {
        Move bestMove = null;
        int maxEval = Integer.MIN_VALUE;

        List<Move> possibleMoves = sortMoves(getPossibleMoves(controller), controller.chessBoard);

        for (Move move : possibleMoves) {
            int eval = evaluateMove(controller, move);
            if (eval > maxEval) {
                maxEval = eval;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int evaluateMove(ChessController controller, Move move) {
        ChessController clonedController = cloneAndApplyMove(controller, move);

        return alphaBeta(clonedController, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
    }

    private ChessController cloneAndApplyMove(ChessController controller, Move move) {
        ChessController clonedController = SerializationUtil.deepClone(controller);

        clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
        clonedController.currentTurn = FigureColor.BLACK;
        return clonedController;
    }

    private int alphaBeta(ChessController controller, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        System.out.println("alphaBeta called with depth: " + depth);
        if (depth == 0) {
            return evaluateBoard.evaluateBoard(controller);
        }

        List<Move> possibleMoves = sortMoves(getPossibleMoves(controller), controller.chessBoard);
        System.out.println("Possible moves: " + possibleMoves.size() + "At depth: " + depth);
        if (possibleMoves.isEmpty()) {
            return evaluateBoard.evaluateBoard(controller);
        }

        if (isMaximizingPlayer) {
            return getMaximizingEval(controller, depth, alpha, beta, possibleMoves);
        } else {
            return getMinimizingEval(controller, depth, alpha, beta, possibleMoves);
        }
    }

    private int getMinimizingEval(ChessController controller, int depth, int alpha, int beta, List<Move> possibleMoves) {
        int minEval = Integer.MAX_VALUE;

        for (Move move : possibleMoves) {
            ChessController clonedController = SerializationUtil.deepClone(controller);
            clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
            clonedController.currentTurn = FigureColor.WHITE;

            int eval = alphaBeta(clonedController, depth - 1, alpha, beta, true);
            minEval = Math.min(minEval, eval);
            beta = Math.min(beta, eval);

            if (beta <= alpha) {
                break;
            }
        }
        return minEval;
    }

    private int getMaximizingEval(ChessController controller, int depth, int alpha, int beta, List<Move> possibleMoves) {
        int maxEval = Integer.MIN_VALUE;

        for (Move move : possibleMoves) {
            ChessController clonedController = SerializationUtil.deepClone(controller);
            clonedController.chessBoard.MoveFigure(move.getSource(clonedController.chessBoard), move.getTarget(clonedController.chessBoard));
            clonedController.currentTurn = FigureColor.BLACK;

            int eval = alphaBeta(clonedController, depth - 1, alpha, beta, false);
            maxEval = Math.max(maxEval, eval);
            alpha = Math.max(alpha, eval);

            if (beta <= alpha) {
                break;
            }
        }
        return maxEval;
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

    private List<Move> sortMoves(List<Move> moves, ChessBoard chessBoard) {
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
