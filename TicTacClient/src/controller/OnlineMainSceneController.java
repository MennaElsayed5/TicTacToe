/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import controller.CustomItems.CustomItemRecordedGameController;
import helper.ConnectionHelper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


/**
 *
 * @author mina
 */
public class OnlineMainSceneController implements Initializable {

    @FXML
    ListView availablePlayersList;

    @FXML
    ListView playersInGameList;
    
    @FXML
    ListView recordedGamesListView;
    
    String requestPlayerName;

    SceneNavigationController controller;
    FXMLLoader fxmlLoader;
    ObservableList observableAvailableList, observablePlayerInGameList,observableRecordeedList;
    private final String DIRNAME = "RecordedGames";

    public void showRecordedList() {
        String[] arr = getFiles();
        System.out.println(observableRecordeedList.size()+"observable size"+arr.length);
            for(int j=0;j<arr.length&&observableRecordeedList.size()<arr.length;j++){
                    try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemRecordedListView.fxml"));
            observableRecordeedList.add(fxmlLoader.load());
            ((CustomItemRecordedGameController) fxmlLoader.getController()).setInfo(arr[j]);
            
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            recordedGamesListView.refresh();
    }
    public void clearObservables()
    {
    observableRecordeedList.clear();
    }

    public String[] getFiles() {
        File recordedGames = new File(DIRNAME);
        String[] gameFiles = recordedGames.list();
        return gameFiles;
    }
 
    public void showAvailablePlayer()
    { 
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemAvailableListView.fxml"));
            observableAvailableList.add(fxmlLoader.load());
            availablePlayersList.refresh();

        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showPlayersInGame();
    }

    public void showPlayersInGame() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemPlayersInGameListView.fxml"));
            observablePlayerInGameList.add(fxmlLoader.load());
            playersInGameList.refresh();
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleRecordButton(ActionEvent event) {
        controller = new SceneNavigationController();
        try {
            controller.switchToRecordedGame(event);
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @FXML
    private void btnLogOutPressed() {
        Stage s=(Stage)playersInGameList.getScene().getWindow();
        ConnectionHelper.logOut(s);
    }

    
    @FXML
    public void handelLogoutButton(ActionEvent event){
    controller = new SceneNavigationController();
        try {
            ConnectionHelper.disconnectFromServer();
            controller.switchToOnlineScene(event);
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startListen()
    {
    Thread th=new Thread()
    {
    @Override
    public void run(){
        
        while(requestPlayerName==null)
        {
         requestPlayerName=ConnectionHelper.returnRequestPlayerName();
         if(requestPlayerName!=null)
             askPlayWithYou(requestPlayerName);
        }
        
    }
    };
       if(ConnectionHelper.isConnected())
           th.start();
    }
     public void askPlayWithYou(String s) {
         
        ButtonType Yes = new ButtonType("Agree");
        ButtonType No = new ButtonType("DisCoard", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setTitle("Requst Game");
        a.getDialogPane().getButtonTypes().addAll(Yes, No);
        a.setHeaderText(s+" want to play with you");
        a.showAndWait();
        if(a.getResult() == Yes) {
              
              ConnectionHelper.replayForRequest(true);
              switchToOnlineGame();
              //return true;
        } else {
            //return false;
            ConnectionHelper.replayForRequest(false);
        }
    }
     
     public void switchToOnlineGame()
     {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
            Stage stag=(Stage)playersInGameList.getScene().getWindow();
            Scene scene=new Scene(root);
            stag.setScene(scene);
            stag.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
        observableAvailableList = FXCollections.observableArrayList();
        availablePlayersList.setItems(observableAvailableList);
        observablePlayerInGameList = FXCollections.observableArrayList();
        playersInGameList.setItems(observablePlayerInGameList);
        observableRecordeedList=FXCollections.observableArrayList();
        recordedGamesListView.setItems(observableRecordeedList);

    }

}
