package eu.ownii.snake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import eu.ownii.snake.R;

public class MainActivity extends AppCompatActivity
{

    private Button bStart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button) findViewById(R.id.bStart);
        bStart.setOnClickListener(v -> startActivity(new Intent(this, GameActivity.class)));
    }
}
