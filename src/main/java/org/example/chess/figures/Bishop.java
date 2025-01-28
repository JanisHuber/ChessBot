package org.example.chess.figures;

import org.example.chess.ChessFigure;
import org.example.chess.Field;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Bishop extends ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Field> possibleMoves = new ArrayList<>();
    public int value = 3;

    public List<Field> getPossibleMoves() {
        possibleMoves.clear();

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
        return possibleMoves;
    }
}