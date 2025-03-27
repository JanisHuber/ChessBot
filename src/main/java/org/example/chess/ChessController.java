package org.example.chess;
import javafx.scene.image.Image;
import org.example.chess.figures.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessController implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChessBoard chessBoard;
    public FigureColor currentTurn = FigureColor.WHITE;
    public List<ChessFigure> WhiteFigures;
    public List<ChessFigure> BlackFigures;
    public List<ChessFigure> DeadWhiteFigures;
    public List<ChessFigure> DeadBlackFigures;

    public List<Field> possibleCaptureSources;    //Source to Capture
    public List<Field> possibleBlockSources;      //Source to Block

    boolean canEscape = false;
    boolean canCapture = false;
    boolean canBlock = false;


    boolean publicAgainstAI = false;
    public transient ChessBot bot;

    public ChessController(boolean againstAI)
    {
        publicAgainstAI = againstAI;
        Initialize();
        if (publicAgainstAI) {
            Move botMove = bot.getBestMove(this);
            Move(botMove.getSource(chessBoard), botMove.getTarget(chessBoard));
        }

        //Move((botMove.getSource()), botMove.getTarget());

    }

    public ChessBoard getClonedBoard() {
        return SerializationUtil.deepClone(chessBoard);
    }

    public boolean getBotMove() {
        if (publicAgainstAI && currentTurn == FigureColor.WHITE) {
            Move botMove = bot.getBestMove(this);
            System.out.println(botMove.getSource(chessBoard).row + botMove.getSource(chessBoard).column);
            System.out.println(botMove.getTarget(chessBoard).row + botMove.getTarget(chessBoard).column);
            Move(botMove.getSource(chessBoard), botMove.getTarget(chessBoard));
            return true;
        }
        return false;
    }

    public boolean Move(Field currentField, Field target)
    {
        List<Field> fields = getCheckedMove(currentField.figure);
        if (fields == null)
        {
            return false;
        }
        if (!fields.contains(target))
        {
            return false;
        } else {
            if (target.figure != null)
            {
                if (target.figure.figureColor == FigureColor.WHITE) //add Dead Figure
                {
                    DeadWhiteFigures.add(target.figure);
                    WhiteFigures.remove(target.figure);
                } else {
                    DeadBlackFigures.add(target.figure);
                    BlackFigures.remove(target.figure);
                }
                target.figure.position = null;          //del targetFigurePosition
                currentField.figure.position = target;  //Figure update
                target.figure = currentField.figure;    //Field update
                currentField.figure = null;
                if (currentTurn == FigureColor.WHITE)   // Change Turn
                {
                    currentTurn = FigureColor.BLACK;
                } else {
                    currentTurn = FigureColor.WHITE;
                }
            }
            else { //No Target Figure
                currentField.figure.position = target;  //Figure update
                target.figure = currentField.figure;    //Field update
                currentField.figure = null;
                if (currentTurn == FigureColor.WHITE)   // Change Turn
                {
                    currentTurn = FigureColor.BLACK;
                } else {
                    currentTurn = FigureColor.WHITE;
                }
            }
            return true;
        }
    }

    public List<Field> getCheckedMove(ChessFigure figure)
    {
        canEscape = false; // Reset all
        canCapture = false;
        canBlock = false;
        List<Field> finalFields = new ArrayList<>();
        List<Field> DOUBLE1 = new ArrayList<>(); // For Double retain cases
        List<Field> DOUBLE2 = new ArrayList<>(); // For Double retain cases

        if (figure.figureColor != currentTurn) //Wrong turn sorted out
        {
            return null;
        }

        List<Field> figureFields = figure.getPossibleMoves();

        int checkmateCounter = isCheckmate(null);   //Check for Checkmate and save the amount of possible mates
        System.out.println(checkmateCounter);               //Debug
        if (checkmateCounter > 0)                           //is check?
        {
            List<Field> escapesBool = canEscape();      //Get all possible escapes & works as boolean
            List<Field> capturesBool = canCapture();    //Get all possible captures & works as boolean
            List<Field> blocksBool = canBlock();        //Get all possible blocks & works as boolean

            if (!escapesBool.isEmpty()) {
                System.out.println("CanEscape");    //Debug
                canEscape = true; //TO DO remove other Moves
            } else {
                canEscape = false;
            }
            if (!capturesBool.isEmpty()) {
                System.out.println("CanCapture");   //Debug
                canCapture = true;
            } else {
                canCapture = false;
            }
            if (!blocksBool.isEmpty()) {
                System.out.println("CanBlock");     //Debug
                canBlock = true;
            } else {
                canBlock = false;
            }
            if (capturesBool.isEmpty() && blocksBool.isEmpty() && escapesBool.isEmpty()) { // Hope lost - Checkmate
                System.out.println("Checkmate");
                return null;
            }
        }

        //Scenarios
        //-- canEscape !canBlock !canCapture SINGLE done
        //-- canEscape !canBlock canCapture DOUBLE done
        //-- canEscape canBlock canCapture DOUBLE done
        //-- canEscape canBlock !canCapture DOUBLE done

        //-- !canEscape canBlock !canCapture SINGLE done
        //-- !canEscape !canBlock canCapture SINGLE done
        //-- !canEscape canBlock canCapture DOUBLE done


        //Only show relevant moves if under check
        List<String> possibleCaptureSourcesString = new ArrayList<>();
        for (Field field : possibleCaptureSources)
        {
            if (field.figure == null)
            {
                continue;
            }
            possibleCaptureSourcesString.add(field.figure.getClassName());
        }
        List<String> possibleBlockSourcesString = new ArrayList<>();
        for (Field field : possibleBlockSources)
        {
            if (field.figure == null)
            {
                continue;
            }
            possibleBlockSourcesString.add(field.figure.getClassName());
        }

        List<Field> escapes = canEscape();
        List<Field> captures = canCapture();
        List<Field> blocks = canBlock();

        // Scenarios ------------------------------------------------
        if (canEscape && !canBlock && !canCapture) { //-- canEscape !canBlock !canCapture SINGLE
            if (figure.getClassName().equals("King"))
            {
                figureFields.retainAll(escapes);
            } else {
                return null;
            }
        }

        if (!canEscape && canBlock && !canCapture) { //-- !canEscape canBlock !canCapture SINGLE
            if (possibleBlockSourcesString.contains(figure.getClassName()) && !Objects.equals(figure.getClassName(), "King")) //King cant block
            {
                figureFields.retainAll(blocks);
            } else {
                return null;
            }
        }

        if (!canEscape && !canBlock && canCapture) { //-- !canEscape !canBlock canCapture SINGLE
            if (possibleCaptureSourcesString.contains(figure.getClassName()))
            {
                figureFields.retainAll(captures);
            } else {
                return null;
            }
        }

        if (!canEscape && canBlock && canCapture) {//-- !canEscape canBlock canCapture DOUBLE
            if (possibleBlockSourcesString.contains(figure.getClassName()) || possibleCaptureSourcesString.contains(figure.getClassName()))
            {
                DOUBLE1.addAll(figureFields);
                DOUBLE1.retainAll(blocks);

                DOUBLE2.addAll(figureFields);
                DOUBLE2.retainAll(captures);

                figureFields.clear();
                figureFields.addAll(DOUBLE1); // Final Results
                figureFields.addAll(DOUBLE2);
            } else {
                return null;
            }
        }

        if (canEscape && !canBlock && canCapture) { //-- canEscape !canBlock canCapture DOUBLE
            if (possibleCaptureSourcesString.contains(figure.getClassName()) || figure.getClassName().equals("King"))
            {
                if (figure.getClassName().equals("King")) {
                    figureFields.retainAll(escapes);
                } else {
                    figureFields.retainAll(captures);
                }
            } else {
                return null;
            }
        }

        if (canEscape && canBlock && canCapture) { //-- canEscape canBlock canCapture TRIPLE
            if (possibleBlockSourcesString.contains(figure.getClassName()) || possibleCaptureSourcesString.contains(figure.getClassName()) || figure.getClassName().equals("King"))
            {
                if (figure.getClassName().equals("King")) {
                    figureFields.retainAll(escapes);
                } else {
                    DOUBLE1.addAll(figureFields);
                    DOUBLE1.retainAll(blocks);

                    DOUBLE2.addAll(figureFields);
                    DOUBLE2.retainAll(captures);

                    figureFields.clear();
                    figureFields.addAll(DOUBLE1); // Final Results
                    figureFields.addAll(DOUBLE2);
                }

            } else {
                return null;
            }
        }

        if (canEscape && canBlock && !canCapture) { //-- canEscape canBlock !canCapture DOUBLE
            if (possibleBlockSourcesString.contains(figure.getClassName()) || figure.getClassName().equals("King")) {
                if (figure.getClassName().equals("King")) {
                    figureFields.retainAll(escapes);
                } else {
                    figureFields.retainAll(blocks);
                }
            } else {
                return null;
            }
        }


        // Check if move is opening any checks
        if (checkmateCounter == 0) {
            List<Field> invalidMoves = new ArrayList<>();

            for (Field targetField : figureFields) {
                ChessFigure originalTargetFigure = targetField.figure;
                boolean isInCheck;

                Field figureSourceField = chessBoard.getField(figure.position.row, figure.position.column);

                figureSourceField.figure = null;
                if (figureSourceField.figure != null) {
                    figureSourceField.figure.position = null;
                }

                targetField.figure = figure;
                figure.position = targetField;

                // Check for any checks
                isInCheck = isCheckmate(null) > 0;

                // Reset changes
                targetField.figure = originalTargetFigure;
                if (originalTargetFigure != null) {
                    originalTargetFigure.position = targetField;
                }

                figureSourceField.figure = figure;
                figure.position = figureSourceField;

                if (isInCheck) {
                    invalidMoves.add(targetField);
                }
            }
            figureFields.removeAll(invalidMoves);
        }


        return figureFields;
    }

    public void newGame()
    {
        Initialize();
    }

    private void Initialize() {
        chessBoard = new ChessBoard();
        List<Field> fields = new ArrayList<>();


        this.WhiteFigures = new ArrayList<>();
        this.BlackFigures = new ArrayList<>();
        this.DeadWhiteFigures = new ArrayList<>();
        this.DeadBlackFigures = new ArrayList<>();

        this.possibleCaptureSources = new ArrayList<>();
        this.possibleBlockSources = new ArrayList<>();

        if (publicAgainstAI) {
            bot = new ChessBot(3);
        }

        for (int i = 1; i <= 8; i++)
        {
            for (int j = 1; j <= 8; j++)
            {
                Field f = new Field();
                f.row = Character.toString((char)(64 + 9 - i));
                f.column = j;

                if ((f.row.equals("H") || f.row.equals("A")) && (j == 1 || j == 8)) // Rook
                {
                    f.figure = new Rook();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 1) {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_rook.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_rook.png").toExternalForm());
                    }
                }

                if (j == 2 || j == 7) // Pawn
                {
                    f.figure = new Pawn();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 2) {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_pawn.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_pawn.png").toExternalForm());
                    }
                }

                if ((j == 8 || j == 1) && (i == 2 || i == 7)) // Knight
                {
                    f.figure = new Knight();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 1)
                    {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_knight.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_knight.png").toExternalForm());
                    }
                }

                if ((j == 8 || j == 1) && (i == 3 || i == 6)) // Bishop
                {
                    f.figure = new Bishop();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 1)
                    {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_bishop.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_bishop.png").toExternalForm());
                    }
                }

                if ((j == 8 || j == 1) && i == 4) // Queen
                {
                    f.figure = new Queen();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 1)
                    {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_queen.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_queen.png").toExternalForm());
                    }
                }

                if ((j == 8 || j == 1) && i == 5) // King
                {
                    f.figure = new King();
                    f.figure.position = f;
                    f.figure.chessBoard = chessBoard;
                    if (j == 1)
                    {
                        f.figure.figureColor = FigureColor.WHITE;
                        f.figure.image = new Image(getClass().getResource("/images/figure_white_king.png").toExternalForm());
                    } else {
                        f.figure.figureColor = FigureColor.BLACK;
                        f.figure.image = new Image(getClass().getResource("/images/figure_black_king.png").toExternalForm());
                    }
                }

                fields.add(f);
            }
        }
        chessBoard.setFields(fields);
    }


    public List<Field> getPossiblesMates(Field kingField) {
        List<Field> possibleMates = new ArrayList<>();
        List<Field> currentCheckingFields = new ArrayList<>();
        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                if (kingField == null) {
                    kingField = field;
                }
            }
            else if (field.figure.figureColor != currentTurn) {
                currentCheckingFields.add(field);
            }
        }
        for (Field field : currentCheckingFields) {
            for (Field figureField : field.figure.getPossibleMoves()) { // go trough all possible Moves
                if (figureField == kingField) {
                    possibleMates.add(field);
                }
            }
        }

        return possibleMates;
    }

    public int isCheckmate(Field kingField) {
        if (kingField == null) {
            for (Field field : chessBoard.Fields) {
                if (field.figure == null) {
                    continue;
                }
                String figureType = field.figure.getClassName();
                if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                    kingField = field;
                }
            }
        }
        List<Field> possibleMates = getPossiblesMates(kingField);
        return possibleMates.size();
    }

    private List<Field> canCapture() {
        possibleCaptureSources.clear();
        List<Field> possibleCaptures = new ArrayList<>();
        List<Field> possibleMates = getPossiblesMates(null);
        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            if (field.figure.figureColor == currentTurn) {
                for (Field mate : possibleMates) {
                    for (Field figureField : field.figure.getPossibleMoves()) {
                        if (figureField == mate) {
                            ChessFigure originalFigure = figureField.figure;
                            figureField.figure = field.figure;
                            field.figure.position = figureField;
                            boolean isOutOfCheck = isCheckmate(null) == 0;

                            figureField.figure = originalFigure;
                            field.figure.position = field;
                            if (isOutOfCheck) {
                                possibleCaptures.add(figureField);
                                possibleCaptureSources.add(field);
                            }
                        }
                    }
                }
            }
        }
        return possibleCaptures;
    }

    private List<Field> canBlock() {
        possibleBlockSources.clear();
        List<Field> possibleBlocks = new ArrayList<>();
        List<Field> blocks = new ArrayList<>();
        Field kingField = null;
        ChessFigure originalFigure;
        boolean isOutOfCheck = false;

        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                kingField = field;
                continue;
            }
            if (field.figure.figureColor != currentTurn) {
                continue;
            }
            for (Field possibleMove : field.figure.getPossibleMoves()) {
                isOutOfCheck = false;
                if (possibleMove.figure != null) {
                    originalFigure = possibleMove.figure;
                } else {
                    originalFigure = null;
                }
                possibleMove.figure = field.figure;
                possibleMove.figure.position = possibleMove;

                isOutOfCheck = isCheckmate(null) == 0;

                field.figure.position = field;
                possibleMove.figure = originalFigure;
                if (isOutOfCheck) {
                    blocks.add(possibleMove);
                    possibleBlockSources.add(field);
                }
            }
        }

        return blocks;
    }

    private List<Field> canEscape() {
        List<Field> possibleEscapes = new ArrayList<>();
        List<Field> escapes = new ArrayList<>();
        Field kingField = null;
        boolean isOutOfCheck = false;

        for (Field field : chessBoard.Fields) {
            if (field.figure == null) {
                continue;
            }
            String figureType = field.figure.getClassName();
            if (figureType.equals("King") && field.figure.figureColor == currentTurn) {
                kingField = field;
            }
        }
        possibleEscapes = kingField.figure.getPossibleMoves();
        for (Field field : possibleEscapes) {
            isOutOfCheck = false;
            ChessFigure king = kingField.figure;
            ChessFigure captured = field.figure;

            field.figure = king;
            kingField.figure = null;
            king.position = field;

            isOutOfCheck = isCheckmate(field) == 0;

            king.position = kingField;
            kingField.figure = king;
            field.figure = captured;

            if (isOutOfCheck) {
                escapes.add(field);
            }
        }
        return escapes;
    }
}
