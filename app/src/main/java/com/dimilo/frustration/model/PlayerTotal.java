package com.dimilo.frustration.model;

public class PlayerTotal {

    private String player;
    private int totalPoints;
    private String[] nextGame;

    public PlayerTotal(String player, int totalPoints, String[] nextGame) {
        this.player = player;
        this.totalPoints = totalPoints;
        this.nextGame = nextGame;
    }

    public String getPlayer() {
        return player;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String[] getNextGame() {
        return nextGame;
    }

}
