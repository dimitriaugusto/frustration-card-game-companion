package com.dimilo.frustration.model;

public class PlayerTotal {

    private String player;
    private int totalPoints;
    private String[] currentHand;

    public PlayerTotal(String player, int totalPoints, String[] currentHand) {
        this.player = player;
        this.totalPoints = totalPoints;
        this.currentHand = currentHand;
    }

    public String getPlayer() {
        return player;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String[] getCurrentHand() {
        return currentHand;
    }

}
