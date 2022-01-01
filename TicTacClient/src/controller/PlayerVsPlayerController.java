package controller;

import helper.PlayAgainDialogBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.GameSession;
import model.PlayerMove;

/**
 *
 * @author Eslam Esmael
 */
public class PlayerVsPlayerController implements Initializable {

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
    private BorderPane borderPane;

    @FXML
    private Label namePlayerOne;

    @FXML
    private Label namePlayerTwo;

    @FXML
    private Label scorePlayerOne;

    @FXML
    private Label scorePlayerTwo;

    @FXML
    private Button restartButton;

    @FXML
    private void buttonBackPressed(ActionEvent event) {
        SceneNavigationController controller = new SceneNavigationController();
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buttonOnePressed(ActionEvent event) {
        ((Button) event.getSource()).setDisable(true);
        gameSession.addMove(returnMove((Button) event.getSource()));
        ((Button) event.getSource()).setText(returnSymbol());
        try {
            checkState();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
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

    private String returnSymbol() {
        String symbol;
        if (isXSymbol == true) {
            symbol = "X";
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
        borderPane.getChildren().add(line);
    }

    private void checkRows() {
        if (btn00.getText().equals(btn01.getText())
                && btn01.getText().equals(btn02.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn02);
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
    }

    private void checkColumns() {
        if (btn00.getText().equals(btn10.getText())
                && btn10.getText().equals(btn20.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn20);
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
            if (btn02.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
    }

    private void checkDiagonal() {
        if (btn00.getText().equals(btn11.getText())
                && btn11.getText().equals(btn22.getText())
                && !btn00.getText().equals("")) {
            drawLine(btn00, btn22);
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

            replayAgain("first player");

        } else if (secondPlayerWinner) {
            System.out.println("O is win");
            scorePlayerTwo.setText(String.valueOf(secondPlayerScore));

            pref.putInt("secondPlayerScore", secondPlayerScore);
            replayAgain("second player");

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
                buttonParent = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
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
            pref.clear();
            clearAllVariales();
        }

    }

    public void clearAllVariales() {
        gameSession.setCounter(0);
        for (int i = 0; i < 9; i++) {
            gameSession.getPlayersMoves()[i] = null;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initPrefPlayer();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initPrefPlayer() throws BackingStoreException {

        pref = Preferences.userNodeForPackage(PlayerVsPlayerController.class);
        if (pref.nodeExists("")) {
            String fristplayerName = pref.get("fristPlayer", "");
            String secondPlayerName = pref.get("secondPlayer", "");
            firstPlayerScore = pref.getInt("firstPlayerScore", 0);
            secondPlayerScore = pref.getInt("secondPlayerScore", 0);
            if (firstPlayerScore > 0 || secondPlayerScore > 0) {
                scorePlayerOne.setText(String.valueOf(firstPlayerScore));
                scorePlayerTwo.setText(String.valueOf(secondPlayerScore));
            }

        }
    }

    public void setNames(String name1, String name2) {
        namePlayerOne.setText(name1);
        namePlayerTwo.setText(name2);
    }

}
