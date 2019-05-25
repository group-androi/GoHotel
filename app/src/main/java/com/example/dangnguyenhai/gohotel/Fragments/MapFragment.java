package com.example.dangnguyenhai.gohotel.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.gps.GeoCodeService;
import com.example.dangnguyenhai.gohotel.model.HotelForm;
import com.example.dangnguyenhai.gohotel.model.MarkerWrapper;
import com.example.dangnguyenhai.gohotel.utils.AddressResultReceiver;
import com.example.dangnguyenhai.gohotel.utils.ParamConstants;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;
import com.example.dangnguyenhai.gohotel.utils.TextOnDrawable;
import com.example.dangnguyenhai.gohotel.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    private TextView tvAddress;
    private String address;
    private GoogleMap mMap;
    private Context context;
    private double latitude;
    private double longitude;
    private float currentZoom = 12.0f;
    private List<MarkerWrapper> markers = new ArrayList<>();
    private Marker previousMarkerClick = null;
    private AddressResultReceiver mResultReceiver;
    private double mapLat, mapLng;
    private LatLng currentPosition;

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
        currentPosition = new LatLng(latitude, longitude);

        setUpMapIfNeeded();
        mResultReceiver = new AddressResultReceiver(new Handler(), (province, messageResult) -> {
            try {
                // when choose district move to distrct call result
                // countshow district when user choose distrct countshowdistrict =0
                if (province.equals("") && messageResult.equals(getString(R.string.service_not_available))) {
                    startGeoCodeIntentService(mapLat, mapLng);
                } else {
                   getHotelMap();
                }
            } catch (Exception e) {
            }
        });
        return rootView;
    }

    protected void startGeoCodeIntentService(double lat, double lng) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), GeoCodeService.class);
            intent.putExtra(ParamConstants.RECEIVER, mResultReceiver);
            intent.putExtra(ParamConstants.LATITUDE_DATA_EXTRA, lat);
            intent.putExtra(ParamConstants.LONGITUDE_DATA_EXTRA, lng);
            getActivity().startService(intent);
        }
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
                            Log.d("radius", "onCreateView: " + radiusMap() / 1000);

                            mMap.setOnCameraIdleListener(() -> {
                                try {

                                    if (currentZoom != mMap.getCameraPosition().zoom || currentPosition != mMap.getCameraPosition().target) {
                                        currentZoom = mMap.getCameraPosition().zoom;
                                        currentPosition = mMap.getCameraPosition().target;
                                        mapLat = currentPosition.latitude;
                                        mapLng = currentPosition.longitude;
                                        startGeoCodeIntentService(mapLat, mapLng);
                                    }
                                } catch (Exception e) {
                                }
                            });

                        }

                    }
                });


            }

        }
    }

    private void getHotelMap() {
        String lat = PreferenceUtils.getLatLocation(context);
        String longlati = PreferenceUtils.getLongLocation(context);
        GoHotelApplication.serviceApi.getHotelMap(lat, longlati, radiusMap() / 1000).enqueue(new Callback<List<HotelForm>>() {
            @Override
            public void onResponse(Call<List<HotelForm>> call, Response<List<HotelForm>> response) {
                if (response.code() == 200) {
                    List<HotelForm> hotelForms = response.body();
                    if (hotelForms != null && hotelForms.size() > 0) {
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();

                        if (markers != null) {
                            for (MarkerWrapper markerWrapper : markers) {
                                markerWrapper.getMarker().setVisible(false);
                            }
                        }
                        for (HotelForm hotelForm : hotelForms) {
                            LatLng latLng = new LatLng(Double.valueOf(hotelForm.getLatitude()), Double.valueOf(hotelForm.getLongitude()));
                            setupMarkerForMap(hotelForm, latLng);
                            builder.include(latLng);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<List<HotelForm>> call, Throwable t) {

            }
        });
    }

    private void setupMarkerForMap(final HotelForm hotelForm, final LatLng latLng) {
        try {
            String fileName = "marker_green";
            String price = getPricePromotion(hotelForm);

            if (context != null) {
                final int idResource = getResources().getIdentifier(fileName, "drawable", context.getPackageName());


                final MarkerWrapper currentMarker = isExitMarker(hotelForm.getHotelId());

                //Create new Maker
                if (currentMarker == null && mMap != null) {
                    TextOnDrawable textOnDrawable = new TextOnDrawable(Objects.requireNonNull(getActivity()), idResource, price, bitmap -> {
                        try {
                            if (bitmap != null) {

                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));

                                MarkerWrapper markerWrapper = new MarkerWrapper(hotelForm, mMap.addMarker(markerOptions));
                                markers.add(markerWrapper);
                                markerWrapper.getMarker().setVisible(true);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    textOnDrawable.execute();

                } else {
                    if (previousMarkerClick != null && mMap != null) {
                        final MarkerWrapper previousMarker = findHotelSn(previousMarkerClick);
                        if (previousMarker != null && previousMarker.getHotelForm() != null && previousMarker.getHotelForm().getHotelId() == currentMarker.getHotelForm().getHotelId()) {
                            final int identifier = getResources().getIdentifier("marker_onclick", "drawable", context.getPackageName());
                            TextOnDrawable textOnDrawable = new TextOnDrawable(context, identifier, "-1", bitmap -> {
                                try {
                                    bitmap = Bitmap.createScaledBitmap(bitmap, 60, 80, false);
                                    currentMarker.getMarker().setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                                } catch (Exception ignored) {
                                }
                            });
                            textOnDrawable.execute();
                        }
                    } else {
                        if (mMap != null) {
                            currentMarker.setHotelForm(hotelForm);
                            TextOnDrawable textOnDrawable = new TextOnDrawable(Objects.requireNonNull(getActivity()), idResource, price, bitmap -> {
                                try {
                                    if (bitmap != null) {

                                        currentMarker.getMarker().setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                                    }
                                } catch (Exception ignored) {

                                }


                            });
                            textOnDrawable.execute();
                        }

                    }
                    if (currentMarker != null && currentMarker.getMarker() != null) {
                        currentMarker.getMarker().setVisible(true);
                    }
                }

            }
        } catch (Exception ignored) {

        }


    }

    private MarkerWrapper findHotelSn(Marker marker) {
        for (int i = 0; i < markers.size(); i++) {
            if (marker.getId().equals(markers.get(i).getMarker().getId())) {
                return markers.get(i);
            }
        }
        return null;
    }

    private MarkerWrapper isExitMarker(int hotelId) {
        for (int i = 0; i < markers.size(); i++) {
            if (markers.get(i).getHotelForm().getHotelId() == hotelId) {
                return markers.get(i);
            }
        }
        return null;
    }

    private String getPricePromotion(HotelForm hotelForm) {
        String price = "0K";
        //Get Price

        if (hotelForm.getPriceRoomPerDay() > 0) {

            price = String.valueOf(Utils.formatCurrencyK(hotelForm.getPriceRoomPerDay()));

        }
        return price;
    }

    public float radiusMap() {
        try {
            if (mMap != null) {
                VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();
                LatLng farRight = visibleRegion.farRight;
                LatLng farLeft = visibleRegion.farLeft;
                LatLng nearRight = visibleRegion.nearRight;
                LatLng nearLeft = visibleRegion.nearLeft;

                float[] distanceWidth = new float[2];
                Location.distanceBetween(
                        (farRight.latitude + nearRight.latitude) / 2,
                        (farRight.longitude + nearRight.longitude) / 2,
                        (farLeft.latitude + nearLeft.latitude) / 2,
                        (farLeft.longitude + nearLeft.longitude) / 2,
                        distanceWidth
                );


                float[] distanceHeight = new float[2];
                Location.distanceBetween(
                        (farRight.latitude + nearRight.latitude) / 2,
                        (farRight.longitude + nearRight.longitude) / 2,
                        (farLeft.latitude + nearLeft.latitude) / 2,
                        (farLeft.longitude + nearLeft.longitude) / 2,
                        distanceHeight
                );

                if (distanceWidth[0] > distanceHeight[0]) {
                    return distanceWidth[0];
                } else {
                    return distanceHeight[0];
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

}
