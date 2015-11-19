package com.example.marco.momauilab;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout red = (LinearLayout) findViewById(R.id.red);
        final LinearLayout yellow = (LinearLayout) findViewById(R.id.yellow);
        final LinearLayout blue = (LinearLayout) findViewById(R.id.blue);
        final LinearLayout white = (LinearLayout) findViewById(R.id.white);
        final LinearLayout green = (LinearLayout) findViewById(R.id.green);

        final int redColor = Color.rgb(255,0,0);
        final int yellowColor = Color.rgb(255,255,0);
        final int blueColor = Color.rgb(0,0,255);
        final int greenColor = Color.rgb(0,255,0);
        final int whiteColor = Color.rgb(255,255,255);


        red.setBackgroundColor(redColor);
        yellow.setBackgroundColor(yellowColor);
        blue.setBackgroundColor(blueColor);
        green.setBackgroundColor(greenColor);
        white.setBackgroundColor(whiteColor);

        SeekBar s = (SeekBar) findViewById(R.id.seekBar);

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red.setBackgroundColor(getBlendedColor(redColor, yellowColor, progress));
                yellow.setBackgroundColor(getBlendedColor(yellowColor, whiteColor, progress));
                blue.setBackgroundColor(getBlendedColor(blueColor, greenColor, progress));
                green.setBackgroundColor(getBlendedColor(greenColor, blueColor, progress));
                white.setBackgroundColor(getBlendedColor(whiteColor, redColor, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public int getBlendedColor(int color1, int color2, int alpha) {
                double alphaToUse = alpha / 100.0;
                int red1 = Color.red(color1);
                int green1 = Color.green(color1);
                int blue1 = Color.blue(color1);

                int red2 = Color.red(color2);
                int green2 = Color.green(color2);
                int blue2 = Color.blue(color2);

                int red3 = (int) ((1 - alphaToUse) * red1 + alphaToUse * red2);
                int blue3 = (int) ((1 - alphaToUse) * blue1 + alphaToUse * blue2);
                int green3 = (int) ((1 - alphaToUse) * green1 + alphaToUse * green2);

                return Color.rgb(red3, green3, blue3);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.moreInfoDialog) {
            DialogFragment fragment = new dialogFragment();
            fragment.show(getFragmentManager(), "moreInfo");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
