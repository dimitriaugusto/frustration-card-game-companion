package com.dimiaug.frustration.features.play.gateways.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "play", primaryKeys = {"player", "round"})
public class PlayEntity {

    @NonNull
    public String player;

    public int round;

    public int points;

    public PlayEntity(@NonNull String player, int round, int points) {
        this.player = player;
        this.round = round;
        this.points = points;
    }
}
