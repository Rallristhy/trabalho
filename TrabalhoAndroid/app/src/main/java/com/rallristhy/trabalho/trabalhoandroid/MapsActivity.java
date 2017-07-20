package com.rallristhy.trabalho.trabalhoandroid;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rallristhy.trabalho.trabalhoandroid.model.Estabelecimentos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        new WebTask(this).execute("https://raw.githubusercontent.com/Rallristhy/trabalho/master/estab2.json");
    }

    public void recebeJson(String s){
        Gson gson = new Gson();
        //Log.d("R4LL", s);
        //Estabelecimentos e = gson.fromJson(s, Estabelecimentos.class);

        Estabelecimentos[] estabelecimentoses = gson.fromJson(s, Estabelecimentos[].class);

        for (int i = 0; i < estabelecimentoses.length; i++) {
            Log.d("R4LL", "Nome: "+estabelecimentoses[i].getNome());
            Log.d("R4LL", "Nome: "+estabelecimentoses[i].getCoord().getLat());
            Log.d("R4LL", "Nome: "+estabelecimentoses[i].getCoord().getLon());

        }
        //Log.d("R4LL", estabelecimentoses[0].getNome()+"");

        //Log.d("R4LL", estabelecimentos[0].getNome());
        //Log.d("R4LL", estabelecimentos[0].getCoord().getLat()+"");
        //Log.d("R4LL", estabelecimentos[0].getCoord().getLon()+"");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng tvmorena = new LatLng(-20.479415, -54.600827);
        LatLng padaria = new LatLng(-20.478790, -54.599307);
        mMap.addMarker(new MarkerOptions().position(padaria).title("Pão Moreno"));
        mMap.addMarker(new MarkerOptions().position(tvmorena).title("TV Morena"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tvmorena));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(padaria));
    }
}
