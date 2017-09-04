package eu.ownii.snake.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import eu.ownii.snake.R;
import eu.ownii.snake.objects.Food;
import eu.ownii.snake.objects.Point;
import eu.ownii.snake.objects.Snake;

/**
 * Created by marti on 30.07.2017.
 */

public class GameView extends View
{
    private List<Snake> snakes = new ArrayList<>();
    private Food food;
    private int grid;
    private HashSet<Point> walls = new HashSet<>();

    public GameView(Context context)
    {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint background = new Paint();
        background.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), background);
        float cell = canvas.getWidth()*1f/grid;

        // draw snake
        Paint snakePaint = new Paint();
        snakePaint.setColor(getContext().getResources().getColor(R.color.snake));
        for(Snake snake: snakes)
        {
            for(Point p: snake.getTail())
            {
                canvas.drawRect(p.x()*cell+1, p.y()*cell+1, p.x()*cell+cell-1, p.y()*cell+cell-1, snakePaint);
            }
        }

        // draw walls
        Paint wallPaint = new Paint();
        wallPaint.setColor(getContext().getResources().getColor(R.color.walls));
        for(Point wall: walls)
        {
            canvas.drawRect(wall.x()*cell+1, wall.y()*cell+1, wall.x()*cell+cell-1, wall.y()*cell+cell-1, wallPaint);
        }

        // draw food
        Paint foodPaint = new Paint();
        foodPaint.setColor(getContext().getResources().getColor(R.color.colorAccent));
        canvas.drawRect(food.getPosition().x()*cell, food.getPosition().y()*cell, food.getPosition().x()*cell+cell, food.getPosition().y()*cell+cell, foodPaint);
    }

    public List<Snake> getSnakes()
    {
        return snakes;
    }

    public Food getFood()
    {
        return food;
    }

    public void setFood(Food food)
    {
        this.food = food;
    }

    public int getGrid()
    {
        return grid;
    }

    public void setGrid(int grid)
    {
        this.grid = grid;
    }

    public void setSnakes(List<Snake> snakes)
    {
        this.snakes = snakes;
    }

    public HashSet<Point> getWalls()
    {
        return walls;
    }

    public void setWalls(HashSet<Point> walls)
    {
        this.walls = walls;
    }
}
