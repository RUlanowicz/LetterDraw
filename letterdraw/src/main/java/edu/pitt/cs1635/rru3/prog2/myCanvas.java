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
    private int myHeight;
    private int myWidth;
    public StringBuilder toServer;
    private boolean firstTouch;


    ArrayList<Point> myPoints = new ArrayList<Point>();
    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        setMyPaint(myPaint);
        toServer = new StringBuilder();
        firstTouch = true;
    }
    @Override
    protected void onDraw(Canvas canvas){
        myHeight = canvas.getHeight();
        myWidth = canvas.getWidth();
        //Log.i(TAG,"AGAIN");
        canvas.drawPath(path, myPaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Point point = new Point();
        point.x = (int)((event.getX()/myWidth)*254);
        point.y = (int)((event.getY()/myHeight)*254);
        Log.i(TAG,"Coords: "+point.x+" "+point.y);
        myPoints.add(point);
        //Log.i(TAG,"Width "+myWidth);
        //Log.i(TAG,"Height "+myHeight);
        if(newSegment){
            if(event.getAction() == MotionEvent.ACTION_UP){
                newSegment = false;
            }
            toServer.append(", "+point.x+", "+point.y);
            path.lineTo(event.getX(),event.getY());
        }
        else{
            newSegment = true;
            if(firstTouch){
                toServer.append("["+point.x+", "+point.y);
                firstTouch=false;
            }
            else{
                toServer.append(", 255, 0, "+point.x+", "+point.y);
            }
            Log.i(TAG,"My String: "+toServer.toString());
            path.moveTo(event.getX(), event.getY());
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

    public void reset(){
        firstTouch = true;
        newSegment = false;
        toServer = new StringBuilder();
        path = new Path();
        invalidate();
    }
    public void onClick(View view){
        int buttonPressed = view.getId();
        switch(buttonPressed){
            case R.id.blue_button:
                this.myPaint.setColor(Color.BLUE);
                break;
            case R.id.red_button:
                this.myPaint.setColor(Color.RED);
                break;
            case R.id.black_button:
                this.myPaint.setColor(Color.BLACK);
                break;
            case R.id.green_button:
                this.myPaint.setColor(Color.GREEN);
                break;
        }
    }
}
class Point {
    int x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}