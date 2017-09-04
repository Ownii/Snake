package eu.ownii.snake.objects;

import android.util.Log;

/**
 * Created by marti on 30.07.2017.
 */

public class Point
{
    private int x;
    private int y;

    private int botX = Integer.MIN_VALUE, topX = Integer.MAX_VALUE;
    private int botY = Integer.MIN_VALUE, topY = Integer.MAX_VALUE;

    public Point()
    {
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public void x(int x)
    {
        this.x = x;
        constrain();
    }

    public void y(int y)
    {
        this.y = y;
        constrain();
    }

    public void setConstrain(int botX, int topX, int botY, int topY)
    {
        this.botX = botX;
        this.topX = topX;
        this.botY = botY;
        this.topY = topY;
        constrain();
    }

    private void constrain()
    {
        if( x < botX )
            x = topX;
        if( x > topX )
            x = botX;

        if( y < botY )
            y = topY;
        if( y > topY )
            y = botY;
    }

    public void random()
    {
        x = (int) (Math.random()*(topX-botX)+botX);
        y = (int) (Math.random()*(topY-botY)+botY);
    }

    @Override
    public boolean equals(Object obj)
    {
        if( !(obj instanceof Point) )
            return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }

    public Point clone()
    {
        Point p = new Point();
        p.x = this.x;
        p.y = this.y;
        p.botX = this.botX;
        p.topX = this.topX;
        p.botY = this.botY;
        p.topY = this.topY;
        return p;
    }

    public int getBotX()
    {
        return botX;
    }

    public int getTopX()
    {
        return topX;
    }

    public int getBotY()
    {
        return botY;
    }

    public int getTopY()
    {
        return topY;
    }

    @Override
    public String toString()
    {
        return "x: " + x + ";y: " + y;
    }

    @Override
    public int hashCode()
    {
        return x*1000+y;
    }
}
