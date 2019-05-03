package com.example.dangnguyenhai.gohotel.Fragments;

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

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.activity.SortFilterActivity;
import com.example.dangnguyenhai.gohotel.adapter.HotelAdapter;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String address;
    private RecyclerView rcvHotel;
    private ImageView imgSort;

    public static HomeFragment newInstance(String address) {
        HomeFragment myFragment = new HomeFragment();

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
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        TextView tvAddress = rootView.findViewById(R.id.tvAddress);
        imgSort = rootView.findViewById(R.id.imgSort);
        imgSort.setOnClickListener(this);
        rcvHotel = rootView.findViewById(R.id.rcvHotel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvHotel.setLayoutManager(linearLayoutManager);
        rcvHotel.setHasFixedSize(true);
        rcvHotel.setAdapter(new HotelAdapter(getContext()));
        tvAddress.setText(address);
        return rootView;

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
