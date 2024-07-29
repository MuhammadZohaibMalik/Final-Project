    package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager=(ViewPager2) findViewById(R.id.viewPager);

        CategoryAdapter adapter=new CategoryAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("NUMBER");
                        break;
                    case 1:
                        tab.setText("FAMILY");
                        break;
                    case 2:
                        tab.setText("COLORS");
                        break;

                    default:
                        tab.setText("PHRASES");
                        break;
                }
            }
        }).attach();







    }

    }