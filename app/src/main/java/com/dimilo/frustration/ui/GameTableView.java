package com.dimilo.frustration.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.*;
import static java.text.MessageFormat.format;

public class GameTableView {

    private final Context mContext;
    private final ConstraintLayout mLayout;

    private final HashMap<String, Integer> players = new HashMap<>();
    private final List<List<TextView>> rounds = new ArrayList<>();
    private final List<Barrier> barriers = new ArrayList<>();

    public GameTableView(ConstraintLayout layout) {
        mLayout = layout;
        mContext = mLayout.getContext();
    }

    public void put(String player, int round, int points) {
        int column = getPlayerColumn(player);
        TextView cell = getCell(player, column, round);
        cell.setText(format("{0}{1}",
                padWithSpaces("" + points, 6),
                points < 50 ? "*" : " "));
    }

    private int getPlayerColumn(String player) {
        players.putIfAbsent(player, players.size());
        return players.get(player);
    }

    private TextView getCell(String player, int column, int round) {
        if (rounds.size() <= column)
            rounds.add(column, new ArrayList<>());

        return rounds.get(column).size() > round ?
                rounds.get(column).get(round) :
                createCell(player, column, round);
    }

    private TextView createCell(String player, int column, int round) {
        TextView cell = new TextView(mContext);
        cell.setId(View.generateViewId());
        cell.setTypeface(Typeface.MONOSPACE);
        cell.setTextSize(16);
        if (round == 0)  {
            cell.setText(padWithSpaces(player, 7));
            cell.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        }
        mLayout.addView(cell);

        rounds.get(column).add(round,
                constrainCell(cell, player, column, round));

        return cell;
    }


    private TextView constrainCell(TextView cell, String player, int column, int round) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mLayout);

        if (round > 0) {
            TextView top = getCell(player, column, round - 1);
            constraintSet.connect(cell.getId(), TOP, top.getId(), BOTTOM, 0);
        }
        if (column > 0) {
            TextView left = getCell(player, column - 1, round);
            constraintSet.connect(cell.getId(), START, left.getId(), END, 5);
        }

        constraintSet.applyTo(mLayout);

        return cell;
    }

    private String padWithSpaces(String input, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(" ");
        return sb.substring(input.length()) + input;
    }

}
