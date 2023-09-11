package com.dimiaug.frustration.features.play.domain.logic;

import android.content.Context;
import android.content.res.TypedArray;

import com.dimiaug.frustration.R;
import com.dimiaug.frustration.features.play.domain.utils.ArrayUtils;
import com.dimiaug.frustration.features.play.domain.model.Play;
import com.dimiaug.frustration.features.play.domain.model.Summary;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameTable {

    private static final int MINIMUM_POINTS_FOR_UNFINISHED_HAND = 50;

    private final Context mContext;

    private final HashMap<String, HashMap<Integer, Integer>> mPlaysTable = new HashMap<>();

    public GameTable(Context context) {
        mContext = context;
    }

    public Summary put(Play play) {
        mPlaysTable.putIfAbsent(play.getPlayer(), new HashMap<>());
        mPlaysTable.get(play.getPlayer()).put(play.getRound(), play.getPoints());
        return getPlayerSummary(play.getPlayer());
    }

    public Summary edit(Play play) {
        return mPlaysTable.containsKey(play.getPlayer()) &&
                mPlaysTable.get(play.getPlayer()).containsKey(play.getRound()) ?
                put(play) :
                new Summary();
    }

    private Summary getPlayerSummary(String player) {
        int sum = getPlayerTotalPoints(player);
        int currentHandIndex = getPlayerCurrentHandIndex(player);
        String[] textForCurrentHand = getTextForCurrent(currentHandIndex);
        return new Summary(player, sum, currentHandIndex, textForCurrentHand);
    }

    private int getPlayerTotalPoints(String player) {
        return mPlaysTable.get(player).values().stream().reduce(0, Integer::sum);
    }

    private int getPlayerCurrentHandIndex(String player) {
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

    public List<Summary> getPlayersSummaries() {
        return mPlaysTable.keySet().stream().map((a) -> getPlayerSummary(a)).collect(Collectors.toList());
    }

    public boolean isGameFinished() {
        return getNextPlay().getRound() == Integer.MAX_VALUE;
    }

    public Play getNextPlay() {
        final boolean hasPlayers = !mPlaysTable.isEmpty();
        boolean playerFinished = false;
        for (int round = 1; hasPlayers && !playerFinished; round++) {
            for (String player : mPlaysTable.keySet()) {
                playerFinished |= getPlayerCurrentHandIndex(player) >= getHandsSequenceLength();
                if (!mPlaysTable.get(player).containsKey(round))
                    return new Play(player, round);
            }
        }
        return playerFinished ? new Play(Integer.MAX_VALUE) : new Play();
    }

}
