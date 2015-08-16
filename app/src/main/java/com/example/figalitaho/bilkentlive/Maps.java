package com.example.figalitaho.bilkentlive;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Maps extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    boolean fromDinings = false;
    ArrayList<Node> markers = new ArrayList<>();
    Node found = null;
    NodeList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fromDinings = getIntent().getBooleanExtra("fromDinings", false);

        if (!fromDinings) {
            SplitAnimation.prepareAnimation(this);
            SplitAnimation.animate(this, 900);
        }
        fromDinings = false;

        AssetManager nodeFile = getAssets();

        Intent intent = getIntent();
        String location = null;
        if ( intent != null)
            location = intent.getStringExtra("location");
        else
            location = "DEFAULT";
        Log.d("Progress", "Retrieved Location: " + location);

        try {
            InputStream file = nodeFile.open("loc.txt");

            list = new NodeList(new InputStreamReader(file));
            markers = list.getList();
            found = list.findNode(location);

            if (found == null){
                Log.i("Found", "Not Found");
            }
        } catch (IOException e) {
            Log.i("file_error", "Incorrect File");
        }

        setUpMapIfNeeded(found, markers);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (markers != null){
            setUpMapIfNeeded(found, markers);
        }
        else
            Log.i("file_error", "Incorrect File");
    }


    private void setUpMapIfNeeded( Node nodeFound, ArrayList<Node> markers) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(nodeFound, markers);
            }
        }
    }

    private void setUpMap(  Node nodeFound, final ArrayList<Node> markers) {


        for( Node p: markers){
            mMap.addMarker(new MarkerOptions().position(new LatLng(p.getxAxis(), p.getyAxis())).title(p.getData()));
        }

        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
        mMap.setMyLocationEnabled(true);

        if (nodeFound != null){
            MarkerOptions point = new MarkerOptions().position(new LatLng(nodeFound.getxAxis(), nodeFound.getyAxis())).title(nodeFound.getData());
            mMap.addMarker(point).showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(nodeFound.getxAxis(), nodeFound.getyAxis()), 18));
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Location Not Found")
                    .setMessage("May not be on Bilkent Campus.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Node notFound = list.findNode("DEFAULT");
                            MarkerOptions point = new MarkerOptions().position(new LatLng(notFound.getxAxis(), notFound.getyAxis())).title(notFound.getData());
                            mMap.addMarker(point).showInfoWindow();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(notFound.getxAxis(), notFound.getyAxis()), 18));
                        }
                    })
                    .show();
        }
    }
}
