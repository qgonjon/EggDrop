package com.fermitech.eggdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by qgonjon on 9/12/14.
 */
public class GameLogic {

    public int xPos = 0;
    public int yPos = 0;
    public int deltaX = 5;
    public int deltaY = 5;


    public void GameMovement(int gWidth, int gHeight, int iWidth, int iHeight){

        int getWidth = gWidth;
        int getHeight = gHeight;
        int iconWidth = iWidth;
        int iconHeight = iHeight;

        xPos += deltaX;
        if(deltaX > 0){
            if(xPos >= getWidth - iconWidth){
                deltaX *= -1;
            }
        }else{
            if(xPos <= 0){
                deltaX *= -1;
            }
        }

        yPos += deltaY;
        if(deltaY > 0){
            if(yPos >= getHeight - iconHeight){
                deltaY *= -1;
            }
        }else{
            if(yPos <= 0){
                deltaY *= -1;
            }
        }
    }


}
