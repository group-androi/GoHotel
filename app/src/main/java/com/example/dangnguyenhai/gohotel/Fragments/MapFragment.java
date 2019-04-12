package com.example.dangnguyenhai.gohotel.Fragments;

import android.Manifest;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {
    private GoogleMap mGoogleMap;
    private TextView tvAddress;
    private SupportMapFragment map;
    private String address;

    public static MapFragment newInstance(String address) {
        MapFragment myFragment = new MapFragment();

        Bundle args = new Bundle();
        args.putString("address", address);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        tvAddress = rootView.findViewById(R.id.tvAddress);
        tvAddress.setText(address);
        map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
                mGoogleMap.getUiSettings().setCompassEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
            }
        });

        return rootView;
    }
}
