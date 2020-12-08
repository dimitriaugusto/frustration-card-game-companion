package com.dimilo.frustration.model;

public class PlayerTotal {

    private String player;
    private int totalPoints;
    private String nextPlayGoal;

    public PlayerTotal(String player, int totalPoints, String nextPlayGoal) {
        this.player = player;
        this.totalPoints = totalPoints;
        this.nextPlayGoal = nextPlayGoal;
    }

    public String getPlayer() {
        return player;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getNextPlayGoal() {
        return nextPlayGoal;
    }

}
