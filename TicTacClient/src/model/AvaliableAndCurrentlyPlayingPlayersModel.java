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
public class AvaliableAndCurrentlyPlayingPlayersModel implements Serializable {

    private static final long serialVersionUID = 6529685098667757700L;

    private ArrayList<Player> availablePlayers;
    private ArrayList<Player> playingPlayers;

    public ArrayList<Player> getAvailablePlayers() {
        return availablePlayers;
    }

    public void setAvailablePlayers(ArrayList<Player> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    public ArrayList<Player> getPlayingPlayers() {
        return playingPlayers;
    }

    public void setPlayingPlayers(ArrayList<Player> playingPlayers) {
        this.playingPlayers = playingPlayers;
    }

}
