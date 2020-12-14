package com.dimilo.frustration.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.GameTableModel;
import com.dimilo.frustration.model.Play;
import com.dimilo.frustration.model.Summary;
import com.dimilo.frustration.view.dialog.EditPlayDialog;
import com.dimilo.frustration.view.dialog.FirstPlayDialog;
import com.dimilo.frustration.view.dialog.NextPlayDialog;
import com.dimilo.frustration.view.GameTableView;

import static com.dimilo.frustration.utils.StringUtils.isEmpty;

public class GameTableFragment extends Fragment {

    private GameTableView mGameTableView;
    private FirstPlayDialog mFirstPlayDialog;
    private NextPlayDialog mNextPlayDialog;
    private EditPlayDialog mEditPlayDialog;

    private GameTableModel mGameTable;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGameTableView = new GameTableView(getActivity());

        mFirstPlayDialog = new FirstPlayDialog(getContext());
        mNextPlayDialog = new NextPlayDialog(getContext());
        mEditPlayDialog = new EditPlayDialog(getContext());

        mGameTable = new GameTableModel(getContext());

        setupClickListeners();
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::addPlay);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::editPlay);
    }

    private void addPlay(View view) {
        Play play = mGameTable.getNextPlay();
        if (isEmpty(play.getPlayer())) addPlayerFirstPlay();
        else addPlayerNextPlay(play.getRound());
    }

    private void addPlayerFirstPlay() {
        mFirstPlayDialog.show(new Play(1), this::onPlayerFirstPlay);
    }

    private void onPlayerFirstPlay(Play play) {
        if (!isEmpty(play.getPlayer())) {
            updateGameTableView(play, putPlayToGameTable(play));
            addPlayerFirstPlay(); // pop for next new player
        }
    }

    private void addPlayerNextPlay(int round) {
        Play play = mGameTable.getNextPlay();
        if (play.getRound() == round)
            mNextPlayDialog.show(play, this::onPlayerNextPlay);
    }

    private void onPlayerNextPlay(Play play) {
        if (!isEmpty(play.getPlayer())) {
            updateGameTableView(play, putPlayToGameTable(play));
            addPlayerNextPlay(play.getRound());
        }
    }


    private void editPlay(View view) {
//        mNextPlayDialog.show(new Play(), this::onPlayEdited);
    }

//    private void onPlayEdited(Play play) {
//        if (!isEmpty(play.getPlayer()))
//            updateGameTableView(play, putPlayToGameTable());
//    }

    private Summary putPlayToGameTable(Play play) {
        Summary total = mGameTable.put(play);
        return total;
    }

    private Summary putPlayToGameTableIfPresent(Play play) {
        //Summary total = mGameTable.putIfPresent(play);
        return null;
    }

    private void updateGameTableView(Play play, Summary total) {
        mGameTableView.put(play);
        mGameTableView.put(total);
    }

}