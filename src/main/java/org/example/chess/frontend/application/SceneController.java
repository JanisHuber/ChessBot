package org.example.chess.frontend.application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.chess.backend.controller.ChessController;
import org.example.chess.backend.board.Field;

import java.util.List;
import java.util.Optional;

public class SceneController {
    @FXML
    private AnchorPane anchorPane;

    ChessController chessController = new ChessController(true);
    private boolean gameOver = false;
    private String msg;

    @FXML
    public void initialize() {
        setBackgroundTiles();
        setLabels();
        updateChessBoard(null);
    }

    @FXML
    public void updateChessBoard(List<Field> markedFields) {
        if (gameOver) {
            showGameOver(msg);
            return;
        }
        anchorPane.getChildren().clear();

        setBackgroundTiles();
        setLabels();

        for (int column = 1; column <= 8; column++) {
            for (int row = 1; row <= 8; row++) {
                Field field = chessController.chessBoard.getField(Character.toString((char) (64 + column)), row);
                if (field == null) {
                    continue;
                }

                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                imageView.setLayoutX(column * 50);
                imageView.setLayoutY(row * 50);
                if (markedFields != null) {
                    if (markedFields.contains(field)) {
                        imageView.setImage(new Image(getClass().getResource("/images/possibleMove.png").toExternalForm()));
                        imageView.setOpacity(0.5);
                        imageView.setFitHeight(25);
                        imageView.setFitWidth(25);
                        imageView.setX(imageView.getX() + 12.5);
                        imageView.setY(imageView.getY() + 12.5);
                    }
                }

                imageView.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        List<Field> possibleMoves = chessController.getCheckedMove(field.figure);
                        if (possibleMoves != null) {
                            updateChessBoard(possibleMoves);
                        }
                    }
                });

                imageView.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        applyPlayerMove(event, field);
                    }
                });

                if (field.figure != null) {
                    imageView.setImage(field.figure.image);
                }
                anchorPane.getChildren().add(imageView);
            }
        }
    }

    private void applyPlayerMove(MouseEvent event, Field field) {
        Optional<Boolean> hasMoved;
        updateChessBoard(null);
        double x = (event.getSceneX() / 50);
        double y = (event.getSceneY() / 50);
        Field targetField = chessController.chessBoard.getField(Character.toString((char) (64 + (int) x)), (int) y);
        if (targetField != null) {
            if (targetField != field) {
                hasMoved = chessController.Move(field, targetField);
                if (hasMoved.isEmpty()) {
                    chessController.getStalemateStatus().ifPresentOrElse(
                            stalemate -> {
                                gameOver = true;
                                msg = stalemate ? "Unentschieden!" : "Du hast gewonnen!";
                                showGameOver(msg);
                            },
                            () -> {}
                    );
                } else if (hasMoved.get()) {
                    updateChessBoard(null);
                    Platform.runLater(this::applyBotMove);
                }
            }
        }
    }

    private void applyBotMove() {
        boolean hasBotMoved = chessController.getBotMove();
        if (hasBotMoved) {
            updateChessBoard(null);
            chessController.getStalemateStatus().ifPresentOrElse(stalemate -> {
                if (stalemate) {
                    gameOver = true;
                    msg = "Unentschieden!";
                } else {
                    gameOver = true;
                    msg = "Du hast verloren!";
                    updateChessBoard(null);
                }
            }, () -> {});
        } else {
            gameOver = true;
            msg = "Du hast gewonnen!";
        }
    }

    public void setBackgroundTiles() {
        for (int column = 1; column <= 8; column++) {
            for (int row = 1; row <= 8; row++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                imageView.setLayoutX(column * 50);
                imageView.setLayoutY(row * 50);
                if ((row + column) % 2 == 0) {
                    imageView.setImage(new Image(getClass().getResource("/images/beige_tile.png").toExternalForm()));
                } else {
                    imageView.setImage(new Image(getClass().getResource("/images/brown_tile.png").toExternalForm()));
                }
                anchorPane.getChildren().add(imageView);
            }
        }
    }

    public void setLabels() {
        for (int i = 1; i <= 8; i++) {
            Text columnLabel = new Text(Character.toString((char) (64 + i)));
            columnLabel.setX(i * 50 + 25);
            columnLabel.setY(25);
            anchorPane.getChildren().add(columnLabel);

            Text rowLabel = new Text(Integer.toString(i));
            rowLabel.setX(25);
            rowLabel.setY(i * 50 + 25);
            anchorPane.getChildren().add(rowLabel);
        }
    }

    private void showGameOver(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}