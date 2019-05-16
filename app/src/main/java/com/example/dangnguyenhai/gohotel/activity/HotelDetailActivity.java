package com.example.dangnguyenhai.gohotel.activity;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.adapter.RoomImageDetailAdapter;
import com.example.dangnguyenhai.gohotel.adapter.RoomTypeAdapter;

public class HotelDetailActivity extends AppCompatActivity {
    private RecyclerView rcvImgRoom, rcvRoomAdapter;
    private int width;
    private int height;
    private Resources resources;
    private RoomImageDetailAdapter adapter;
    private RoomTypeAdapter roomTypeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_detail_activity);
        resources = getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        setViews();
    }

    private void setViews() {
        rcvImgRoom = findViewById(R.id.rcvImgRoom);
        rcvImgRoom.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvImgRoom.setHasFixedSize(true);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 2);
        rcvImgRoom.setLayoutParams(layoutParams);
        adapter = new RoomImageDetailAdapter(this, null);
        rcvImgRoom.setAdapter(adapter);

        rcvRoomAdapter = findViewById(R.id.rcvRoomAdapter);
        rcvRoomAdapter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvImgRoom.setHasFixedSize(true);
        roomTypeAdapter = new RoomTypeAdapter(this);
        rcvRoomAdapter.setAdapter(roomTypeAdapter);

    }


}
