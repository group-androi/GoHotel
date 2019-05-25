package com.example.dangnguyenhai.gohotel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.model.HotelForm;
import com.example.dangnguyenhai.gohotel.utils.Utils;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context context;
    private List<HotelForm> hotelForms;

    public HotelAdapter(Context context, List<HotelForm> hotelForms) {
        this.context = context;
        this.hotelForms = hotelForms;
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
        HotelForm hotelForm=hotelForms.get(position);
        holder.tvHotelName.setText(hotelForm.getNameHotel());
        holder.tvAddress.setText(hotelForm.getAddress());
        holder.tvPriceDiscount.setText(Utils.formatCurrency(hotelForm.getPriceRoomPerDay())+" đồng");
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_big)
                .error(R.drawable.loading_big);
        Glide.with(context).load(hotelForm.getNameImage()).apply(requestOptions).into(holder.imgHotel);
    }

    @Override
    public int getItemCount() {
        return hotelForms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHotel;
        TextView tvHotelName,tvAddress,tvPrice,tvPriceDiscount;

        ViewHolder(View itemView) {
            super(itemView);
            imgHotel = itemView.findViewById(R.id.imgHotel);
            tvHotelName = itemView.findViewById(R.id.tvHotelName);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvPriceDiscount=itemView.findViewById(R.id.tvPriceDiscount);
        }
    }
}
