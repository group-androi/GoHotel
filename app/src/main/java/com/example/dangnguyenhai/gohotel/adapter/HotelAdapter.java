package com.example.dangnguyenhai.gohotel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dangnguyenhai.gohotel.R;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context context;

    public HotelAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hotel_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(R.drawable.hotel).into(holder.imgHotel);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHotel;

        ViewHolder(View itemView) {
            super(itemView);
            imgHotel = itemView.findViewById(R.id.imgHotel);
        }
    }
}
