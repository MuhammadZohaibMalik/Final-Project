package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("where are you going ?","minto wuksus"));
        words.add(new Word("what is your name","tinna oyaasine"));
        words.add(new Word("my name is","oyyasit"));
        words.add(new Word("how are you feeling","michakas"));
        words.add(new Word("im feeling good","kuchi achit"));
        words.add(new Word("Are you coming","aanas'aa"));
        words.add(new Word("Yes im coming","haa'aanam"));
        words.add(new Word("im coming","aanam"));





        WordAdapter Adapter=new WordAdapter(this,words);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(Adapter);





    }
}