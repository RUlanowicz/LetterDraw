package edu.pitt.cs1635.rru3.prog2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();
    boolean newSegment = false;
    int measuredHeight = getMeasuredHeight();
    int measuredWidth = getMeasuredWidth();

    ArrayList<Point> myPoints = new ArrayList<Point>();
    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        setMyPaint(myPaint);

    }
    @Override
    protected void onDraw(Canvas canvas){
        Log.i(TAG,Integer.toString(canvas.getHeight()));
        canvas.drawPath(path, myPaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Point point = new Point();
        myPoints.add(point);
        if(newSegment){
            if(event.getAction() == MotionEvent.ACTION_UP){
                newSegment = false;
            }
            path.lineTo(event.getX(),event.getY());
        }
        else{
            newSegment = true;
            path.moveTo(event.getX(),event.getY());
        }
        invalidate();
        return true;
    }

    public void setMyPaint(Paint myPaint) {
        this.myPaint = myPaint;
        this.myPaint.setColor(Color.BLACK);
        this.myPaint.setStyle(Paint.Style.STROKE);
        this.myPaint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(
                parentWidth, parentHeight);
        Log.i(TAG,Integer.toString(parentWidth));
        Log.i(TAG,Integer.toString(parentHeight));
    }

}
class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}