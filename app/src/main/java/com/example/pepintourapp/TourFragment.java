package com.example.pepintourapp;

import android.Manifest;
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
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap;

import java.util.Locale;

import static android.content.ContentValues.TAG;


public class TourFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
//
//    private GoogleMap mMap
//
//    SupportMapFragment mapFragment;
//    public TourFragment() {
//
//    }


    /*@Nullable*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tour, container, false);
   /*     mapView = (MapView) v.findViewById(R.id.fragment_map);
        mapView.onCreate(savedInstanceState);*/
   /*     SupportMapFragment mapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);


        // Gets to GoogleMap from the MapView and does initialization stuff
         mapView.getMapAsync(this);
*/

         setHasOptionsMenu(true);

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

/*    @Override
    public void onViewCreated ( View view, Bundle savedInstanceState ){
        mapView = view.findViewById(R.id.hybrid);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


    }*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("FragCreateList","onCreateOptionsMenu called");
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.map_options, menu);

    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng home = new LatLng(45.081329, 7.566367);


        float zoom = 15;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, zoom));

        GroundOverlayOptions homeOverlay = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_accessibility_black_24dp))

                .position(home, 100);
        // 100 = width in meters of the desired overlay
        mMap.addGroundOverlay(homeOverlay);

        setMapLongClick(mMap);

        setPoiClick(mMap);

        setInfoWindowClickToPanorama(mMap);


        enableMyLocation();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                return super.onOptionsItemSelected(item);

        }
    }
    private void setMapLongClick(final GoogleMap map) {

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                String snippet = String.format(Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latLng.latitude,
                        latLng.longitude);

                map.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(getString(R.string.dropped_pin))
                                .snippet(snippet)

                                .icon(BitmapDescriptorFactory.defaultMarker
                                        (BitmapDescriptorFactory.HUE_BLUE))

                );

            }
        });
    }



    private void setPoiClick(final GoogleMap map) {

        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
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

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                            { Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
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
