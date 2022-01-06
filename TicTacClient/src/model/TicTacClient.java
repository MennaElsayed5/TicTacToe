package model;

import controller.SplashScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Eslam Esmael
 */
public class TicTacClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
// Todo uncomment the splash        
 new SplashScreenController().display();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/game.png")));
         stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
