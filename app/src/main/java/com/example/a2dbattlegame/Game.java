package com.example.a2dbattlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/*
* Game Manages all objects in the game and is responsible for updating all states and render
* all objects to the screen
* */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private GameLoop gameLoop;
    //private Context context;

    public Game(Context context) {
        super(context);
        //getContext();
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //this. context = context;
        gameLoop = new GameLoop(this, surfaceHolder);

        //Initialize Player
        player = new Player(getContext(), 2*500, 500, 30);

        setFocusable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Handle different types of touch events actions
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPostion((double) event.getX(), (double) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                player.setPostion((double) event.getX(), (double) event.getY());
                return true;

        }


        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        // Update game state
        player.update();
    }
}
