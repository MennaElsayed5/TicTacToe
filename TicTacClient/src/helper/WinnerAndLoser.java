package helper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mina
 */
public class WinnerAndLoser extends Thread {

    public static boolean won = true;
    Stage stage;
    ImageView iv = new ImageView();
    VBox box;

    public WinnerAndLoser(boolean w) {
        won = w;
        stage = new Stage();
        box = new VBox();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            Platform.runLater(() -> {
                stage.close();
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(WinnerAndLoser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void display() {
        start();
        Image i;
        if (won) {
            i = new Image(getClass().getResourceAsStream("/assets/winner.gif"));
        } else {
            i = new Image(getClass().getResourceAsStream("/assets/loser.gif"));
        }

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        iv.setFitWidth(500);
        iv.setFitHeight(500);
        iv.setImage(i);

        box.getChildren().add(iv);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.showAndWait();

    }

}
