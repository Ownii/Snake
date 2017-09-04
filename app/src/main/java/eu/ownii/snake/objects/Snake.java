package eu.ownii.snake.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by marti on 30.07.2017.
 */

public class Snake
{
    private List<Point> tail = new ArrayList<>();
    private Point velocity = new Point(1, 0);
    private boolean increase = false;

    public Snake(Point start)
    {
        tail.add(start);
        velocity.setConstrain(-1, 1, -1, 1);
    }

    /**
     * moves the snake one block forward
     * @return true if snake hits herself
     */
    public boolean update()
    {
        boolean hit = false;
        Point head = tail.get(tail.size()-1);
        head = head.clone();
        head.x(head.x()+velocity.x());
        head.y(head.y()+velocity.y());
        if( tail.contains(head) )
            hit = true;
        tail.add(head);
        if( !increase )
            tail.remove(0);
        else
            increase = false;

        return hit;
    }

    public boolean hits(Collection<Point> points)
    {
        Point head = tail.get(tail.size()-1);
        return points.contains(head);
    }

    /**
     * increase the snakes tail by the next frame
     */
    public void increase()
    {
        this.increase = true;
    }

    /**
     *
     * @return list of points from tail
     */
    public List<Point> getTail()
    {
        return tail;
    }

    /**
     * current velocity
     * @return
     */
    public Point getVelocity()
    {
        return velocity;
    }
}
