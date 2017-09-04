package eu.ownii.snake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

import eu.ownii.snake.R;

public class ResultActivity extends AppCompatActivity
{

    private TextView tvScore;
    private Button bRestart, bMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore     = (TextView) findViewById(R.id.tvScore);
        bRestart    = (Button) findViewById(R.id.bRestart);
        bMenu       = (Button) findViewById(R.id.bMenu);

        if( !getIntent().getExtras().containsKey("score") )
            finish();

        int score = getIntent().getExtras().getInt("score");

        tvScore.setText(String.format(getString(R.string.your_score), score));

        bRestart.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, GameActivity.class));
            finish();
        });
        bMenu.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
            finish();
        });
    }
}
