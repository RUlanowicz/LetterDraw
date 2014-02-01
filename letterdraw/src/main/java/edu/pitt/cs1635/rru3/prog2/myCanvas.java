package edu.pitt.cs1635.rru3.prog2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ulanowicz on 2/1/14.
 */
public class myCanvas extends View implements View.OnTouchListener{
    private static final String TAG = "myCanvas";
    Paint myPaint = new Paint();
    ArrayList<Point> myPoints = new ArrayList<Point>();

    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);

    }
    @Override
    protected void onDraw(Canvas canvas){

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        myPoints.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);
        return true;
    }
}
class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}