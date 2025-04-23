package org.example.chess.util;

import org.example.chess.board.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LegalMovesInCheckHelper {

    public final static String KING_CLASS_NAME = "King";

    /**
     * Filters the list of possible moves for a figure when the king is in check.
     * <p>
     * This method determines whether a given figure can help resolve a check against the king
     * by evaluating if it can escape (in case of the king), block the check, or capture the attacking piece.
     * Only moves that contribute to resolving the check are retained.
     * </p>
     *
     * @param figureFields The list of possible fields the figure can move to.
     * @param captures The list of fields where an attacking piece can be captured.
     * @param blocks The list of fields that could block the check.
     * @param escapes The list of safe fields the king can escape to.
     * @param figure The figure for which to filter legal moves.
     * @param possibleCaptureSources A list of own figures that are allowed to capture.
     * @param possibleBlockSources A list of own figures that are allowed to block.
     *
     * @return A filtered list of legal moves that resolve the check, or {@code null} if the figure cannot assist.
     */
    public static List<Field> resolveLegalMoves(List<Field> figureFields, List<Field> captures, List<Field> blocks, List<Field> escapes, ChessFigure figure, List<Field> possibleCaptureSources, List<Field> possibleBlockSources) {
        boolean canCapture = !captures.isEmpty();
        boolean canBlock = !blocks.isEmpty();
        boolean canEscape = !escapes.isEmpty();

        List<String> possibleCaptureSourcesString = turnFieldsToFigureNames(possibleCaptureSources);
        List<String> possibleBlockSourcesString = turnFieldsToFigureNames(possibleBlockSources);

        List<Field> sourceList = figureFields;

        if (!canCapture && !canBlock && !canEscape) {
            return null;
        }

        if (canEscape && !canBlock && !canCapture) {
            if (figure.getClassName().equals(KING_CLASS_NAME))
            {
                sourceList.retainAll(escapes);
            } else {
                return null;
            }
        }

        if (!canEscape && canBlock && !canCapture) {
            if (possibleBlockSourcesString.contains(figure.getClassName()) && !Objects.equals(figure.getClassName(), KING_CLASS_NAME))
            {
                //Is bug fixed?
                System.out.println(figure.getClassName());
                sourceList.retainAll(blocks);
            } else {
                return null;
            }
        }

        if (!canEscape && !canBlock && canCapture) {
            if (possibleCaptureSourcesString.contains(figure.getClassName()))
            {
                sourceList.retainAll(captures);
            } else {
                return null;
            }
        }

        if (!canEscape && canBlock && canCapture) {
            if (possibleBlockSourcesString.contains(figure.getClassName()) || possibleCaptureSourcesString.contains(figure.getClassName()))
            {
                retainTwoLists(sourceList, blocks, captures);
            } else {
                return null;
            }
        }

        if (canEscape && !canBlock && canCapture) {
            if (possibleCaptureSourcesString.contains(figure.getClassName()) || figure.getClassName().equals(KING_CLASS_NAME))
            {
                if (figure.getClassName().equals(KING_CLASS_NAME)) {
                    sourceList.retainAll(escapes);
                } else {
                    sourceList.retainAll(captures);
                }
            } else {
                return null;
            }
        }

        if (canEscape && canBlock && canCapture) {
            if (possibleBlockSourcesString.contains(figure.getClassName()) || possibleCaptureSourcesString.contains(figure.getClassName()) || figure.getClassName().equals(KING_CLASS_NAME))
            {
                if (figure.getClassName().equals(KING_CLASS_NAME)) {
                    sourceList.retainAll(escapes);
                } else {
                    sourceList = retainTwoLists(sourceList, blocks, captures);
                }
            } else {
                return null;
            }
        }

        if (canEscape && canBlock && !canCapture) {
            if (possibleBlockSourcesString.contains(figure.getClassName()) || figure.getClassName().equals(KING_CLASS_NAME)) {
                if (figure.getClassName().equals(KING_CLASS_NAME)) {
                    sourceList.retainAll(escapes);
                } else {
                    sourceList.retainAll(blocks);
                }
            } else {
                return null;
            }
        }
        return sourceList;
    }

    /**
     * Converts a list of fields to a list of figure names.
     *
     * @param fields The list of fields to convert.
     * @return A list of figure names corresponding to the figures in the fields.
     */
    private static List<String> turnFieldsToFigureNames(List<Field> fields) {
        List<String> figureNames = new ArrayList<>();
        for (Field field : fields) {
            if (field.figure == null) {
                continue;
            }
            figureNames.add(field.figure.getClassName());
        }
        return figureNames;
    }

    /**
     * joins elements from the source list that are present in either of the two provided lists.
     *
     * @param sourceList The original list to filter.
     * @param list1 The first list to compare against.
     * @param list2 The second list to compare against.
     * @return The modified source list containing elements present in either list1 or list2.
     */
    private static List<Field> retainTwoLists(List<Field> sourceList, List<Field> list1, List<Field> list2) {
        List<Field> double1 = new ArrayList<>();
        List<Field> double2 = new ArrayList<>();

        double1.addAll(sourceList);
        double1.retainAll(list1);

        double2.addAll(sourceList);
        double2.retainAll(list2);

        sourceList.clear();
        sourceList.addAll(double1);
        sourceList.addAll(double2);

        return sourceList;
    }
}
