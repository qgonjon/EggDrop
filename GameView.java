package com.fermitech.eggdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon;
    private GameLoopThread gameThread;
    int iconWidth;
    int iconHeight;
    protected  GameLogic gameLogic = new GameLogic();

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public String getScore(){
        return Long.toString(System.nanoTime());
    }


    private void init() {


        bmpIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        gameThread = new GameLoopThread(this);
        surfaceHolder = getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void paintCanvas(Canvas canvas, long t) {

        Paint textPaint = new Paint();

        canvas.drawColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);
        canvas.drawText("Time:"+Long.toString(t),70,100,textPaint);
        canvas.drawBitmap(bmpIcon, gameLogic.xPos, gameLogic.yPos, null);

    }

}
