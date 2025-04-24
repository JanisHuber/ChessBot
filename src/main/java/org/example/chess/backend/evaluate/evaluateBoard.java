package org.example.chess.backend.evaluate;

import org.example.chess.backend.board.Field;
import org.example.chess.backend.bot.ChessBot;
import org.example.chess.backend.controller.ChessController;
import org.example.chess.backend.enums.FigureColor;
import org.example.chess.backend.figures.Pawn;
import org.example.chess.backend.util.LoggingToFile;
import org.example.chess.backend.util.Move;

import java.util.List;
import java.util.logging.Logger;

public class evaluateBoard {
    public static int evaluateBoard(ChessController controller, Logger logger) {
        int pieceValue = getPieceValue(controller);
        int positionValue = getPawnPositionValue(controller);
        int mobilityValue = getMobilityValue(controller);
        int checkmateValue = checkmateValue(controller);

        logger.info("Estimated Board with Values:");
        logger.info("Piece Value: " + pieceValue);
        logger.info("Position Value: " + positionValue);
        logger.info("Mobility Value: " + mobilityValue);

        return
                checkmateValue * 5 +
                pieceValue * 3 +
                mobilityValue * 2 +
                positionValue * 3;
    }

    private static int getMobilityValue(ChessController chessController) {
        int value = 0;
        for (Field field : chessController.chessBoard.getFields()) {
            if (field.figure != null) {
                List<Field> possibleMoves = field.figure.getPossibleMoves();
                value += possibleMoves.size();
            }
        }
        return value / 7;
    }

    private static int getPieceValue(ChessController chessController) {
        int value = 0;
        for (Field field : chessController.chessBoard.getFields()) {
            if (field.figure != null) {
                switch (field.figure.getClassName()) {
                    case "Pawn":
                        if (field.figure.figureColor == FigureColor.WHITE) {
                            value += 10;
                        } else {
                            value -= 10;
                        }
                        break;
                    case "Rook":
                        if (field.figure.figureColor == FigureColor.WHITE) {
                            value += 50;
                        } else {
                            value -= 50;
                        }
                        break;
                    case "Knight", "Bishop":
                        if (field.figure.figureColor == FigureColor.WHITE) {
                            value += 30;
                        } else {
                            value -= 30;
                        }
                        break;
                    case "Queen":
                        if (field.figure.figureColor == FigureColor.WHITE) {
                            value += 90;
                        } else {
                            value -= 90;
                        }
                        break;
                    case "King":
                        if (field.figure.figureColor == FigureColor.WHITE) {
                            value += 10000;
                        } else {
                            value -= 10000;
                        }
                        break;
                }
            }
        }
        return value;
    }

    private static int getPawnPositionValue(ChessController chessController) {
        int value = 0;
        int[][] centralFields = { {4, 4}, {4, 5}, {5, 4}, {5, 5} };

        for (Field field : chessController.chessBoard.getFields()) {
            if (field.figure instanceof Pawn pawn) {
                int row = convertRowToInt(pawn.position.row);
                int col = pawn.position.column;

                value += (pawn.figureColor == FigureColor.WHITE ? row : (9 - row));

                if (isCentralField(row, col, centralFields)) {
                    value += 20;
                    if (isPawnProtected(field, chessController)) {
                        value += 10;
                    }
                }
            }
        }
        return value / 5;
    }

    private static int convertRowToInt(String row) {
        return row.charAt(0) - 'A' + 1;
    }

    private static boolean isCentralField(int row, int col, int[][] centralFields) {
        for (int[] centralField : centralFields) {
            if (centralField[0] == row && centralField[1] == col) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPawnProtected(Field field, ChessController chessController) {
        for (Field currentField : chessController.chessBoard.getFields()) {
            if (currentField == field) {
                continue;
            }
            if (currentField.figure != null) {
                if (currentField.figure.figureColor == field.figure.figureColor) {
                    List<Field> possibleMoves = currentField.figure.getPossibleMoves();
                    if (possibleMoves.contains(field)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int checkmateValue(ChessController chessController) {
        int counterWhite = 0;
        int counterBlack = 0;

        for (Field field : chessController.chessBoard.getFields()) {
            if (field.figure != null) {
                if (chessController.getCheckMoveHandler().getCheckedMove(field.figure) == null) {
                    break;
                }
                if (!chessController.getCheckMoveHandler().getCheckedMove(field.figure).isEmpty()) {
                    if (field.figure.figureColor == FigureColor.WHITE) {
                        counterWhite++;
                    } else {
                        counterBlack++;
                    }
                }
            }
        }
        if (counterWhite == 0 && counterBlack == 0) {
            return 0;
        } else if (counterWhite == 0) {
            System.out.println("White checkmate detected");
            return -100000;
        } else if (counterBlack == 0) {
            System.out.println("Black checkmate detected");
            return 100000;
        }
        return 0;
    }
}
