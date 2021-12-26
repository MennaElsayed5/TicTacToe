package controller;

import java.io.IOException;
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
public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToOnlineScene(ActionEvent event) throws IOException{
        switchToScene(event , "/view/OnlineSceneView.fxml");
    }
    
    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException{
        switchToScene(event , "/view/PlayerVsPlayerView.fxml");
    }
    
    public void switchToMainScene(ActionEvent event) throws IOException{
        switchToScene(event , "/view/MainScene.fxml");
    }
    
    private void switchToScene(ActionEvent event , String filePath) throws IOException{
        root = FXMLLoader.load(getClass().getResource(filePath));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
