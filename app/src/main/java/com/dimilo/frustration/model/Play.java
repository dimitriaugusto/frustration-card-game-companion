package com.dimilo.frustration.model;

public class Play {

    private final String player;
    private final int round;
    private final int points;

    public Play(String player, int round, int points) {
        this.player = player;
        this.round = round;
        this.points = points;
    }

    public Play(String player, int round) {
        this(player, round, 0);
    }

    public Play(int round) {
        this("", round, 0);
    }

    public Play() {
        this("", 0, 0);
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
