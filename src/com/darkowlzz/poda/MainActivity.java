package com.darkowlzz.poda;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends Activity
{
    Context context = this;
    Button mButton;
    MediaPlayer mp = null;
    Random rand = new Random();

    private static LinkedList<Integer> randomHistory = new LinkedList<Integer>();

    // Clean this shit once I learn SAX.
    String[] contributors = {
                             "Jagdish", "Saran", "Praveen",
                             "Vasanth", "Gregory", "Sankar",
                             "Vikneshwar", "Paul", "RV",
                             "Manikandan", "Nebin"
                            };


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });
    }

    /** Called when the optionsMenu is requested. */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }


    //////////////////////////////// Options Menu code ////////////////////////////////////

    /** Called when an item is selected from the Menu. */
    public boolean onOptionsItemSelected(MenuItem item) {
        final Dialog dialog = new Dialog(context);
        TextView text;

        switch (item.getItemId()) {
            case R.id.credits:
                dialog.setContentView(R.layout.credits);
                dialog.setTitle(getString(R.string.credits_title));

                text = (TextView) dialog.findViewById(R.id.text);
                text.setText(getString(R.string.credits_head) + "\n\n" + credits());
                dialog.show();
                break;

            case R.id.about:
                dialog.setContentView(R.layout.about);
                dialog.setTitle(getString(R.string.about_title));

                text = (TextView) dialog.findViewById(R.id.text);
                text.setText(getString(R.string.about_head) + "\n\n" +
                             getString(R.string.about_version) + getString(R.string.version) + "\n" +
                             getString(R.string.about_release) + getString(R.string.release_date)
                            );
                dialog.show();
                break;
        }
        return true;
    }

    /** Return sorted and well formed contributors list. */
    public String credits() {
        String names = "";
        Arrays.sort(contributors);
        for (String contributor : contributors) names += contributor + ", ";
        names = names.substring( 0, names.length() - 2);

        return names;
    }

    /** Return random numbers with 5 non-repeating numbers */
    private Integer getRandom() {
        Integer r = rand.nextInt(11);
        if (randomHistory.contains(r)) {
            return getRandom();
        }
        randomHistory.add(r);
        if (randomHistory.size() > 5) {
            randomHistory.remove();
        }

        return r;
    }


    ///////////////////////// Audio player code //////////////////////////////////

    /** Called when an audio play is requested. */
    private void playSound() {
        switch(getRandom()) {
            case 0: mp = MediaPlayer.create(MainActivity.this, R.raw.poda1);
                    break;
            case 1: mp = MediaPlayer.create(MainActivity.this, R.raw.poda2);
                    break;
            case 2: mp = MediaPlayer.create(MainActivity.this, R.raw.poda4);
                    break;
            case 3: mp = MediaPlayer.create(MainActivity.this, R.raw.poda5);
                break;
            case 4: mp = MediaPlayer.create(MainActivity.this, R.raw.poda6);
                break;
            case 5: mp = MediaPlayer.create(MainActivity.this, R.raw.poda7);
                break;
            case 6: mp = MediaPlayer.create(MainActivity.this, R.raw.poda8);
                break;
            case 7: mp = MediaPlayer.create(MainActivity.this, R.raw.poda9);
                break;
            case 8: mp = MediaPlayer.create(MainActivity.this, R.raw.poda10);
                break;
            case 9: mp = MediaPlayer.create(MainActivity.this, R.raw.poda11);
                break;
            case 10: mp = MediaPlayer.create(MainActivity.this, R.raw.poda12);
                break;
        }

        /** To release the MediaPlayer. */
        mp.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        mp.start();
    }
}
