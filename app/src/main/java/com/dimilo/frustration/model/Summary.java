package com.dimilo.frustration.model;

public class Summary {

    private String player;
    private int totalPoints;
    private String[] currentHand;

    public Summary(String player, int totalPoints, String[] currentHand) {
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
