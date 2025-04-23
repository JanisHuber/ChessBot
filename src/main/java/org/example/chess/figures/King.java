package org.example.chess.figures;
import org.example.chess.util.ChessFigure;
import org.example.chess.board.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Field> possibleMoves = new ArrayList<>();
    private boolean hasMoved = false;
    public int value = 1000;

    public List<Field> getPossibleMoves() {
        possibleMoves.clear();

        if (this.position.column + 1 <= 8 && this.position.getRowInt() <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 64)), this.position.column + 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column + 1 <= 8 && this.position.getRowInt() + 1 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 1 + 64)), this.position.column + 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column <= 8 && this.position.getRowInt() + 1 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 1 + 64)), this.position.column);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 1 >= 1 && this.position.getRowInt() + 1 <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 1 + 64)), this.position.column - 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 1 >= 1 && this.position.getRowInt() <= 8) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 64)), this.position.column - 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column - 1 >= 1 && this.position.getRowInt() - 1 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 1 + 64)), this.position.column - 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column <= 8 && this.position.getRowInt() - 1 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 1 + 64)), this.position.column);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }
        if (this.position.column + 1 <= 8 && this.position.getRowInt() - 1 >= 1) {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() - 1 + 64)), this.position.column + 1);
            if (field.figure == null || field.figure.figureColor != this.figureColor) {
                possibleMoves.add(field);
            }
        }

        // Castling

        /*if (!hasMoved) Too complicated, verify moves in chessController
        {
            Field field = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 64)), this.position.column + 2);
            Field field2 = chessBoard.getField(Character.toString((char) (this.position.getRowInt() + 64)), this.position.column + 1);
            if (field.figure == null && field2.figure == null)
            {
                possibleMoves.add(field);
            }
        }*/

        return possibleMoves;
    }
}
