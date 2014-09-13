package com.fermitech.eggdrop;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by qgonjon on 9/12/14.
 */
public class ScoreBoard extends Activity {

    TextView displayTextView;

    public void Score(long time){

        long Time = time;

        displayTextView = (TextView) findViewById(R.id.displayTextView);

        displayTextView.setText("Time:" + Time);
    }
}
