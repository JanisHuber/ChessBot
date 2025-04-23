package org.example.chess.figures;

import org.example.chess.util.ChessFigure;
import org.example.chess.board.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Field> possibleMoves = new ArrayList<>();
    public int value = 9;

    public List<Field> getPossibleMoves() {
        possibleMoves.clear();
        // Straight
        if (this.position.column < 8) {
            for (int i = this.position.column + 1; i <= 8; i++) {
                Field field = chessBoard.getField(this.position.row, i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }
        if (this.position.column > 1) {
            for (int i = this.position.column - 1; i >= 1; i--) {
                Field field = chessBoard.getField(this.position.row, i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }
        if ((char)(position.getRowInt() + 64) < 'H') {
            for (int i = (char)(position.getRowInt() + 1); i <= 8; i++) {
                Field field = chessBoard.getField(String.valueOf((char) (64 + i)), this.position.column);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }
        if ((char)(position.getRowInt() + 64) > 'A') {
            for (int i = (char)(position.getRowInt() - 1); i >= 1; i--) {
                Field field = chessBoard.getField(String.valueOf((char)(64 + i)), this.position.column);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }
        //Straight

        //Diagonal
        if (this.position.column < 8 && this.position.getRowInt() < 8) {
            for (int i = 1; this.position.column + i <= 8 && this.position.getRowInt() + i <= 8; i++) {
                Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + i + 64)), this.position.column + i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }

        if (this.position.column < 8 && this.position.getRowInt() > 1) {
            for (int i = 1; this.position.column + i <= 8 && this.position.getRowInt() - i >= 1; i++) {
                Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - i + 64)), this.position.column + i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }

        if (this.position.column > 1 && this.position.getRowInt() < 8) {
            for (int i = 1; this.position.column - i >= 1 && this.position.getRowInt() + i <= 8; i++) {
                Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + i + 64)), this.position.column - i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }

        if (this.position.column > 1 && this.position.getRowInt() > 1) {
            for (int i = 1; this.position.column - i >= 1 && this.position.getRowInt() - i >= 1; i++) {
                Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - i + 64)), this.position.column - i);
                if (field.figure == null) {
                    possibleMoves.add(field);
                } else if (field.figure.figureColor != this.figureColor) {
                    possibleMoves.add(field);
                    break;
                } else {
                    break;
                }
            }
        }
        //Diagonal
        return possibleMoves;
    }
}
