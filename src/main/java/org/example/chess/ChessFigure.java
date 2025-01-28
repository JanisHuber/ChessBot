package org.example.chess;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.List;

public abstract class ChessFigure implements Serializable {
    private static final long serialVersionUID = 1L;

    public transient Image image;
    public FigureColor figureColor;
    public Field position;
    public ChessBoard chessBoard;
    public int value;

    public abstract List<Field> getPossibleMoves();

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
