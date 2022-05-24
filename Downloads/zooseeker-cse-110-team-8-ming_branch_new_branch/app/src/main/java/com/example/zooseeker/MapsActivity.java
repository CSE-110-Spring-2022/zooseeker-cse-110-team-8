package com.example.zooseeker;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.zooseeker.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final LatLng UCSD_LAATLNG = new LatLng(32.8801,-117.2340);
    public static final LatLng ZOO_LATLNG = new LatLng(32.7353,-117.1490);
    //public static final CameraUpdate UPDATE = CameraUpdateFactory.zoomTo(11.5f);


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

        // Add a marker in Sydney and move the camera
        var ucsd_Pos= UCSD_LAATLNG;


        mMap.addMarker(new MarkerOptions().position(ucsd_Pos).title("San Diego"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ucsd_Pos));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(UPDATE));
        mMap.setMinZoomPreference(15);
      //  mMap.moveCamera();
    }
}