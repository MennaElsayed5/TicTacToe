package controller;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import model.GameSession;
import model.PlayerMove;

/**
 * FXML Controller class
 *
 * @author Eslam Esmael
 */
public class GameBoardComponentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Region region;
    //static boolean challengeComputer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol;

    private String player = "X";
    private boolean winner = false;
    private boolean display = false;
    private boolean firstPlayerWinner = false;
    private boolean secondPlayerWinner = false;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    Color xForeground = Color.BLUE;
    Color oForeground = Color.RED;
    boolean isGameEnds;

    @FXML
    private Pane pane;

    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private GridPane buttonsGrid;
    @FXML
    private Button buttonExit;
    @FXML
    private Label labelScorePlayer1;
    @FXML
    private Label labelScorePlayer2;

    @FXML
    private void buttonOnePressed(ActionEvent event) {
        ((Button) event.getSource()).setDisable(true);
        gameSession.addMove(returnMove((Button) event.getSource()));
        ((Button) event.getSource()).setText(returnSymbol());
        checkState();

    }

    private PlayerMove returnMove(Button btn) {
        PlayerMove move = new PlayerMove();
        if (btn == btn00) {
            move = new PlayerMove(0, 0, isXSymbol);
        } else if (btn == btn01) {
            move = new PlayerMove(0, 1, isXSymbol);
        } else if (btn == btn02) {
            move = new PlayerMove(0, 2, isXSymbol);
        } else if (btn == btn10) {
            move = new PlayerMove(1, 0, isXSymbol);
        } else if (btn == btn11) {
            move = new PlayerMove(1, 1, isXSymbol);
        } else if (btn == btn12) {
            move = new PlayerMove(1, 2, isXSymbol);
        } else if (btn == btn20) {
            move = new PlayerMove(2, 0, isXSymbol);
        } else if (btn == btn21) {
            move = new PlayerMove(2, 1, isXSymbol);
        } else if (btn == btn22) {
            move = new PlayerMove(2, 2, isXSymbol);
        }
        if (btn == buttonExit) {
            System.exit(0);
        }
        return move;
    }

    private String returnSymbol() {
        //  String symbol;
        if (isXSymbol == true) {
            symbol = "X";
            // symbol.setFont(Font.font(18));
        } else {
            symbol = "O";
        }
        isXSymbol = !isXSymbol;
        return symbol;
    }

    private void drawLine(Button b1, Button b2) {
        Bounds bound1 = b1.localToScene(b1.getBoundsInLocal());
        Bounds bound2 = b2.localToScene(b2.getBoundsInLocal());
        double x1, y1, x2, y2;
        x1 = (bound1.getMinX() + bound1.getMaxX()) / 2;
        y1 = (bound1.getMinY() + bound1.getMaxY()) / 2;
        x2 = (bound2.getMinX() + bound2.getMaxX()) / 2;
        y2 = (bound2.getMinY() + bound2.getMaxY()) / 2;
        Line line = new Line(x1, y1, x2, y2);
        pane.getChildren().add(line);

    }

    private void checkRows() {
        if (btn00.getText().equals(btn01.getText()) && btn01.getText().equals(btn02.getText()) && !btn00.getText().equals("")) {
            drawLine(btn00, btn02);
            colorBackgroundWinnerButtons(btn00, btn01, btn02);
            //paneWalid.setDisable(true);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
                //firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                //secondPlayerScore += 10;
            }
            winner = true;
            //  paneWalid.setDisable(true);
        } else if (btn10.getText().equals(btn11.getText())
                && btn11.getText().equals(btn12.getText())
                && !btn10.getText().equals("")) {
            drawLine(btn10, btn12);
            colorBackgroundWinnerButtons(btn10, btn11, btn12);
            //paneWalid.setDisable(true);
            if (btn10.getText().equals("X")) {
                firstPlayerWinner = true;
                //  firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                //  secondPlayerScore += 10;
            }
            winner = true;

        } else if (btn20.getText().equals(btn21.getText())
                && btn21.getText().equals(btn22.getText())
                && !btn22.getText().equals("")) {
            drawLine(btn20, btn22);
            colorBackgroundWinnerButtons(btn20, btn21, btn22);
            //paneWalid.setDisable(true);
            if (btn22.getText().equals("X")) {
                System.out.println("x is winning");

                firstPlayerWinner = true;
                // firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                //  secondPlayerScore += 10;
            }
            winner = true;

        }

    }

    private void checkColumns() {
        if (btn00.getText().equals(btn10.getText())
                && btn10.getText().equals(btn20.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn20);
            colorBackgroundWinnerButtons(btn00, btn10, btn20);
            //paneWalid.setDisable(true);
            if (btn00.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                //  firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                //  secondPlayerScore += 10;
            }
            winner = true;

        } else if (btn01.getText().equals(btn11.getText())
                && btn11.getText().equals(btn21.getText())
                && !btn01.getText().equals("")) {
            drawLine(btn01, btn21);
            colorBackgroundWinnerButtons(btn01, btn11, btn21);
            // paneWalid.setDisable(true);
            if (btn01.getText().equals("X")) {
                firstPlayerWinner = true;
                //   firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                //   secondPlayerScore += 10;
            }
            winner = true;

        } else if (btn02.getText().equals(btn12.getText())
                && btn12.getText().equals(btn22.getText())
                && !btn02.getText().equals("")) {
            drawLine(btn02, btn22);
            colorBackgroundWinnerButtons(btn02, btn12, btn22);
            //   paneWalid.setDisable(true);
            if (btn02.getText().equals("X")) {
                firstPlayerWinner = true;
                //  firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                //  secondPlayerScore += 10;
            }
            winner = true;

        }

    }

    private void checkDiagonal() {
        if (btn00.getText().equals(btn11.getText())
                && btn11.getText().equals(btn22.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn22);
            colorBackgroundWinnerButtons(btn00, btn11, btn22);
            //paneWalid.setDisable(true);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
                //   firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                //  secondPlayerScore += 10;
            }
            winner = true;

        } else if (btn02.getText().equals(btn11.getText())
                && btn11.getText().equals(btn20.getText())
                && !btn02.getText().equals("")) {
            drawLine(btn02, btn20);
            colorBackgroundWinnerButtons(btn02, btn11, btn20);
            //  paneWalid.setDisable(true);
            if (btn02.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                //     firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                //    secondPlayerScore += 10;
            }
            winner = true;

        }

    }

    private boolean isFullGrid() {
        if (!btn00.getText().equals("")
                && !btn01.getText().equals("")
                && !btn02.getText().equals("")
                && !btn10.getText().equals("")
                && !btn11.getText().equals("")
                && !btn12.getText().equals("")
                && !btn20.getText().equals("")
                && !btn21.getText().equals("")
                && !btn22.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void checkState() {
        checkRows();
        checkColumns();
        checkDiagonal();
        if (firstPlayerWinner) {
            disable();
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Congratulations you are winner");
            dialog.setContentText("would you play again ?");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);
            dialogPane.lookupButton(cancelButtonType).setVisible(true);

            Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
            okButton.setAlignment(Pos.CENTER);
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
            Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
            okButton.setAlignment(Pos.CENTER);

            dialog.showAndWait();
            //firstPlayerScore ++;
            //labelScorePlayer1.setText(""+firstPlayerScore);

        } else if (secondPlayerWinner) {
            disable();
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Congratulations you are winner");
            dialog.setContentText("would you play again ?");

            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);
            dialogPane.lookupButton(cancelButtonType).setVisible(true);

            Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
            okButton.setAlignment(Pos.CENTER);
            Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
            okButton.setAlignment(Pos.CENTER);
            dialog.showAndWait();
            //  secondPlayerScore ++;
            //  labelScorePlayer2.setText(""+secondPlayerScore);
        } else {
            if ((isFullGrid())) {
                disable();
                Dialog dialog = new Dialog();
                DialogPane dialogPane = dialog.getDialogPane();
                dialog.setTitle("It's a Draw");
                dialog.setContentText("would you play again ?");

                ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);
                dialogPane.lookupButton(cancelButtonType).setVisible(true);

                Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
                okButton.setAlignment(Pos.CENTER);
                Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
                okButton.setAlignment(Pos.CENTER);
                dialog.showAndWait();

            }
        }
    }

    public void disable() {
        btn00.setDisable(true);
        btn01.setDisable(true);
        btn02.setDisable(true);
        btn10.setDisable(true);
        btn11.setDisable(true);
        btn12.setDisable(true);
        btn20.setDisable(true);
        btn21.setDisable(true);
        btn22.setDisable(true);

    }

    public void colorBackgroundWinnerButtons(Button b1, Button b2, Button b3) {
        b1.setStyle("-fx-background-color: yellow;");
        b2.setStyle("-fx-background-color: yellow;");
        b3.setStyle("-fx-background-color: yellow;");
    }

}
