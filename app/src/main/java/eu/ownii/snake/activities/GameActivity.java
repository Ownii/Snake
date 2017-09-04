package eu.ownii.snake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import eu.ownii.snake.R;
import eu.ownii.snake.objects.Food;
import eu.ownii.snake.objects.Helper;
import eu.ownii.snake.objects.Point;
import eu.ownii.snake.objects.Snake;
import eu.ownii.snake.views.GameView;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener
{

    GameView gameView;
    private int grid = 25;
    private Timer timer;
    private long rate = 1000/8;
    private Snake snake;
    private Food food;
    //private HashSet<Point> walls = new HashSet<>();

    private float touchX, touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // init gameView
        gameView = (GameView) findViewById(R.id.gameView);
        gameView.setGrid(grid);
        gameView.setOnTouchListener(this);
       /* gameView.setWalls(walls);
        walls.add(new Point(0,0));
        walls.add(new Point(1,0));
        walls.add(new Point(2,0));
        walls.add(new Point(3,0));
        walls.add(new Point(0,1));
        walls.add(new Point(0,2));
        walls.add(new Point(0,3));
        walls.add(new Point(0,24));
        walls.add(new Point(1,24));
        walls.add(new Point(2,24));
        walls.add(new Point(3,24));
        walls.add(new Point(0,23));
        walls.add(new Point(0,22));
        walls.add(new Point(0,21));
        walls.add(new Point(24,0));
        walls.add(new Point(23,0));
        walls.add(new Point(22,0));
        walls.add(new Point(21,0));
        walls.add(new Point(24,1));
        walls.add(new Point(24,2));
        walls.add(new Point(24,3));
        walls.add(new Point(24,24));
        walls.add(new Point(23,24));
        walls.add(new Point(22,24));
        walls.add(new Point(21,24));
        walls.add(new Point(24,23));
        walls.add(new Point(24,22));
        walls.add(new Point(24,21));*/

        // init snake
        Point start = new Point();
        start.setConstrain(0, grid-1, 0, grid-1);
        start.random();
        snake = new Snake(start);
        gameView.getSnakes().add(snake);

        // init food
        food = new Food();
        food.getPosition().setConstrain(0, grid-1, 0, grid-1);
        food.getPosition().random();
        gameView.setFood(food);

        // init framerate
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                for(Snake snake: gameView.getSnakes())
                {
                    // snake hit anything
                    if( snake.update() /*|| snake.hits(walls)*/ )
                    {
                        timer.cancel();
                        Intent resultIntent = new Intent(GameActivity.this, ResultActivity.class);
                        resultIntent.putExtra("score", snake.getTail().size()-1);
                        startActivity(resultIntent);
                        finish();
                    }
                    // snake hits food and increases tail
                    if( snake.getTail().get(snake.getTail().size()-1).equals(food.getPosition()) )
                    {
                        snake.increase();
                        food.getPosition().random();
                    }
                    // update gameView
                    gameView.invalidate();
                }
            }
        }, rate, rate);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        // detect swipes
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                // horizontal
                if( snake.getVelocity().x() == 0 )
                {
                    snake.getVelocity().y(0);
                    // detect direction
                    if (event.getX() - touchX > 0)
                    {
                        // right swipe
                        snake.getVelocity().x(1);
                    } else
                    {
                        // left swipe
                        snake.getVelocity().x(-1);
                    }
                }
                // vertical
                else if( snake.getVelocity().y() == 0 )
                {
                    snake.getVelocity().x(0);
                    // detect direction
                    if( event.getY()-touchY > 0 )
                    {
                        // down swipe
                        snake.getVelocity().y(1);
                    }
                    else
                    {
                        // up swipe
                        snake.getVelocity().y(-1);
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;
        }
        return true;
    }
}
