package com.example.dangnguyenhai.gohotel.activity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.dangnguyenhai.gohotel.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ReservationActivity extends AppCompatActivity {

    ImageView imgHotel, imgRoom;
    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;


        imgHotel = findViewById(R.id.imgHotel);
        imgRoom = findViewById(R.id.imgRoom);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 2);
        imgHotel.setLayoutParams(layoutParams);

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_big)
                .error(R.drawable.loading_big);
        Glide.with(this)
                .load(R.drawable.bananahotel)
                .apply(requestOptions)
                .transition(withCrossFade())
                .into(imgHotel);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading_big)
                .diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop();

         Glide.with(this)
                .load(R.drawable.bananahotel).apply(options).into(imgRoom);
    }
}
