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

    public PlayerTotal put(PlayerRound play) {
        mPlaysTable.putIfAbsent(play.getPlayer(), new HashMap<>());
        mPlaysTable.get(play.getPlayer()).put(play.getRound(), play.getPoints());
        return getPlayerTotal(play.getPlayer());
    }

    private PlayerTotal getPlayerTotal(String player) {
        int sum = getPlayerTotalPoints(player);
        String[] textForCurrentHand = getTextForCurrent(getPlayerCurrentHand(player));
        return new PlayerTotal(player, sum, textForCurrentHand);
    }

    private int getPlayerTotalPoints(String player) {
        return mPlaysTable.get(player).values().stream().reduce(0, Integer::sum);
    }

    private int getPlayerCurrentHand(String player) {
        return mPlaysTable.get(player).values().stream()
                .mapToInt((a) -> a < MINIMUM_POINTS_FOR_UNFINISHED_HAND ? 0 : 1).sum();
    }

    private String[] getTextForCurrent(int handNumber) {
        TypedArray handsSequence = mContext.getResources().obtainTypedArray(R.array.hands_sequence);
        String[] currentHand = handNumber > handsSequence.length() ?
                mContext.getResources().getStringArray(R.array.no_more_hands) :
                ArrayUtils.toStringArray(handsSequence.getTextArray(handNumber));
        handsSequence.recycle();
        return currentHand;
    }

    private int getHandsSequenceLength() {
        TypedArray handsSequence = mContext.getResources().obtainTypedArray(R.array.hands_sequence);
        int length = handsSequence.length();
        handsSequence.recycle();
        return length;
    }


    public PlayerRound getNextPlay() {
        boolean playerFinished = false;
        for (int round = 1; !playerFinished; round++) {
            for (String player : mPlaysTable.keySet()) {
                playerFinished |= getPlayerCurrentHand(player) > getHandsSequenceLength();
                if (!mPlaysTable.get(player).containsKey(round))
                    return new PlayerRound(player, round);
            }
        }
        return new PlayerRound();
    }

}
