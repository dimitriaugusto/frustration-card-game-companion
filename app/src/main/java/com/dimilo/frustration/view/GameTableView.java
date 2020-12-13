package com.dimilo.frustration.view;

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

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.*;
import static com.dimilo.frustration.utils.StringUtils.posIntToString;

public class GameTableView {

    private static final int COLUMN_WIDTH = 180;
    private static final int MAX_TEXT_LENGTH = 7;

    private static final int SMALL_COLUMN_WIDTH = 80;

    private static final int TEXT_SIZE = 16;

    private final Context mContext;
    private final ConstraintLayout mGameTableLayout;
    private final ConstraintLayout mTotalsLayout;
    private final ConstraintLayout mNextGameLayout;

    private final HashMap<String, Integer> mPlayers = new HashMap<>();
    private final List<List<TextView>> mGameTable = new ArrayList<>();
    private final List<TextView> mTotals = new ArrayList<>();
    private final List<TextView> mNextGames = new ArrayList<>();

    public GameTableView(Activity activity) {
        mContext = activity.getApplicationContext();

        mGameTableLayout = activity.findViewById(R.id.game_table_layout);
        mTotalsLayout = activity.findViewById(R.id.totals_layout);
        mNextGameLayout = activity.findViewById(R.id.next_game_layout);
    }

    public void put(PlayerRound play) {
        validate(play);

        int column = getPlayerColumn(play.getPlayer());

        TextView cell = getCell(play.getPlayer(), column, play.getRound());
        cell.setText(play.getFormattedPoints());
    }


    public void put(PlayerTotal total) {
        validate(total);

        int column = getPlayerColumn(total.getPlayer());

        TextView totalCell = getFooterCell(mTotalsLayout, mTotals, column);
        totalCell.setText(posIntToString(total.getTotalPoints()));

        TextView nextGameCell = getFooterCell(mNextGameLayout, mNextGames, column);
        nextGameCell.setText(buildNextGameText(total.getCurrentHand()));
    }

    private void validate(PlayerRound play) {
        validatePlayer(play);
        validateRound(play.getRound());
    }

    private void validate(PlayerTotal total) {
        validatePlayer(total);
        validateNextGame(total.getCurrentHand());
    }

    private void validatePlayer(PlayerRound play) {
        validatePlayerName(play.getPlayer());
    }

    private void validateRound(int round) {
        if (round <= 0)
            throwParamException("Round number should be positive integer: " + round);
    }

    private void validatePlayer(PlayerTotal total) {
        validatePlayerName(total.getPlayer());
        validatePlayerIsPlaying(total.getPlayer());
    }

    private void validatePlayerName(String player) {
        if (player == null || player.length() > MAX_TEXT_LENGTH)
            throwParamException("Player name is null or exceeds max length: " + player.length());
    }

    private void validatePlayerIsPlaying(String player) {
        if (mPlayers.get(player) == null)
            throwParamException("Player is unknown. Cannot put total: " + player);
    }

    private void validateNextGame(String[] nextGame) {
        Arrays.stream(nextGame).forEach((hand) -> {
            if (hand.length() > MAX_TEXT_LENGTH)
                throwParamException("Too long hand text: " + hand);
        });
    }

    private void throwParamException(String message) {
        throw new InvalidParameterException(message);
    }

    private String buildNextGameText(String[] nextHands) {
        StringBuilder nextGameText = new StringBuilder();
        for (String hand : nextHands)
            nextGameText.append('\n').append(hand);
        nextGameText.deleteCharAt(0);
        return nextGameText.toString();
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
                        createHeaderFooterTextView(mGameTableLayout, column, player) :
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

    private TextView getFooterCell(ConstraintLayout layout, List<TextView> list, int column) {
        return list.size() > column ? list.get(column) : createFooterCell(layout, list, column);
    }

    private TextView createFooterCell(ConstraintLayout layout, List<TextView> list, int column) {
        TextView playerTotalCell = column == 0 ?
                createRoundNumberTextView(layout, 0) :
                createHeaderFooterTextView(layout, column, "");
        list.add(column, constrainFooterCell(layout, list, playerTotalCell, column));
        return playerTotalCell;
    }

    private TextView constrainFooterCell(ConstraintLayout layout, List<TextView> list,
                                         TextView current, int column) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        if (column > 0) {
            TextView left = getFooterCell(layout, list, column - 1);
            constraintSet.connect(current.getId(), START, left.getId(), END, 5);
        }
        constraintSet.applyTo(layout);
        return current;
    }

    private TextView createHeaderFooterTextView(ConstraintLayout layout, int column, String text) {
        TextView header = column == 0 ?
                createRoundNumberTextView(layout, 0) :
                createBoldTextView(layout);
        header.setText(text);
        return header;
    }

    private TextView createRoundNumberTextView(ConstraintLayout layout, int round) {
        TextView roundNumber = createBoldTextView(layout);
        roundNumber.setWidth(SMALL_COLUMN_WIDTH);
        roundNumber.setText(round == 0 ? "" : posIntToString(round));
        return roundNumber;
    }

    private TextView createBoldTextView(ConstraintLayout layout) {
        TextView bold = createRegularTextView(layout);
        bold.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        return bold;
    }

    private TextView createRegularTextView(ConstraintLayout layout) {
        TextView regular = new TextView(mContext);
        regular.setId(View.generateViewId());
        regular.setTextSize(TEXT_SIZE);
        regular.setWidth(COLUMN_WIDTH);
        regular.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        regular.setTypeface(Typeface.MONOSPACE);
        layout.addView(regular);
        return regular;
    }

}
