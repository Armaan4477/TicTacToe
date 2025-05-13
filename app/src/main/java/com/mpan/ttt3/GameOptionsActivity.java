package com.mpan.ttt3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

/** @noinspection ALL */
public class GameOptionsActivity extends Activity {

    private ToggleButton toggleMode1;
    private ToggleButton toggleMode2;
    private EditText editTextPlayer1;
    private EditText editTextPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        toggleMode1 = findViewById(R.id.toggle_mode1);
        toggleMode2 = findViewById(R.id.toggle_mode2);
        editTextPlayer1 = findViewById(R.id.edit_text_player1);
        editTextPlayer2 = findViewById(R.id.edit_text_player2);

        Button buttonStart = findViewById(R.id.button_start);
        Button buttonCredits = findViewById(R.id.button_credits);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Name = editTextPlayer1.getText().toString();
                String player2Name = toggleMode1.isChecked() ? editTextPlayer2.getText().toString() : "BOT";

                Intent intent = new Intent(GameOptionsActivity.this, MainActivity.class);
                intent.putExtra("player1Name", player1Name);
                intent.putExtra("player2Name", player2Name);
                intent.putExtra("isTwoPlayersMode", toggleMode1.isChecked());
                intent.putExtra("isHardDifficulty", toggleMode2.isChecked());
                startActivity(intent);
                finish();
            }
        });

        buttonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCredits();
            }
        });

        toggleMode1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                toggleMode2.setVisibility(View.GONE);
                editTextPlayer2.setVisibility(View.VISIBLE);
            } else {
                toggleMode2.setVisibility(View.VISIBLE);
                editTextPlayer2.setVisibility(View.GONE);
            }
        });
    }

    /** @noinspection DataFlowIssue*/
    private void showCredits() {
        Dialog creditsDialog = new Dialog(this);
        creditsDialog.setContentView(R.layout.credits_dialog);
        creditsDialog.setCancelable(true);
        creditsDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageButton logoButton = creditsDialog.findViewById(R.id.logoButton);
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite(v);
            }
        });

        creditsDialog.show();
    }

    public void openWebsite(View view) {
        Uri uri = Uri.parse("https://armaan44.is-a.dev/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
