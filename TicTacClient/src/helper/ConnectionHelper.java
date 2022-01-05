/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.SceneNavigationController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Player;

/**
 *
 * @author Eslam Esmael
 */
public class ConnectionHelper {

    public static Socket socket = null;
    private static ObjectOutputStream objOutputStream;
    private static ObjectInputStream objInputStream;
    private static final String MY_IP = "10.178.241.76";
    private static SceneNavigationController controller=new SceneNavigationController();
    
    public static String returnRequestPlayerName()
    {
        Player player;
    if(isConnected()){
       try {
          player=(Player) objInputStream.readObject();
          if(player!=null)
          return player.getUsername();
    } catch (IOException ex) {
        Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        return null;
    }
    public static void replayForRequest(boolean replay)
    {
    
    }

    public static void connectToServer()throws IOException {
        if (socket == null) {
            
                socket = new Socket(MY_IP, 3333);
                objOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objInputStream = new ObjectInputStream(socket.getInputStream());
            
        }

    }

    public static void disconnectFromServer() {
        closeStreams();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static boolean isConnected() {
        return socket.isConnected();
    }

    public static ObjectInputStream getObjectInputStream() {
        return objInputStream;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objOutputStream;
    }

    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Test Connection");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void closeStreams(){
        try {
            objOutputStream.close();
            objInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void handleTheOtherPlayerDisconnation(Stage stage){
        showErrorDialog("your conpatitor not found \nyou won");
        try {
           
            controller.switchToOnlineMainScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void logOut(Stage stage){
        ButtonType Yes = new ButtonType("yes");
        ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setContentText("Are you sure you want to log out?");
        a.getDialogPane().getButtonTypes().addAll(Yes, No);
        a.setHeaderText("LOGOUT");
        a.showAndWait();
        if(stage==null)
        System.out.println("stage null");
        if(a.getResult() == Yes) {
          //  disconnectFromServer();
            try {
                controller.switchToMainScene(stage);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public static void handleServerDisconnation(Stage stage){
        disconnectFromServer();
        showErrorDialog("Server not found");
        try {
            controller.switchToMainScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
