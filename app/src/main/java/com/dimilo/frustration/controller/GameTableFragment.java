package com.dimilo.frustration.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dimilo.frustration.R;

import java.util.function.ToDoubleBiFunction;

public class GameTableFragment extends Fragment {

    private static final int BACK_TO_WELCOME_ITEM_ID = 10;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.add(Menu.NONE, BACK_TO_WELCOME_ITEM_ID, 0, R.string.action_welcome);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case BACK_TO_WELCOME_ITEM_ID:
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupClickListeners();

//        GameTableView gameTableView = new GameTableView(getActivity());
//
//        String[] hands = {"Set 4", "Run 5"};
//        String[] hands2 = {"Set 3", "Run 3"};
//
////        GameTableModel gameTable = new GameTableModel();
//        PlayerRound play = new PlayerRound("dimitri", 1, 30);
//        gameTableView.put(play);
//        play = new PlayerRound("dimitri", 2, 50);
//        gameTableView.put(play);
//
//        play = new PlayerRound("monica", 1, 60);
//        gameTableView.put(play);
//        play = new PlayerRound("monica", 2, 30);
//        gameTableView.put(play);
//
//        PlayerTotal total = new PlayerTotal("dimitri", 60, hands);
//        gameTableView.put(total);
//        total = new PlayerTotal("monica", 120, hands2);
//        gameTableView.put(total);
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::addPlay);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::editPlay);
    }

    private void addPlay(View view) {
        AlertDialog.Builder addDialog = new AlertDialog.Builder(getContext());
        addDialog.setTitle("Frustration")
                .setMessage("Inform player name")
                .setView(R.layout.dialog_fields)
                .setCancelable(false)
                .setPositiveButton("Inform points", this::informPoints)
                .setNegativeButton("Cancel player", (dialog, which) -> dialog.dismiss())
                .show();
    }


    private void informPoints(DialogInterface dialogInterface, int i) {
        new AlertDialog.Builder(getContext())
                .setTitle("Frustration")
                .setMessage("Inform points for " + getDialogPlayerName(dialogInterface))
                .setView(R.id.dialog_player_points)
                .setCancelable(false)
                .setPositiveButton("Save play", this::savePlay)
                .setNegativeButton("Cancel player", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }

    private void savePlay(DialogInterface dialogInterface, int i) {
        new Toast(getContext()).setText("player: " );
    }

    private void editPlay(View view) {
    }

    private String getDialogPlayerName(DialogInterface dialog) {
        EditText view = ((AlertDialog) dialog).findViewById(R.id.dialog_player_name);
        return view.getText().toString();
    }

    private String getDialogPlayerPoints(DialogInterface dialog) {
        EditText view = ((AlertDialog) dialog).findViewById(R.id.dialog_player_points);
        return view.getText().toString();
    }

}
