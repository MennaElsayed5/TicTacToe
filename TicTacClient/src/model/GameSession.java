package model;

/**
 *
 * @author Eslam Esmael
 */
public class GameSession {

    private int counter;

    Player playerOne;
    Player playerTwo;
    PlayerMove[] playersMoves;
    boolean isPlayerOneTurn;

    public GameSession() {
        playersMoves = new PlayerMove[9];
        counter = 0;
    }

    public void addMove(PlayerMove move) {
        playersMoves[counter] = move;
        counter++;
    }

    public PlayerMove[] getPlayersMoves() {
        return playersMoves;
    }

    public void setPlayersMoves(PlayerMove[] playersMoves) {
        this.playersMoves = playersMoves;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
