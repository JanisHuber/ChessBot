package org.example.chess.backend.controller;

import org.example.chess.backend.board.ChessBoard;
import org.example.chess.backend.util.ChessFigure;
import org.example.chess.backend.board.Field;
import org.example.chess.backend.enums.FigureColor;

import java.util.ArrayList;
import java.util.List;

public class CheckmateHandler {
    private final ChessBoard chessBoard;
    private final FigureColor currentTurn;

    private final List<Field> possibleCaptureSources = new ArrayList<>();
    private final List<Field> possibleBlockSources = new ArrayList<>();

    public CheckmateHandler(ChessBoard chessBoard, FigureColor currentTurn) {
        this.chessBoard = chessBoard;
        this.currentTurn = currentTurn;
    }

    public List<Field> getPossibleCaptureSources() {
        return possibleCaptureSources;
    }

    public List<Field> getPossibleBlockSources() {
        return possibleBlockSources;
    }

    public List<Field> getPossiblesMates(Field kingField) {
        List<Field> possibleMates = new ArrayList<>();
        List<Field> currentCheckingFields = new ArrayList<>();
        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                if (kingField == null) {
                    kingField = field;
                }
            }
            else if (field.figure.figureColor != currentTurn) {
                currentCheckingFields.add(field);
            }
        }
        for (Field field : currentCheckingFields) {
            for (Field figureField : field.figure.getPossibleMoves()) {
                if (figureField == kingField) {
                    possibleMates.add(field);
                }
            }
        }
        return possibleMates;
    }

    public int isMate(Field kingField) {
        if (kingField == null) {
            for (Field field : chessBoard.Fields) {
                if (field.figure == null) {
                    continue;
                }
                String figureType = field.figure.getClassName();
                if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                    kingField = field;
                }
            }
        }
        List<Field> possibleMates = getPossiblesMates(kingField);
        return possibleMates.size();
    }

    public List<Field> canCapture() {
        possibleCaptureSources.clear();
        List<Field> possibleCaptures = new ArrayList<>();
        List<Field> possibleMates = getPossiblesMates(null);
        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            if (field.figure.figureColor == currentTurn) {
                for (Field mate : possibleMates) {
                    for (Field figureField : field.figure.getPossibleMoves()) {
                        if (figureField == mate) {
                            ChessFigure originalFigure = figureField.figure;
                            Field originalPosition = field.figure.position;

                            boolean isOutOfCheck = isMate(null) == 0;

                            figureField.figure = originalFigure;
                            field.figure.position = originalPosition;
                            if (isOutOfCheck) {
                                possibleCaptures.add(figureField);
                                possibleCaptureSources.add(field);
                            }
                        }
                    }
                }
            }
        }
        return possibleCaptures;
    }

    public List<Field> canBlock() {
        possibleBlockSources.clear();
        List<Field> blocks = new ArrayList<>();
        ChessFigure originalFigure;
        boolean isOutOfCheck;

        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                continue;
            }
            if (field.figure.figureColor != currentTurn) {
                continue;
            }
            for (Field possibleMove : field.figure.getPossibleMoves()) {
                isOutOfCheck = false;
                if (possibleMove.figure != null) {
                    originalFigure = possibleMove.figure;
                } else {
                    originalFigure = null;
                }
                possibleMove.figure = field.figure;
                possibleMove.figure.position = possibleMove;

                isOutOfCheck = isMate(null) == 0;

                field.figure.position = field;
                possibleMove.figure = originalFigure;
                if (isOutOfCheck) {
                    blocks.add(possibleMove);
                    possibleBlockSources.add(field);
                }
            }
        }
        return blocks;
    }

    public List<Field> canEscape() {
        List<Field> possibleEscapes;
        List<Field> escapes = new ArrayList<>();
        Field kingField = null;
        boolean isOutOfCheck;

        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                kingField = field;
            }
        }
        possibleEscapes = kingField.figure.getPossibleMoves();
        for (Field escapingField : possibleEscapes) {
            isOutOfCheck = false;
            ChessFigure king = kingField.figure;
            ChessFigure captured = escapingField.figure;

            escapingField.figure = king;
            kingField.figure = null;
            king.position = escapingField;

            isOutOfCheck = isMate(escapingField) == 0;

            king.position = kingField;
            kingField.figure = king;
            escapingField.figure = captured;

            if (isOutOfCheck) {
                escapes.add(escapingField);
            }
        }
        return escapes;
    }
}
