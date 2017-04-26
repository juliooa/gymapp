package com.wearenicecorp.gymapp.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wearenicecorp.gymapp.R;

public class GymsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        setDefaultGym();
    }

    private void setDefaultGym() {

        LatLng smartfitGym = new LatLng(-33.4127188, -70.5824303);
        MarkerOptions markerOptions = new MarkerOptions().position(smartfitGym).title("Gimnasio SmartFit");
        map.addMarker(markerOptions );
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(smartfitGym, 20));

    }
}
