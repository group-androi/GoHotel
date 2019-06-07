package com.example.dangnguyenhai.gohotel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.activity.BookingDetail;
import com.example.dangnguyenhai.gohotel.activity.BookingList;
import com.example.dangnguyenhai.gohotel.model.api.BookingUserForm;
import com.example.dangnguyenhai.gohotel.utils.AppTimeUtils;
import com.example.dangnguyenhai.gohotel.utils.Utils;

import java.util.List;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.Viewholder> {
    private Context context;
    private List<BookingUserForm> bookingUserForms;


    public MyBookingAdapter(Context context, List<BookingUserForm> bookingUserForms) {
        this.context = context;
        this.bookingUserForms = bookingUserForms;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_booking_adapter, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        BookingUserForm bookingForm = bookingUserForms.get(position);
        if (bookingForm != null) {
            holder.tvHotelName.setText(bookingForm.getNameHotel());
            holder.tvDate.setText(AppTimeUtils.changeDateShowClient(bookingForm.getTimeBook()));
            holder.tvRoomName.setText(bookingForm.getNameRoom());
            holder.tvFee.setText(String.format("%s VND", Utils.formatCurrency(bookingForm.getPrice())));
            holder.tvBookingType.setText("Theo ngày");
            holder.tvBookingStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            if (bookingForm.getStatus() == 0) {
                holder.tvBookingStatus.setText("Đã đặt");
            } else if (bookingForm.getStatus() == -1)
                holder.tvBookingStatus.setText("Đã hủy");
            else if (bookingForm.getStatus() == 1)
                holder.tvBookingStatus.setText("Đã nhận phòng");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, BookingDetail.class);
                    intent.putExtra("BookingID",bookingForm.getIdBook());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookingUserForms.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView tvHotelName, tvDate, tvRoomName, tvFee, tvBookingType, tvBookingStatus;

        Viewholder(View itemView) {
            super(itemView);
            tvHotelName = itemView.findViewById(R.id.tvHotelName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvFee = itemView.findViewById(R.id.tvFee);
            tvBookingType = itemView.findViewById(R.id.tvBookingType);
            tvBookingStatus = itemView.findViewById(R.id.tvBookingStatus);
        }
    }
}
