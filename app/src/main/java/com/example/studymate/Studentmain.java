package com.example.studymate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Studentmain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmain);
         TabLayout tabLayout;
         ViewPager2 viewPager2;
         ViewPagerAdapter1 adapter;


         tabLayout = findViewById(R.id.tablayout);
         viewPager2 = findViewById(R.id.viewPager);
            tabLayout.addTab(tabLayout.newTab().setText("Student Signup"));
            tabLayout.addTab(tabLayout.newTab().setText("Student Login"));

            FragmentManager fragmentManager = getSupportFragmentManager();
            adapter = new ViewPagerAdapter1(fragmentManager, getLifecycle());
            viewPager2.setAdapter(adapter);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager2.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    tabLayout.selectTab(tabLayout.getTabAt(position));
                }
            });
        }
    }
