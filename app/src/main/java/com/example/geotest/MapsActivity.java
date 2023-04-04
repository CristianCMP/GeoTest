package com.example.geotest;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.geotest.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //LatLng sydney = new LatLng(-29.4443789, -51.95636597);
        //mMap.addMarker(new MarkerOptions().position(sydney));

        //for(int i=0; i < 10; i++){
        //    mMap.addMarker(new MarkerOptions().position(new LatLng(-29.4443789, -51.95636597)));
        //}

        Bundle bundle = getIntent().getExtras();

        ArrayList<Location> listaLocalizacoes = new ArrayList<>();
        listaLocalizacoes = bundle.getParcelableArrayList("localizacoes");

        System.out.println("Antes");

        PolylineOptions rota = new PolylineOptions();
        rota.color(Color.RED);
        rota.width(5);

        for (Location x : listaLocalizacoes){

            System.out.println(x.toString());

            LatLng latlong = new LatLng(x.getLatitude(), x.getLongitude());
            rota.add(latlong);
            mMap.addMarker(new MarkerOptions().position(latlong));
        };

        mMap.addPolyline(rota);

        System.out.println(listaLocalizacoes.size());

        System.out.println("Fora");

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}