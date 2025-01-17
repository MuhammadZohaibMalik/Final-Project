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

public class PhrasesFragment extends Fragment {
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



    public PhrasesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("where are you going ?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("what is your name","tinna oyaasine",R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is","oyyasit",R.raw.phrase_my_name_is));
        words.add(new Word("how are you feeling","michakas",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("im feeling good","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming","aanas'aa",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes im coming","haa'aanam",R.raw.phrase_yes_im_coming));
        words.add(new Word("im coming","aanam",R.raw.phrase_im_coming));





        WordAdapter Adapter=new WordAdapter(getActivity(),words,R.color.category_phrases);
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