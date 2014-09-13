package com.fermitech.eggdrop;

/**
 * Created by qgonjon on 9/12/14.
 */
import android.graphics.Canvas;

public class GameLoopThread extends Thread {

    GameView deviceView;
    private boolean gameRunning = false;
    long timeDelay = 0;

    public GameLoopThread(GameView view) {
        deviceView = view;
    }

    public void setRunning(boolean action) {
        gameRunning = action;
    }

    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
        long lastFpsTime=0;
        int fps=0;
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while(gameRunning){

            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {

                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            // doGameUpdates(delta);
            deviceView.gameLogic.GameMovement(deviceView.getWidth(),deviceView.getHeight(),deviceView.iconWidth,deviceView.iconHeight);

            // draw everything
            Canvas canvas = deviceView.getHolder().lockCanvas();

            if(canvas != null){
                synchronized (deviceView.getHolder()) {
                    deviceView.paintCanvas(canvas,timeDelay);
                }
                deviceView.getHolder().unlockCanvasAndPost(canvas);
            }

            timeDelay = -(lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;

            try {
                sleep(timeDelay);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


}
