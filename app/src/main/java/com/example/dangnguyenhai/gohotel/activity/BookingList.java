package com.example.dangnguyenhai.gohotel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.adapter.MyBookingAdapter;
import com.example.dangnguyenhai.gohotel.model.api.BookingUserForm;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingList extends AppCompatActivity {
    RecyclerView rcvMyBooking;
    private List<BookingUserForm> bookingUserForms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_list_activity);
        rcvMyBooking = findViewById(R.id.rcvMyBooking);
        rcvMyBooking.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvMyBooking.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyBooking();
    }

    private void getMyBooking() {
        GoHotelApplication.serviceApi.getMyBooking(PreferenceUtils.getToken(this)).enqueue(new Callback<List<BookingUserForm>>() {
            @Override
            public void onResponse(Call<List<BookingUserForm>> call, Response<List<BookingUserForm>> response) {
                if (response.code() == 200) {
                    bookingUserForms = response.body();
                    if (bookingUserForms != null && bookingUserForms.size() > 0) {
                        rcvMyBooking.setAdapter(new MyBookingAdapter(BookingList.this, bookingUserForms));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BookingUserForm>> call, Throwable t) {

            }
        });
    }
}
