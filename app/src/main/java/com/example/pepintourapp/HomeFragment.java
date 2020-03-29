package com.example.pepintourapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.support.v4.app.*;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false); //changed from fragment_home to activity_main.xml file, then back to fragment_home after

        //Creates transition from help button on fragment_home to the help page
        Button tourButton = (Button) v.findViewById(R.id.tours);
        Button helpButton = (Button) v.findViewById(R.id.help);
        Button feedBackButton = (Button) v.findViewById(R.id.feedback);
        Button aboutUsButton = (Button) v.findViewById(R.id.aboutus);

        Button chooseDestinationButton = (Button)v.findViewById(R.id.mydest);



        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container_home, new HelpFragment());
                fr.commit();
            }

        });

        tourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container_home, new TourFragment());
                fr.commit();
            }
        });

        feedBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container_home, new FeedbackFragment());
                fr.commit();
            }
        });

        chooseDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), choose_destination.class);
                in.putExtra("Some", "random strings for testing");
                Log.i("tag", "I AM HEREEE!");
                startActivity (in);
            }
        });

        return v;
    }
}
