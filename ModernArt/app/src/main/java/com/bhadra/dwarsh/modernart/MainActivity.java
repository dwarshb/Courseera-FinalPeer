package com.bhadra.dwarsh.modernart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    View view1,view2,view3,view4,view5,view6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = (View)findViewById(R.id.layout1);
        view2 = (View)findViewById(R.id.layout2);
        view3 = (View)findViewById(R.id.layout3);
        view4 = (View)findViewById(R.id.layout4);
        view5= (View)findViewById(R.id.layout5);
        view6= (View)findViewById(R.id.layout6);
        seekbar = (SeekBar)findViewById(R.id.colorProgress);
        final int originalview1 = ((ColorDrawable) view1.getBackground()).getColor();
        final int originalview2 = ((ColorDrawable) view2.getBackground()).getColor();
        final int originalview3 = ((ColorDrawable) view3.getBackground()).getColor();
        final int originalview4 = ((ColorDrawable) view4.getBackground()).getColor();
        final int originalview5 = ((ColorDrawable) view5.getBackground()).getColor();
        final int originalview6 = ((ColorDrawable) view6.getBackground()).getColor();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progresscount = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progresscount  = i;
                setProgressBasedBackgroundColor(view1, originalview1);
                setProgressBasedBackgroundColor(view2, originalview2);
                setProgressBasedBackgroundColor(view3, originalview3);
                setProgressBasedBackgroundColor(view4, originalview4);
                setProgressBasedBackgroundColor(view5, originalview5);
                setProgressBasedBackgroundColor(view6, originalview6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            private void setProgressBasedBackgroundColor(View box, int OriginalBoxColor) {
                float[] hsvColor = new float[3];
                Color.colorToHSV(OriginalBoxColor, hsvColor);
                hsvColor[0] = hsvColor[0] + progresscount;
                hsvColor[0] = hsvColor[0] % 360;
                box.setBackgroundColor(Color.HSVToColor(Color.alpha(OriginalBoxColor), hsvColor));
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
        if (id == R.id.action_more_information) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Inspired by the works of artists such as\\n Ben Nicholson and Piet Mondrian");
            dialog.setPositiveButton(R.string.moma_website, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.moma.org"));
                    startActivity(intent);
                }
            });
            dialog.setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            dialog.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
