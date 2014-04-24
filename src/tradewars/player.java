/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tradewars;

/**
 *
 * @author bailey
 */
public class player {

    private String playerName;
    private int playerCash;
    private int playerLife;
    private int bagSize;
    private int bagSpaceUsed;

    public int getBagSpaceUsed() {
        return bagSpaceUsed;
    }

    public void setBagSpaceUsed(int bagSpaceUsed) {
        this.bagSpaceUsed = bagSpaceUsed;
    }
    
    private tradewars.commodities[] stash;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerCash() {
        return playerCash;
    }

    public void setPlayerCash(int playerCash) {
        this.playerCash = playerCash;
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }

    public int getBagSize() {
        return bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }

    public commodities[] getStash() {
        return stash;
    }

    public void setStash(commodities[] stash) {
        this.stash = stash;
    }

    public player(String playerName, int playerCash, int playerLife, int bagSize) {
        this.playerName = playerName;
        this.playerCash = playerCash;
        this.playerLife = playerLife;
        this.bagSize = bagSize;
    }

    public player(String playerName) {
        this.playerName = playerName;
    }
    
    
    
     
}
