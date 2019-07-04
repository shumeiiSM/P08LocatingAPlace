package com.example.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spinnerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_SG = new LatLng(1.352083, 103.819839);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,
                        11));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng poi_Central = new LatLng(1.301800, 103.837799);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \nOperating hours: 11am-8pm \nTel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.349860, 103.934190);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \nOperating hours: 9am-5pm \nTel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng poi_North = new LatLng(1.445540, 103.776920);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 \nOperating hours: 10am-5pm \nTel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        spinnerMap = findViewById(R.id.spinner);

        spinnerMap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                switch (i) {
                    case 1:
                        if (map != null) {
                            LatLng poi_North = new LatLng(1.445540, 103.776920);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                    15));
                        }
                        break;
                    case 2:
                        if (map != null) {
                            LatLng poi_Central = new LatLng(1.301800, 103.837799);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                    15));
                        }
                        break;
                    case 3:
                        if (map != null) {
                            LatLng poi_East = new LatLng(1.349860, 103.934190);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                    15));
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_North = new LatLng(1.445540, 103.776920);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                            15));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_Central = new LatLng(1.301800, 103.837799);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                            15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_East = new LatLng(1.349860, 103.934190);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                            15));
                }
            }
        });

    }
}
