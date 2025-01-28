package org.example.chess;

public class Move {
    private final Field source;
    private final Field target;

    public Move(Field source, Field target) {
        this.source = source;
        this.target = target;
    }

    public Field getSource(ChessBoard board) {
        return board.getField(source.row, source.column);
    }

    public Field getTarget(ChessBoard board) {
        return board.getField(target.row, target.column);
    }
}
