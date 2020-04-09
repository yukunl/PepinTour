package com.example.pepintourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("OnCreate:", "Started");
//
//        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
//
//        mViewPager = (ViewPager) findViewById(R.id.containter);
//        //setup pager
//        setupViewPager(mViewPager);
//
//
//        //Code for creating the navbar on the bottom of the app, and setting default page to the homeFragment (fragment_home.xml)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

//    private void setupViewPager(ViewPager viewPager) {
//        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new TourFragment(), "TourFragment");
//        adapter.addFragment(new TourFragment(), "HelpFragment");
//        adapter.addFragment(new TourFragment(), "FeedbackFragment");
//
//        viewPager.setAdapter(adapter);
//    }
//
//    public void setViewPager (int fragmentNumer) {
//        mViewPager.setCurrentItem(fragmentNumer);
//    }




    //Navbar method for redirecting to different fragments
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new HomeFragment();

            switch(item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_tour:
                    selectedFragment = new TourFragment();
                    break;
                case R.id.nav_help:
                    selectedFragment = new HelpFragment();
                    break;
                case R.id.nav_feedback:
                    selectedFragment = new FeedbackFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}

//This comment is a test to see if the github repo updates!
