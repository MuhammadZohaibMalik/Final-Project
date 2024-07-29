package com.example.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
            {
                //pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                //resume playback
                mMediaPlayer.start();

            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
                //stop playback
            }

        }



    };


    private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("mustard yellow","chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow","topiisa",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","kelilli",R.drawable.color_white,R.raw.color_white));




        WordAdapter Adapter=new WordAdapter(getActivity(),words,R.color.category_colors);
        ListView listView=(ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word =words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResource());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}