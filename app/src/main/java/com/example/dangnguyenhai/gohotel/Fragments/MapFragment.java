package com.example.dangnguyenhai.gohotel.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class MapFragment extends Fragment {
    private TextView tvAddress;
    private String address;
    private GoogleMap mMap;
    private Context context;
    private double latitude;
    private double longitude;
    private float currentZoom = 12.0f;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static MapFragment newInstance(String address) {
        MapFragment myFragment = new MapFragment();

        Bundle args = new Bundle();
        args.putString("address", address);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = getArguments().getString("address");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        tvAddress = rootView.findViewById(R.id.tvAddress);
        tvAddress.setText(address);
        String lastLocation = PreferenceUtils.getLatLocation(context);
        if (!lastLocation.equals("")) {
            latitude = Double.parseDouble(PreferenceUtils.getLatLocation(context));
            longitude = Double.parseDouble(PreferenceUtils.getLongLocation(context));
        }
        setUpMapIfNeeded();


        return rootView;
    }

    private void setUpMapIfNeeded() {
        if (getActivity() != null && mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = null;

            try {
                mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mapFragment != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mMap = googleMap;
                        // Check if we were successful in obtaining the map.
                        if (mMap != null) {
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }

                            mMap.setMyLocationEnabled(true);
                            mMap.getUiSettings().setMapToolbarEnabled(false);
                            mMap.getUiSettings().setCompassEnabled(true);
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);

                            // move camera to current position
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                                    longitude), currentZoom));

                        }
                    }
                });


            }

        }
    }

}
