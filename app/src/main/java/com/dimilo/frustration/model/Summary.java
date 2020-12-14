package com.dimilo.frustration.model;

import com.dimilo.frustration.utils.ArrayUtils;

public class Summary {

    private final String player;
    private final int totalPoints;
    private final int currentHandIndex;
    private final String[] currentHand;

    public Summary(String player, int totalPoints, int currentHandIndex, String[] currentHand) {
        this.player = player;
        this.totalPoints = totalPoints;
        this.currentHand = currentHand;
        this.currentHandIndex = currentHandIndex;
    }

    public Summary() {
        this("", 0, 0, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public String getPlayer() {
        return player;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getCurrentHandIndex() {
        return currentHandIndex;
    }

    public String[] getCurrentHand() {
        return currentHand;
    }

}
