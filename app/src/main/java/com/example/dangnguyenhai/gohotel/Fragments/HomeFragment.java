package com.example.dangnguyenhai.gohotel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.activity.SortFilterActivity;
import com.example.dangnguyenhai.gohotel.adapter.HotelAdapter;
import com.example.dangnguyenhai.gohotel.model.HotelForm;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String address;
    private RecyclerView rcvHotel;
    private ImageView imgSort;
    private Context context;
    private int offset = 0;
    private HotelAdapter hotelAdapter;
    private List<HotelForm> hotelForms;

    public static HomeFragment newInstance(String address) {
        HomeFragment myFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putString("address", address);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        TextView tvAddress = rootView.findViewById(R.id.tvAddress);
        imgSort = rootView.findViewById(R.id.imgSort);
        imgSort.setOnClickListener(this);
        rcvHotel = rootView.findViewById(R.id.rcvHotel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvHotel.setLayoutManager(linearLayoutManager);
        rcvHotel.setHasFixedSize(true);

        tvAddress.setText(address);
        getHotelHome();
        return rootView;

    }

    private void getHotelHome() {
        String lat = PreferenceUtils.getLatLocation(context);
        String longtidue = PreferenceUtils.getLongLocation(context);

        GoHotelApplication.serviceApi.getHotelHome(lat, longtidue, offset, GoHotelApplication.limit).enqueue(new Callback<List<HotelForm>>() {
            @Override
            public void onResponse(Call<List<HotelForm>> call, Response<List<HotelForm>> response) {
                if (response.code() == 200) {
                    List<HotelForm> hotelForms = response.body();
                    if (hotelForms != null && hotelForms.size() > 0) {
                        handleListHotel(hotelForms);
                    }
                } else {
                    Toast.makeText(context, "Không thể lấy danh sách khách sạn", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<HotelForm>> call, Throwable t) {

            }
        });
    }

    private void handleListHotel(List<HotelForm> hotelForms) {
        if(this.hotelForms==null)
            this.hotelForms=new ArrayList<>();
        this.hotelForms.addAll(hotelForms);
        if(hotelAdapter!=null){
            hotelAdapter.notifyItemRangeInserted(offset, hotelForms.size());
        }else {
            hotelAdapter = new HotelAdapter(context,this.hotelForms);
            rcvHotel.setAdapter(hotelAdapter);
        }
        offset = this.hotelForms.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSort:
                gotoSortFilter();
                break;
        }
    }

    private void gotoSortFilter() {
        Intent intent = new Intent(getContext(), SortFilterActivity.class);
        startActivity(intent);
    }
}
