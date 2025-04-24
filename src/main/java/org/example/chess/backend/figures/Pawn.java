package org.example.chess.backend.figures;

import org.example.chess.backend.util.ChessFigure;
import org.example.chess.backend.board.Field;
import org.example.chess.backend.enums.FigureColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Field> getPossibleMoves() {
        List<Field> possibleMoves = new ArrayList<>();
        possibleMoves.clear();

        for (Field field : this.chessBoard.getFields()) {
            if (this.figureColor == FigureColor.WHITE) {

                if (field.row.equals(this.position.row) && field.column == (this.position.column + 1)) {
                    if (field.figure == null) {
                        possibleMoves.add(field);
                    }
                }
                if (field.row.equals(this.position.row) && field.column == (this.position.column + 2)) {
                    if (field.figure == null && this.position.column == 2) {
                        Field field1 = chessBoard.getField(this.position.row, this.position.column + 1);
                        if (field1.figure == null) {
                            possibleMoves.add(field);
                        }
                    }
                }
                if (field.row.equals(String.valueOf((char)(this.position.row.charAt(0) + 1))) && field.column == (this.position.column + 1)) {
                    if (field.figure != null && field.figure.figureColor != this.figureColor) {
                        possibleMoves.add(field);
                    }
                }
                if (field.row.equals(String.valueOf((char)(this.position.row.charAt(0) - 1))) && field.column == (this.position.column + 1)) {
                    if (field.figure != null && field.figure.figureColor != this.figureColor) {
                        possibleMoves.add(field);
                    }
                }

            } else if (this.figureColor == FigureColor.BLACK)
            {
                if (field.row.equals(this.position.row) && field.column == (this.position.column - 1)) {
                    if (field.figure == null) {
                        possibleMoves.add(field);
                    }
                }
                if (field.row.equals(this.position.row) && field.column == (this.position.column - 2)) {
                    if (field.figure == null && this.position.column == 7) {
                        Field field1 = chessBoard.getField(this.position.row, this.position.column - 1);
                        if (field1.figure == null) {
                            possibleMoves.add(field);
                        }
                    }
                }
                if (field.row.equals(String.valueOf((char)(this.position.row.charAt(0) + 1))) && field.column == (this.position.column - 1)) {
                    if (field.figure != null && field.figure.figureColor != this.figureColor) {
                        possibleMoves.add(field);
                    }
                }
                if (field.row.equals(String.valueOf((char)(this.position.row.charAt(0) - 1))) && field.column == (this.position.column - 1)) {
                    if (field.figure != null && field.figure.figureColor != this.figureColor) {
                        possibleMoves.add(field);
                    }
                }
            }
        }
        return possibleMoves;
    }
}