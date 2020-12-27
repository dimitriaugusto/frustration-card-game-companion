package com.dimiaug.frustration.model;

import android.os.Bundle;

import com.dimiaug.frustration.data.PlayEntity;

public class Play {

    private final String player;
    private final int round;
    private final int points;

    public Play(String player, int round, int points) {
        this.player = player;
        this.round = round;
        this.points = points;
    }

    public Play(PlayEntity playEntity) {
        this.player = playEntity.player;
        this.round = playEntity.round;
        this.points = playEntity.points;
    }

    public Play(Bundle state) {
        this(state.getString("player", ""),
                state.getInt("round", -1),
                state.getInt("points", -1));
    }

    public Bundle getState() {
        Bundle state = new Bundle();
        state.putString("player", this.player);
        state.putInt("round", this.round);
        state.putInt("points", this.points);
        return state;
    }

    public Play(String player, int round) {
        this(player, round, -1);
    }

    public Play(int round) {
        this("", round, -1);
    }

    public Play() {
        this("", -1, -1);
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
