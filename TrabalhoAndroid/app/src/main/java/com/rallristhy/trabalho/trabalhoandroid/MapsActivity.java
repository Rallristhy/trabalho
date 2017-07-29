package com.rallristhy.trabalho.trabalhoandroid;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.rallristhy.trabalho.trabalhoandroid.model.Estabelecimentos;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void recebeJson(String s){
        Gson gson = new Gson();

        Estabelecimentos[] estabelecimentos = gson.fromJson(s, Estabelecimentos[].class);

        for (int i = 0; i < estabelecimentos.length; i++) {
            Log.d("R4LL", "Nome: "+estabelecimentos[i].getNome());
            Log.d("R4LL", "Latitude: "+estabelecimentos[i].getCoord().getLat());
            Log.d("R4LL", "Longitude: "+estabelecimentos[i].getCoord().getLon());
            Log.d("R4LL", "");

            LatLng x = new LatLng(estabelecimentos[i].getCoord().getLat(),estabelecimentos[i].getCoord().getLon());
            mMap.addMarker(new MarkerOptions().position(x).title(estabelecimentos[i].getNome()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(x));
        }

        //LatLng tvmorena = new LatLng(-20.479415, -54.600827);
        //LatLng padaria = new LatLng(-20.478790, -54.599307);

        //mMap.addMarker(new MarkerOptions().position(padaria).title("Pão Moreno"));
       // mMap.addMarker(new MarkerOptions().position(tvmorena).title("TV Morena"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(tvmorena));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); //MAP_TYPE_NORMAL  MAP_TYPE_HYBRID MAP_TYPE_SATELLITE MAP_TYPE_TERRAIN

        new WebTask(this).execute("https://raw.githubusercontent.com/Rallristhy/trabalho/master/estab2.json");

        // Add a marker in Sydney and move the camera
        /*LatLng tvmorena = new LatLng(-20.479415, -54.600827);
        LatLng padaria = new LatLng(-20.478790, -54.599307);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); //MAP_TYPE_NORMAL  MAP_TYPE_HYBRID MAP_TYPE_SATELLITE MAP_TYPE_TERRAIN
        mMap.addMarker(new MarkerOptions().position(padaria).title("Pão Moreno"));
        mMap.addMarker(new MarkerOptions().position(tvmorena).title("TV Morena"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tvmorena));*/
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(padaria));


    }

}
