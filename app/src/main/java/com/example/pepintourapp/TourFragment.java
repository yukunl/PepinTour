package com.example.pepintourapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class TourFragment extends Fragment /*implements OnMapReadyCallback*/ {
//
//    private GoogleMap mMap
//
//    SupportMapFragment mapFragment;
//    public TourFragment() {
//
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
//        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//        if (mapFragment == null) {
//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            mapFragment = SupportMapFragment.newInstance();
//            ft.replace(R.id.map, mapFragment).commit();
//
//        }
//        mapFragment.getMapAsync(this);
        return v;
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng mansoura = new LatLng(31.037933, 31.381523);
//        mMap.addMarker(new MarkerOptions().position(mansoura).title("Marker in mansoura"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(mansoura));
//    }
}
