package com.example.geotest;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private Button btMostrar = null;
    private Button btCapturar = null;

    private ArrayList<Location> listaLocalizacoes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMostrar = (Button) findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                System.out.println(listaLocalizacoes.size());

                System.out.println("Antes de entrar");

                bundle.putParcelableArrayList("localizacoes", (ArrayList<? extends Parcelable>) listaLocalizacoes);

                //for (Location x : listaLocalizacoes){
                //    bundle.putSerializable("localizacao", (Serializable) x);
                //};

                System.out.println("Depois de entrar");

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btCapturar = (Button) findViewById(R.id.btCapturar);
        btCapturar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Localização inserida no mapa!", Toast.LENGTH_SHORT).show();
            }
        });

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    public ArrayList<Location> getListaLocalizacoes() {
        return listaLocalizacoes;
    }

    public void setListaLocalizacoes(ArrayList<Location> listaLocalizacoes) {
        this.listaLocalizacoes = listaLocalizacoes;
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("Antes" + listaLocalizacoes.size());
        System.out.println("Latitude: "+ location.getLatitude()+" \nLongitude: " + location.getLongitude());
        listaLocalizacoes.add(location);
        System.out.println("Depois" + listaLocalizacoes.size());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View view) {

    }
}