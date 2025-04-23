package org.example.chess.backend.figures;

import org.example.chess.backend.util.ChessFigure;
import org.example.chess.backend.board.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Field> possibleMoves = new ArrayList<>();
    public int value = 3;

    public List<Field> getPossibleMoves() {
        possibleMoves.clear();

        if (this.position.column + 1 <= 8 && this.position.getRowInt() + 2 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 2 + 64)), this.position.column + 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column + 2 <= 8 && this.position.getRowInt() + 1 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 1 + 64)), this.position.column + 2);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column + 2 <= 8 && this.position.getRowInt() - 1 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 1 + 64)), this.position.column + 2);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column + 1 <= 8 && this.position.getRowInt() - 2 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 2 + 64)), this.position.column + 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 1 >= 1 && this.position.getRowInt() - 2 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 2 + 64)), this.position.column - 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 2 >= 1 && this.position.getRowInt() - 1 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 1 + 64)), this.position.column - 2);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 2 >= 1 && this.position.getRowInt() + 1 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 1 + 64)), this.position.column - 2);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 1 >= 1 && this.position.getRowInt() + 2 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 2 + 64)), this.position.column - 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        return possibleMoves;
    }
}
