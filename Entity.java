package com.fermitech.eggdrop;

/**
 * Created by qgonjon on 9/12/14.
 */
import java.util.ArrayList;

public class Entity {

    public ArrayList<Integer> xLocation = new ArrayList<Integer>();
    public ArrayList<Integer> yLocation = new ArrayList<Integer>();
    public ArrayList<Integer> objType = new ArrayList<Integer>();

    int index;

    public void Entity(int index, int x, int y, int obj){

        xLocation.add(index,x);
        yLocation.add(index,y);
        objType.add(index,obj);

    }
}
