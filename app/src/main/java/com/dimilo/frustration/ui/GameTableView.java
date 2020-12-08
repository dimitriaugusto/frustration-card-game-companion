package com.dimilo.frustration.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.dimilo.frustration.model.PlayerRound;
import com.dimilo.frustration.model.PlayerTotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.*;
import static java.text.MessageFormat.format;

public class GameTableView {

    private final Context mContext;
    private final ConstraintLayout mGameTableLayout;
    private final ConstraintLayout mTotalsLayout;

    private final HashMap<String, Integer> mPlayers = new HashMap<>();
    private final List<List<TextView>> mGameTable = new ArrayList<>();
    private final List<TextView> mTotals = new ArrayList<>();

    public GameTableView(ConstraintLayout gameTableLayout, ConstraintLayout totalsLayout) {
        mGameTableLayout = gameTableLayout;
        mTotalsLayout = totalsLayout;
        mContext = mGameTableLayout.getContext();
    }

    public void put(PlayerRound play) {
        int column = getPlayerColumn(play.getPlayer());

        TextView cell = getCell(play.getPlayer(), column, play.getRound());
        cell.setText(padWithSpaces(play.getFormattedPoints(), 7));
    }


    public void put(PlayerTotal total) {
        int column = getPlayerColumn(total.getPlayer());

        TextView totalCell = getTotalCell(column);
        totalCell.setText(padWithSpaces(total.getTotalPoints(), 7) + "\n" + total.getNextPlayGoal());
    }


    private int getPlayerColumn(String player) {
        mPlayers.putIfAbsent(player, mPlayers.size());
        return mPlayers.get(player);
    }

    private TextView getCell(String player, int column, int round) {
        if (mGameTable.size() <= column) mGameTable.add(column, new ArrayList<>());
        return mGameTable.get(column).size() > round ?
                mGameTable.get(column).get(round) :
                createCell(player, column, round);
    }

    private TextView createCell(String player, int column, int round) {
        TextView cell = round == 0 ?
                createHeaderTextView(mGameTableLayout, player) : createRegularTextView(mGameTableLayout);
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
        TextView headerTextView = createRegularTextView(layout);
        headerTextView.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        headerTextView.setText(padWithSpaces(text, 7));
        return headerTextView;
    }

    private TextView createRegularTextView(ConstraintLayout layout) {
        TextView textView = new TextView(mContext);
        textView.setId(View.generateViewId());
        textView.setTextSize(16);
        textView.setTypeface(Typeface.MONOSPACE);
        layout.addView(textView);
        return textView;
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
