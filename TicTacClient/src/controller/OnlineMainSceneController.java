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
import javafx.scene.control.ListView;


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
    private void buttonBackPressed(ActionEvent event) {
        
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
