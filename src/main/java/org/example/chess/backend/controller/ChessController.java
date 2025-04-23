package org.example.chess.backend.controller;
import org.example.chess.backend.board.ChessBoard;
import org.example.chess.backend.board.Field;
import org.example.chess.backend.bot.ChessBot;
import org.example.chess.backend.enums.FigureColor;
import org.example.chess.backend.util.BoardInitializerUtil;
import org.example.chess.backend.util.ChessFigure;
import org.example.chess.backend.util.Move;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChessController implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChessBoard chessBoard;
    public FigureColor currentTurn = FigureColor.WHITE;

    private List<Field> possibleCaptureSources;
    private List<Field> possibleBlockSources;

    private final boolean publicAgainstAI;
    private transient ChessBot bot;

    public CheckMoveHandler checkMoveHandler;

    public ChessController(boolean againstAI)
    {
        publicAgainstAI = againstAI;
        init();
        if (publicAgainstAI) {
            Move botMove = bot.getBestMove(this);
            Move(botMove.getSource(chessBoard), botMove.getTarget(chessBoard));
        }
    }

    private void init() {
        this.possibleCaptureSources = new ArrayList<>();
        this.possibleBlockSources = new ArrayList<>();

        if (publicAgainstAI) {
            bot = new ChessBot(3);
        }

        chessBoard = BoardInitializerUtil.Initialize(new ChessBoard());
    }

    public boolean getBotMove() {
        if (publicAgainstAI && currentTurn == FigureColor.WHITE) {
            Move botMove = bot.getBestMove(this);
            Move(botMove.getSource(chessBoard), botMove.getTarget(chessBoard));
            return true;
        }
        return false;
    }

    public boolean Move(Field currentField, Field target)
    {
        List<Field> fields = getCheckedMove(currentField.figure);
        if (fields == null) {
            return false;
        }
        if (!fields.contains(target)) {
            return false;
        } else {
            applyMove(currentField, target);
            return true;
        }
    }

    public List<Field> getCheckedMove(ChessFigure figure) {
        checkMoveHandler = new CheckMoveHandler(chessBoard, currentTurn, possibleCaptureSources, possibleBlockSources);

        return checkMoveHandler.getCheckedMove(figure);
    }

    private void applyMove(Field currentField, Field target) {
        if (target.figure != null)
        {
            target.figure.position = null;
        }
        currentField.figure.position = target;
        target.figure = currentField.figure;
        currentField.figure = null;

        currentTurn = currentTurn == FigureColor.WHITE ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
