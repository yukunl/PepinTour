package com.example.pepintourapp;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import static android.content.ContentValues.TAG;


public class TourFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    String MAP = "Map";
    String BLANK = "Blank";
    SharedPreferences sharedPreferences;
    int locationCount = 0;

    /*@Nullable*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment

        mapFragment.getMapAsync(this);
        setHasOptionsMenu(true);
        return v;
    }


    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        Log.i("FragCreateList","onCreateOptionsMenu called");
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.map_options, menu);
//
//    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("FragCreateList", "onCreateOptionsMenu called");
        inflater.inflate(R.menu.map_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.i("Frag", "onMapReady called");
        LatLng NEWARK = new LatLng(40.714086, -74.228697);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

       // mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
        enableMyLocation();
        setMapLongClick(mMap);

        setPoiClick(mMap);

        setMaponClick(mMap);
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4219999, -122.0862462))
//                .title("Marker 1")
//                .icon(BitmapDescriptorFactory.defaultMarker
//                        (BitmapDescriptorFactory.HUE_BLUE)));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4629101, -122.2449094))
//                .title("Marker 2 ")
//                .snippet("His Talent : Plenty of money"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.3092293, -122.1136845))
//                .title("Captain America"));

        // Opening the sharedPreferences object
        sharedPreferences = this.getActivity().getSharedPreferences("location", 0);

        // Getting number of locations already stored
        locationCount = sharedPreferences.getInt("locationCount", 0);


        // Getting stored zoom level if exists else return 0
        String zoom = sharedPreferences.getString("zoom", "0");

        // If locations are already saved
        if (locationCount != 0) {

            String lat = "";
            String lng = "";

            // Iterating through all the locations stored
            for (int i = 0; i < locationCount; i++) {

                // Getting the latitude of the i-th location
                lat = sharedPreferences.getString("lat" + i, "0");

                // Getting the longitude of the i-th location
                lng = sharedPreferences.getString("lng" + i, "0");
                Toast.makeText(getActivity().getBaseContext(), lat + "," + lng, Toast.LENGTH_LONG).show();

                // Drawing marker on the map
                drawMarker(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));
            }


            //setInfoWindowClickToPanorama(mMap);


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Frag", "onOptionsItemSelected called");
        int id = item.getItemId();
        switch (id) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return false;

        }
    }


    @Override
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }


    private void setInfoWindowClickToPanorama(GoogleMap map) {
        map.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        // and check the marker tag
                        if (marker.getTag() == "poi") {
                            // with a SupportStreetViewPanoramaFragment
                            StreetViewPanoramaOptions options =
                                    new StreetViewPanoramaOptions().position(
                                            marker.getPosition());

                            // with a SupportStreetViewPanoramaFragment
                            SupportStreetViewPanoramaFragment streetViewFragment
                                    = SupportStreetViewPanoramaFragment
                                    .newInstance(options);

                        }

                    }
                });

    }

    private void setMaponClick(final GoogleMap mMap) {

        Log.i("Frag", "setMaponClick");
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                locationCount++;

                // Drawing marker on the map
                drawMarker(point);

                /** Opening the editor object to write data to sharedPreferences */
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Storing the latitude for the i-th location
                editor.putString("lat"+ Integer.toString((locationCount-1)), Double.toString(point.latitude));

                // Storing the longitude for the i-th location
                editor.putString("lng"+ Integer.toString((locationCount-1)), Double.toString(point.longitude));

                // Storing the count of locations or marker count
                editor.putInt("locationCount", locationCount);

                /** Storing the zoom level to the shared preferences */
                editor.putString("zoom", Float.toString(mMap.getCameraPosition().zoom));

                /** Saving the values stored in the shared preferences */
                editor.commit();

                // Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setPoiClick(final GoogleMap mMap) {
        mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {

                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .title(poi.name));
                poiMarker.setTag("poi");
                poiMarker.showInfoWindow();
            }
        });
    }

    private void setMapLongClick(final GoogleMap mMap) {

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

// Removing the marker and circle from the Google Map
                mMap.clear();

                // Opening the editor object to delete data from sharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Clearing the editor
                editor.clear();

                // Committing the changes
                editor.commit();

                // Setting locationCount to zero
                locationCount=0;

            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }

    }

    private void drawMarker(LatLng latLng) {
        Log.i("Frag", "draw marker");
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(latLng);

        // Adding marker on the Google Map
        mMap.addMarker(markerOptions);


    }
}