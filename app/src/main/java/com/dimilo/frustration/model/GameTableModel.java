package com.dimilo.frustration.model;

import android.content.Context;
import android.content.res.TypedArray;

import com.dimilo.frustration.R;
import com.dimilo.frustration.utils.ArrayUtils;

import java.util.HashMap;

public class GameTableModel {

    private static final int MINIMUM_POINTS_FOR_UNFINISHED_HAND = 50;

    private final Context mContext;

    private final HashMap<String, HashMap<Integer, Integer>> mPlaysTable = new HashMap<>();

    public GameTableModel(Context context) {
        mContext = context;
    }

    public Summary put(Play play) {
        mPlaysTable.putIfAbsent(play.getPlayer(), new HashMap<>());
        mPlaysTable.get(play.getPlayer()).put(play.getRound(), play.getPoints());
        return getPlayerSummary(play.getPlayer());
    }

    private Summary getPlayerSummary(String player) {
        int sum = getPlayerTotalPoints(player);
        String[] textForCurrentHand = getTextForCurrent(getPlayerCurrentHand(player));
        return new Summary(player, sum, textForCurrentHand);
    }

    private int getPlayerTotalPoints(String player) {
        return mPlaysTable.get(player).values().stream().reduce(0, Integer::sum);
    }

    private int getPlayerCurrentHand(String player) {
        return mPlaysTable.get(player).values().stream()
                .mapToInt((a) -> a < MINIMUM_POINTS_FOR_UNFINISHED_HAND ? 1 : 0).sum();
    }

    private String[] getTextForCurrent(int handNumber) {
        TypedArray handsSequence = mContext.getResources().obtainTypedArray(R.array.hands_sequence);
        String[] currentHand = handNumber < handsSequence.length() ?
                ArrayUtils.toStringArray(handsSequence.getTextArray(handNumber)) :
                mContext.getResources().getStringArray(R.array.no_more_hands);
        handsSequence.recycle();
        return currentHand;
    }

    private int getHandsSequenceLength() {
        TypedArray handsSequence = mContext.getResources().obtainTypedArray(R.array.hands_sequence);
        int length = handsSequence.length();
        handsSequence.recycle();
        return length;
    }

    public Play getNextPlay() {
        final boolean hasPlayers = !mPlaysTable.isEmpty();
        boolean playerFinished = false;
        for (int round = 1; hasPlayers && !playerFinished; round++) {
            for (String player : mPlaysTable.keySet()) {
                playerFinished |= getPlayerCurrentHand(player) > getHandsSequenceLength();
                if (!mPlaysTable.get(player).containsKey(round))
                    return new Play(player, round);
            }
        }
        return new Play();
    }

}
