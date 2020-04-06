package com.example.pepintourapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.*;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {
    private static final String TAG = "HelpFragment";

    private Button btnNavFrag1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false); //changed R.layout.fragment_help to;
//        btnNavFrag1 = (Button) v.findViewById(R.id.helpToHome);
//        Log.d(TAG, "onCreateView started.");
//
//        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), " Going back to home fragment", Toast.LENGTH_SHORT).show();
//
//                ((MainActivity)getActivity()).setViewPager(0);
//            }
//        });

        return v;
    }

}
