package model;

import helper.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eslam Esmael
 */
public class TicTacClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        new SplashScreen().display();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
