package com.darkowlzz.poda;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import java.util.LinkedList;
import java.util.Random;

/**
 * To handle all the sound related activities.
 */
public class Sound {
    MediaPlayer mp = null;
    Random rand = new Random();
    private static LinkedList<Integer> randomHistory = new LinkedList<Integer>();

    /** Called when an audio play is requested. */
    public void playSound(Context context) {
        switch(getRandom()) {
            case 0: mp = MediaPlayer.create(context, R.raw.poda1);
                break;
            case 1: mp = MediaPlayer.create(context, R.raw.poda2);
                break;
            case 2: mp = MediaPlayer.create(context, R.raw.poda4);
                break;
            case 3: mp = MediaPlayer.create(context, R.raw.poda5);
                break;
            case 4: mp = MediaPlayer.create(context, R.raw.poda6);
                break;
            case 5: mp = MediaPlayer.create(context, R.raw.poda7);
                break;
            case 6: mp = MediaPlayer.create(context, R.raw.poda8);
                break;
            case 7: mp = MediaPlayer.create(context, R.raw.poda9);
                break;
            case 8: mp = MediaPlayer.create(context, R.raw.poda10);
                break;
            case 9: mp = MediaPlayer.create(context, R.raw.poda11);
                break;
            case 10: mp = MediaPlayer.create(context, R.raw.poda12);
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

    /** Return random numbers with 5 non-repeating numbers */
    private Integer getRandom() {
        Integer r = rand.nextInt(11);
        if (randomHistory.contains(r)) {
            return getRandom();
        }
        randomHistory.add(r);
        if (randomHistory.size() > 7) {
            randomHistory.remove();
        }

        return r;
    }

}