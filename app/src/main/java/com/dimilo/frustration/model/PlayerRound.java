package com.dimilo.frustration.model;

public class PlayerRound {

    private String player;
    private int round;
    private int points;

    public PlayerRound(String player, int round, int points) {
        this.player = player;
        this.round = round;
        this.points = points;
    }

    public PlayerRound(int round) {
        this("", round, 0);
    }

    public String getPlayer() {
        return player;
    }

    public int getRound() {
        return round;
    }

    public int getPoints() {
        return points;
    }

    public String getFormattedPoints() {
        return (points < 50 ? "*" : " ") + points;
    }

}
