/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.PlayAgainDialogBuilder;
import helper.WinnerAndLoserVideoBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.GameSession;
import model.PlayerMove;

/**
 *
 * @author mina
 */
public class PlayerVsAIViewController implements Initializable {

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol = "X";
    PlayerMove[] playersMoves = new PlayerMove[9];
    int counter = 0;

    private String player = "X";
    private boolean winner = false;
    private boolean display = false;
    private boolean firstPlayerWinner = false;
    private boolean secondPlayerWinner = false;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    private Preferences pref;

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
    private Pane pane;

    @FXML
    private Label scorePlayerOne;

    @FXML
    private Label computerScore;

    @FXML
    private Button restartButton;

    @FXML
    private void buttonBackPressed(ActionEvent event) {
        SceneNavigationController controller = new SceneNavigationController();

        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buttonOnePressed(ActionEvent event) {
        ((Button) event.getSource()).setDisable(true);
        // disableAllButtons(true);
        gameSession.addMove(returnMove((Button) event.getSource()));
        playersMoves[counter++] = returnMove((Button) event.getSource());
        System.out.println("counter after " + counter);
        ((Button) event.getSource()).setText(symbol);

        try {

            checkState();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PlayerMove newAiMove = aiMove();
        gameSession.addMove(newAiMove);
        setAIText(newAiMove.getX(), newAiMove.getY());
        playersMoves[counter++] = newAiMove;

        try {
            checkState();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setAIText(int x, int y) {
        if (x == 0 && y == 0) {
            btn00.setText("O");
            btn00.setDisable(true);
        }

        if (x == 0 && y == 1) {
            btn01.setText("O");
            btn01.setDisable(true);
        }
        if (x == 0 && y == 2) {
            btn02.setText("O");
            btn02.setDisable(true);
        }
        if (x == 1 && y == 0) {
            btn10.setText("O");
            btn10.setDisable(true);
        }
        if (x == 1 && y == 1) {
            btn11.setText("O");
            btn11.setDisable(true);
        }
        if (x == 1 && y == 2) {
            btn12.setText("O");
            btn12.setDisable(true);
        }
        if (x == 2 && y == 0) {
            btn20.setText("O");
            btn20.setDisable(true);
        }
        if (x == 2 && y == 1) {
            btn21.setText("O");
            btn21.setDisable(true);
        }
        if (x == 2 && y == 2) {
            btn22.setText("O");
            btn22.setDisable(true);
        }
    }

    public boolean checkMoveFound(int x, int y) {
        for (int j = 0; j < counter; j++) {
            if (x == playersMoves[j].getX() && y == playersMoves[j].getY()) {
                return true;
            }
        }
        return false;
    }

    public void disableAllButtons(boolean b) {
        btn00.setDisable(b);
        btn01.setDisable(b);
        btn02.setDisable(b);
        btn10.setDisable(b);
        btn11.setDisable(b);
        btn12.setDisable(b);
        btn20.setDisable(b);
        btn21.setDisable(b);
        btn22.setDisable(b);
    }

    public void enableEmptyButtons(boolean b) {
        System.out.println("mina enable");
        if (btn00.getText().equals("")) {
            btn00.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn01.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn02.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn10.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn11.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn12.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn20.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn21.setDisable(!b);
        }
        if (btn00.getText().equals("")) {
            btn22.setDisable(!b);
        }
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
        return move;
    }

    public PlayerMove aiMove() {
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        if (counter < 9) {
            while (checkMoveFound(x, y)) {
                x = r.nextInt(3);
                y = r.nextInt(3);
            }
        }
        return new PlayerMove(x, y, false);
    }

    public void clearAllVariales() {
        counter = 0;
        for (int i = 0; i < 9; i++) {
            playersMoves[i] = null;
        }
        winner = false;
        clearAllButtons();
        display = false;
        firstPlayerWinner = false;
        secondPlayerWinner = false;
        gameSession = null;
        gameSession = new GameSession();
    }

    public void clearAllButtons() {
        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
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
        if (btn00.getText().equals(btn01.getText())
                && btn01.getText().equals(btn02.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn02);
            colorBackgroundWinnerButtons(btn00, btn01, btn02);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn10.getText().equals(btn11.getText())
                && btn11.getText().equals(btn12.getText())
                && !btn10.getText().equals("")) {
            drawLine(btn10, btn12);
            colorBackgroundWinnerButtons(btn10, btn11, btn12);
            if (btn10.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn20.getText().equals(btn21.getText())
                && btn21.getText().equals(btn22.getText())
                && !btn22.getText().equals("")) {
            drawLine(btn20, btn22);
            colorBackgroundWinnerButtons(btn20, btn21, btn22);
            if (btn22.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
        if (winner) {
            disableAllButtons(true);
        }
    }

    private void checkColumns() {
        if (btn00.getText().equals(btn10.getText())
                && btn10.getText().equals(btn20.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn20);
            colorBackgroundWinnerButtons(btn00, btn10, btn20);
            if (btn00.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn01.getText().equals(btn11.getText())
                && btn11.getText().equals(btn21.getText())
                && !btn01.getText().equals("")) {
            drawLine(btn01, btn21);
            colorBackgroundWinnerButtons(btn01, btn11, btn21);
            if (btn01.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn02.getText().equals(btn12.getText())
                && btn12.getText().equals(btn22.getText())
                && !btn02.getText().equals("")) {
            drawLine(btn02, btn22);
            colorBackgroundWinnerButtons(btn02, btn12, btn22);
            if (btn02.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
        if (winner) {
            disableAllButtons(true);
        }
    }

    private void checkDiagonal() {
        if (btn00.getText().equals(btn11.getText())
                && btn11.getText().equals(btn22.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn22);
            colorBackgroundWinnerButtons(btn00, btn11, btn22);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn02.getText().equals(btn11.getText())
                && btn11.getText().equals(btn20.getText())
                && !btn02.getText().equals("")) {
            drawLine(btn02, btn20);
            colorBackgroundWinnerButtons(btn02, btn11, btn20);
            if (btn02.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
        if (winner) {
            disableAllButtons(true);
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

    private void checkState() throws BackingStoreException {
        checkRows();
        checkColumns();
        checkDiagonal();
        if (firstPlayerWinner) {
            System.out.println("X is win");
            scorePlayerOne.setText(String.valueOf(firstPlayerScore));
            pref.putInt("firstPlayerScore", firstPlayerScore);
            new WinnerAndLoserVideoBuilder(firstPlayerWinner).display();
            replayAgain("You Won!");
        } else if (secondPlayerWinner) {
            new WinnerAndLoserVideoBuilder(firstPlayerWinner).display();
            System.out.println("O is win");
            computerScore.setText(String.valueOf(secondPlayerScore));
            pref.putInt("secondPlayerScore", secondPlayerScore);
            replayAgain("You Lose!");
        } else {
            if ((isFullGrid())) {
                System.out.println("It's a Draw");
                replayAgain("Draw");
            }
        }
    }

    public void replayAgain(String winner) throws BackingStoreException {

        boolean result = PlayAgainDialogBuilder.askPlayAgain(winner);
        if (result) {
            clearAllVariales();

            //get scene
            Parent buttonParent;
            try {
                buttonParent = FXMLLoader.load(getClass().getResource("/view/PlayerVsAIView.fxml"));
                //generate new scene
                Scene buttonScene = new Scene(buttonParent);
                //get stage information
                Stage window = (Stage) btn00.getScene().getWindow();
                window.setTitle("Home");
                window.setScene(buttonScene);
                window.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            disableAllButtons(true);
            pref.clear();
            clearAllVariales();

            //TODO navigate to main to main menu ya 5elan portsaid
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initPrefPlayer();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initPrefPlayer() throws BackingStoreException {

        pref = Preferences.userNodeForPackage(PlayerVsAIViewController.class);
        if (pref.nodeExists("")) {
            String fristplayerName = pref.get("fristPlayer", "");
            String secondPlayerName = pref.get("Computer", "");
            firstPlayerScore = pref.getInt("firstPlayerScore", 0);
            secondPlayerScore = pref.getInt("Computer", 0);
            if (firstPlayerScore > 0 || secondPlayerScore > 0) {
                scorePlayerOne.setText(String.valueOf(firstPlayerScore));
                computerScore.setText(String.valueOf(secondPlayerScore));
            }

        }
    }

    public void colorBackgroundWinnerButtons(Button b1, Button b2, Button b3) {
        b1.setStyle("-fx-background-color: yellow;");
        b2.setStyle("-fx-background-color: yellow;");
        b3.setStyle("-fx-background-color: yellow;");
    }

}
