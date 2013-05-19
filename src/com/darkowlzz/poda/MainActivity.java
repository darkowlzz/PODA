package com.darkowlzz.poda;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends Activity
{
    Sound sound = new Sound();
    Context context = this;
    ImageButton mButton;

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

        mButton = (ImageButton) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.playSound(context);
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
        // Remove ", " from the end.
        names = names.substring( 0, names.length() - 2);

        return names;
    }

}
