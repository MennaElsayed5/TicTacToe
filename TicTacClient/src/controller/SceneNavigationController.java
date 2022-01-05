package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eslam Esmael
 */
public class SceneNavigationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

   
     public void switchToPlayerVsPlayerScene(Stage stage) throws IOException {
         Parent r=FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
        switchToScene(stage,r);
    }

     public void switchToPlayerVsAIScene(Stage stage) throws IOException {
         Parent r=FXMLLoader.load(getClass().getResource("/view/PlayerVsAIView.fxml"));
        switchToScene(stage,r);
    }
    

    public void switchToMainScene(Stage stage) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource( "/view/MainScene.fxml"));
        switchToScene(stage,parent);
    }
    
    public void switchToOnlineMainScene(Stage stage) throws IOException {
        Parent r=FXMLLoader.load(getClass().getResource("/view/OnlineMainView.fxml"));
        switchToScene(stage,r);
    }

    public void switchToOnlineScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/LoginRegisterView.fxml");
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        //  switchToScene(event,"/view/FirstScreenAfterEnteringIP.fxml");
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/RegisterScreenView.fxml");
    }
    
    public void switchToRecordedGame(ActionEvent event) throws IOException {
        switchToScene(event, "/view/RecordedGameView.fxml");
    }

    private void switchToScene(ActionEvent event, String path) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void switchToScene(Stage stage,Parent fXMLLoader)
    {
        scene = new Scene(fXMLLoader);
        stage.setScene(scene);
        stage.show();
    }
}
