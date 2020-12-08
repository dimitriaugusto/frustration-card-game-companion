package com.dimilo.frustration.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.PlayerRound;
import com.dimilo.frustration.model.PlayerTotal;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.*;

public class GameTableView {

    private static final int COLUMN_WIDTH = 7;

    private final Context mContext;
    private final ConstraintLayout mGameTableLayout;
    private final ConstraintLayout mTotalsLayout;
//    private final ConstraintLayout mNextGameLayout;

    private final HashMap<String, Integer> mPlayers = new HashMap<>();
    private final List<List<TextView>> mGameTable = new ArrayList<>();
    private final List<TextView> mTotals = new ArrayList<>();
    private final List<TextView> mNextGames = new ArrayList<>();

    public GameTableView(Activity activity) {
        mContext = activity.getApplicationContext();

        mGameTableLayout = activity.findViewById(R.id.gameTableLayout);
        mTotalsLayout = activity.findViewById(R.id.playerTotalsLayout);
//        mNextGameLayout = activity.findViewById(R.id.nextGamesLayout);
    }

    public void put(PlayerRound play) {
        int column = getPlayerColumn(play.getPlayer());

        TextView cell = getCell(play.getPlayer(), column, play.getRound());
        cell.setText(padWithSpaces(play.getFormattedPoints(), COLUMN_WIDTH));
    }


    public void put(PlayerTotal total) {
        int column = getPlayerColumn(total.getPlayer());

        TextView totalCell = getTotalCell(column);
        totalCell.setText(MessageFormat.format("{0}\n{1}", padWithSpaces(total.getTotalPoints(), COLUMN_WIDTH), total.getNextPlayGoal()));
    }


    private int getPlayerColumn(String player) {
        mPlayers.putIfAbsent(player, mPlayers.size() + 1);
        return mPlayers.get(player);
    }

    private TextView getCell(String player, int column, int round) {
        addColumnIfAbsent(column);
        return mGameTable.get(column).size() > round ?
                mGameTable.get(column).get(round) :
                createCell(player, column, round);
    }

    private void addColumnIfAbsent(int column) {
        if (mGameTable.size() <= column) {
            addColumnIfAbsent(column - 1);
            mGameTable.add(column, new ArrayList<>());
        }
    }

    private TextView createCell(String player, int column, int round) {
        TextView cell = column == 0 ?
                createRoundNumberTextView(mGameTableLayout, round) :
                round == 0 ?
                        createHeaderTextView(mGameTableLayout, player) :
                        createRegularTextView(mGameTableLayout);

        mGameTable.get(column).add(round, constrainCell(cell, player, column, round));

        return cell;
    }


    private TextView constrainCell(TextView current, String player, int column, int round) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mGameTableLayout);

        if (round > 0) {
            TextView top = getCell(player, column, round - 1);
            constraintSet.connect(current.getId(), TOP, top.getId(), BOTTOM);
        }

        if (column > 0) {
            TextView left = getCell(player, column - 1, round);
            constraintSet.connect(current.getId(), START, left.getId(), END, 5);
        }

        constraintSet.applyTo(mGameTableLayout);

        return current;
    }

    private TextView getTotalCell(int column) {
        return mTotals.size() > column ? mTotals.get(column) : createTotalCell(column);
    }

    private TextView createTotalCell(int column) {
        TextView playerTotalCell = createHeaderTextView(mTotalsLayout, "SUM");
        mTotals.add(column, constrainTotalCell(playerTotalCell, column));
        return playerTotalCell;
    }

    private TextView constrainTotalCell(TextView current, int column) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mTotalsLayout);

        if (column > 0) {
            TextView left = getTotalCell(column - 1);
            constraintSet.connect(current.getId(), START, left.getId(), END, 5);
        }

        constraintSet.applyTo(mTotalsLayout);

        return current;
    }

    private TextView createHeaderTextView(ConstraintLayout layout, String text) {
        TextView header = createRegularTextView(layout);
        header.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        header.setText(padWithSpaces(text, COLUMN_WIDTH));
        return header;
    }

    private TextView createRoundNumberTextView(ConstraintLayout layout, int round) {
        TextView roundNumber = createRegularTextView(layout);
        roundNumber.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        roundNumber.setText(round == 0 ?
                padWithSpaces("", COLUMN_WIDTH) :
                padWithSpaces(round, COLUMN_WIDTH));
        return roundNumber;
    }

    private TextView createRegularTextView(ConstraintLayout layout) {
        TextView regular = new TextView(mContext);
        regular.setId(View.generateViewId());
        regular.setTextSize(16);
        regular.setTypeface(Typeface.MONOSPACE);
        layout.addView(regular);
        return regular;
    }

    private String padWithSpaces(int input, int length) {
        return padWithSpaces(Integer.toString(input), length);
    }

    private String padWithSpaces(String input, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(" ");
        return sb.substring(input.length()) + input;
    }

}
