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
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.OnlineAndCurrentlyPlayingPlayersModel;
import model.Player;

/**
 *
 * @author mina
 */
public class OnlineMainSceneController implements Initializable {
    
    Thread listener;
    
    @FXML
    TextField playerNameTextField;
    
    @FXML
    TextField playerScoreTextField;
    
    @FXML
    ListView availablePlayersList;
    
    @FXML
    ListView playersInGameList;
    
    @FXML
    ListView recordedGamesListView;
    
    String requestPlayerName;
    
    SceneNavigationController controller;
    FXMLLoader fxmlLoader;
    ObservableList observableAvailableList, observablePlayerInGameList, observableRecordeedList;
    private final String DIRNAME = "RecordedGames";
    
    public void showRecordedList() {
        String[] arr = getFiles();
        System.out.println(observableRecordeedList.size() + "observable size" + arr.length);
        for (int j = 0; j < arr.length && observableRecordeedList.size() < arr.length; j++) {
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
    
    public void clearObservables() {
        observableRecordeedList.clear();
    }
    
    public String[] getFiles() {
        File recordedGames = new File(DIRNAME);
        String[] gameFiles = recordedGames.list();
        return gameFiles;
    }
    
    public void showAvailablePlayer() {
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
        Stage s = (Stage) playersInGameList.getScene().getWindow();
        ConnectionHelper.logOut(s);
    }
    
    @FXML
    public void handelLogoutButton(ActionEvent event) {
        controller = new SceneNavigationController();
        try {
            ConnectionHelper.disconnectFromServer();
            controller.switchToLoginScene(event);
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void switchToOnlineGame() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
            Stage stag = (Stage) playersInGameList.getScene().getWindow();
            Scene scene = new Scene(root);
            stag.setScene(scene);
            stag.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateUI(Player player) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ConnectionHelper.player = player;
                playerNameTextField.setText(player.getUsername());
                player.setScore(player.getWins() * 3);
                playerScoreTextField.setText(String.valueOf(player.getScore()));
            }
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
        observableAvailableList = FXCollections.observableArrayList();
        availablePlayersList.setItems(observableAvailableList);
        observablePlayerInGameList = FXCollections.observableArrayList();
        playersInGameList.setItems(observablePlayerInGameList);
        observableRecordeedList = FXCollections.observableArrayList();
        recordedGamesListView.setItems(observableRecordeedList);
        
        try {
            ConnectionHelper.getObjectOutputStream().writeObject(ConnectionHelper.userId);
            ConnectionHelper.getObjectOutputStream().writeObject("GetPlayers");
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listener = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ConnectionHelper.getInstanceOf(ConnectionHelper.SERVER_IP);
                        Object object = ConnectionHelper.getObjectInputStream().readObject();
                        if (object instanceof OnlineAndCurrentlyPlayingPlayersModel) {
                            OnlineAndCurrentlyPlayingPlayersModel players = (OnlineAndCurrentlyPlayingPlayersModel) object;
                            observableAvailableList = FXCollections.observableList(players.getAvailablePlayers());
                            observablePlayerInGameList = FXCollections.observableList(players.getPlayingPlayers());
                            
                            availablePlayersList.setItems(observableAvailableList);
                            playersInGameList.setItems(observablePlayerInGameList);
                            
                            if (!listener.isAlive()) {
                                listener.start();
                            }
                        } else if (object instanceof Player) {
                            Player player = (Player) object;
                            updateUI(player);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
                        listener.stop();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
                        listener.stop();
                    }
                }
                
            }
        });
        listener.start();
    }
    
}
