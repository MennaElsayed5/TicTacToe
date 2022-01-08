/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eslam Esmael
 */
public class OnlineAndCurrentlyPlayingPlayersModel implements Serializable {

    private static final long serialVersionUID = 6529685098667757700L;

    private ArrayList<Player> onlinePlayers;
    private ArrayList<Player> playingPlayers;

    public ArrayList<Player> getAvailablePlayers() {
        return onlinePlayers;
    }

    public void setAvailablePlayers(ArrayList<Player> availablePlayers) {
        this.onlinePlayers = availablePlayers;
    }

    public ArrayList<Player> getPlayingPlayers() {
        return playingPlayers;
    }

    public void setPlayingPlayers(ArrayList<Player> playingPlayers) {
        this.playingPlayers = playingPlayers;
    }

}
