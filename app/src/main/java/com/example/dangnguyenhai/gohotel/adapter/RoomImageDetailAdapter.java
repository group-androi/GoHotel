package com.example.dangnguyenhai.gohotel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dangnguyenhai.gohotel.R;

import java.util.List;

public class RoomImageDetailAdapter extends RecyclerView.Adapter<RoomImageDetailAdapter.ViewHolder> {

    private Context context;
    //private List<HotelImageForm> hotelImageForms;

    public RoomImageDetailAdapter(Context context, List<String> hotelImageForms) {
        this.context = context;
        //this.hotelImageForms = hotelImageForms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hotel_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        //final String url = UrlParams.MAIN_URL + "/hotelapi/hotel/download/downloadHotelImage?hotelImageSn=" + data.get(position).getSn() + "&fileName=" + data.get(position).getCustomizeName();
//        String url = UrlParams.getInstance().url + "/hotelapi/hotel/download/downloadHotelImageViaKey?imageKey=" + hotelImageForms.get(position).getImageKey();
//
//        /*
//        / Set Image Normal
//        */
//
//        Glide.with(context)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(holder.imgViewNormal);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewNormal;

        public ViewHolder(View itemView) {
            super(itemView);
            imgViewNormal = itemView.findViewById(R.id.imgItem);
        }
    }
}
