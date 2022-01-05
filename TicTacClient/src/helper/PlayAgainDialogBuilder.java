package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Eslam Esmael
 */
public class PlayAgainDialogBuilder {

    public static boolean askPlayAgain(String s) {
        ButtonType Yes = new ButtonType("Play Again");
        ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setTitle("Play Again");
        a.getDialogPane().getButtonTypes().addAll(Yes, No);
        a.setHeaderText(s);
        a.showAndWait();
        if(a.getResult() == Yes) {
            return true;
        } else {
            return false;
        }
    }   
}
