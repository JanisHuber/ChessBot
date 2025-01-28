package org.example.chess;

import java.io.Serializable;
import java.util.List;

public class ChessBoard implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Field> Fields;

    public List<Field> getFields() {
        return Fields;
    }

    public void setFields(List<Field> fields) {
        Fields = fields;
    }

    public Field getField(String row, int column) {
        for (Field field : Fields) {
            if (field.row.equals(row) && field.column == column) {
                return field;
            }
        }
        return null;
    }

    public void MoveFigure(Field source, Field target) {
        //System.out.println("Attempting to move figure from source: " + source.row + source.column + " to target: " + target.row + target.column);

        if (source.figure == null) {
            //System.out.println("Error: source.figure is null. Cannot move.");
            return;
        }

        if (target.figure != null && target.figure.figureColor != source.figure.figureColor) {
            //System.out.println("Target figure will be captured: " + target.figure);
            target.figure.position = null;
            target.figure = null;
            //System.out.println("target should be captured");
        } else if (target.figure != null && target.figure.figureColor == source.figure.figureColor) {
            //System.out.println("Error: target.figure is the same color as source.figure. Cannot move.");
            return;
        }

        source.figure.position = target;
        target.figure = source.figure;
        source.figure = null;

        System.out.println("Move completed successfully." + this);
    }

}
