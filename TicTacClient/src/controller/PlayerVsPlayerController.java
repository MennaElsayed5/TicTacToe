package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Eslam Esmael
 */
public class PlayerVsPlayerController implements Initializable {

    @FXML
    AnchorPane anchorPane;
    Parent root;
    static String playerOneName;
    static String playerTwoName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameBoardComponentController.previousView = "player";
        GameBoardComponentController.playerOneName = playerOneName;
        GameBoardComponentController.playerTwoName = playerTwoName;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/GameBoardComponent.fxml"));
            anchorPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
