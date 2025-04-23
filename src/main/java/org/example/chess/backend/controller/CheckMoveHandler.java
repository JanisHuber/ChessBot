package org.example.chess.backend.controller;

import org.example.chess.backend.board.ChessBoard;
import org.example.chess.backend.util.ChessFigure;
import org.example.chess.backend.board.Field;
import org.example.chess.backend.enums.FigureColor;
import org.example.chess.backend.util.LegalMovesInCheckHelper;
import org.example.chess.backend.util.LegalMovesOutOfCheckHelper;

import java.util.List;

public class CheckMoveHandler {
    private final ChessBoard chessBoard;
    private final FigureColor currentTurn;
    private final List<Field> possibleCaptureSources;
    private final List<Field> possibleBlockSources;

    public final CheckmateHandler checkmateHandler;

    public CheckMoveHandler(ChessBoard chessBoard, FigureColor currentTurn, List<Field> possibleCaptureSources, List<Field> possibleBlockSources) {
        this.chessBoard = chessBoard;
        this.currentTurn = currentTurn;
        this.possibleCaptureSources = possibleCaptureSources;
        this.possibleBlockSources = possibleBlockSources;

        this.checkmateHandler = new CheckmateHandler(chessBoard, currentTurn);
    }

    public List<Field> getCheckedMove(ChessFigure figure)
    {
        if (figure.figureColor != currentTurn) {
            return null;
        }
        List<Field> figureFields = figure.getPossibleMoves();

        if (checkmateHandler.isMate(null) > 0) {
            List<Field> escapes = checkmateHandler.canEscape();
            List<Field> captures = checkmateHandler.canCapture();
            List<Field> blocks = checkmateHandler.canBlock();

            figureFields = LegalMovesInCheckHelper.resolveLegalMoves(figureFields, captures, blocks, escapes, figure, possibleCaptureSources, possibleBlockSources);
        }

        figureFields = LegalMovesOutOfCheckHelper.filterMovesPreventingCheckmate(chessBoard, checkmateHandler, figureFields, figure);

        return figureFields;
    }
}
