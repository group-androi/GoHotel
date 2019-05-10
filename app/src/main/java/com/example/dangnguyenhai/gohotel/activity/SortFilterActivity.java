package com.example.dangnguyenhai.gohotel.activity;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.utils.Utils;
import com.example.dangnguyenhai.gohotel.widgets.OnRangeChangedListener;
import com.example.dangnguyenhai.gohotel.widgets.RangeSeekBar;

public class SortFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private Resources resources;
    private ImageView btnBack;
    private TextView tvDistance, tvPriceAsc, tvPriceDesc, tvRanting;
    private ImageView imgDistance, imgPriceAsc, imgPriceDesc, imgRating;
    private LinearLayout btnDistance, btnPriceAsc, btnPriceDesc, btnRating;
    private RangeSeekBar sbRange;
    private TextView tvPriceTo;
    private TextView tvPriceFrom,btnClear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_filter_activity);
//        resources = getResources();
//        btnBack = findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(this);
//        tvDistance = findViewById(R.id.tvDistance);
//        tvPriceAsc = findViewById(R.id.tvPriceAsc);
//        tvPriceDesc = findViewById(R.id.tvPriceDesc);
//        tvRanting = findViewById(R.id.tvRanting);
//        imgDistance = findViewById(R.id.imgDistance);
//        imgPriceAsc = findViewById(R.id.imgPriceAsc);
//        imgPriceDesc = findViewById(R.id.imgPriceDesc);
//        imgRating = findViewById(R.id.imgRating);
//
//        btnDistance = findViewById(R.id.btnDistance);
//        btnDistance.setOnClickListener(this);
//        btnPriceAsc = findViewById(R.id.btnPriceAsc);
//        btnPriceAsc.setOnClickListener(this);
//        btnPriceDesc = findViewById(R.id.btnPriceDesc);
//        btnPriceDesc.setOnClickListener(this);
//        btnRating = findViewById(R.id.btnRating);
//        btnRating.setOnClickListener(this);
//        sbRange = findViewById(R.id.sbRange);
//        sbRange.setRange(0, 30);
//        sbRange.setValue(0, 30);
//        sbRange.setOnRangeChangedListener(new OnRangeChangedListener() {
//            @Override
//            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
//                if (isFromUser) {
//                    tvPriceFrom.setText(Utils.formatCurrency(((int) leftValue) * 100000));
//                    tvPriceTo.setText(Utils.formatCurrency((int) rightValue * 100000));
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
//            }
//        });
//        tvPriceFrom = findViewById(R.id.tvPriceFrom);
//        tvPriceTo = findViewById(R.id.tvPriceTo);
//        btnClear=findViewById(R.id.btnClear);
//        btnClear.setOnClickListener(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                getWindow().setStatusBarColor(resources.getColor(R.color.colorWhite));
//            }
//        }
    }

    private void chooseSort(int i) {
//        switch (i) {
//            case 0:
//                tvDistance.setTextColor(resources.getColor(R.color.colorPrimary));
//                tvPriceAsc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvPriceDesc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvRanting.setTextColor(resources.getColor(R.color.colorDefault));
//
//                imgDistance.setImageResource(R.drawable.verified_selected);
//                imgPriceAsc.setImageResource(R.drawable.verified);
//                imgPriceDesc.setImageResource(R.drawable.verified);
//                imgRating.setImageResource(R.drawable.verified);
//
//                break;
//            case 1:
//                tvPriceAsc.setTextColor(resources.getColor(R.color.colorPrimary));
//                tvDistance.setTextColor(resources.getColor(R.color.colorDefault));
//                tvPriceDesc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvRanting.setTextColor(resources.getColor(R.color.colorDefault));
//
//                imgPriceAsc.setImageResource(R.drawable.verified_selected);
//                imgDistance.setImageResource(R.drawable.verified);
//                imgPriceDesc.setImageResource(R.drawable.verified);
//                imgRating.setImageResource(R.drawable.verified);
//
//                break;
//            case 2:
//                tvPriceDesc.setTextColor(resources.getColor(R.color.colorPrimary));
//                tvPriceAsc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvDistance.setTextColor(resources.getColor(R.color.colorDefault));
//                tvRanting.setTextColor(resources.getColor(R.color.colorDefault));
//
//                imgPriceDesc.setImageResource(R.drawable.verified_selected);
//                imgPriceAsc.setImageResource(R.drawable.verified);
//                imgDistance.setImageResource(R.drawable.verified);
//                imgRating.setImageResource(R.drawable.verified);
//
//                break;
//            case 3:
//                tvRanting.setTextColor(resources.getColor(R.color.colorPrimary));
//                tvPriceAsc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvPriceDesc.setTextColor(resources.getColor(R.color.colorDefault));
//                tvDistance.setTextColor(resources.getColor(R.color.colorDefault));
//
//                imgRating.setImageResource(R.drawable.verified_selected);
//                imgPriceAsc.setImageResource(R.drawable.verified);
//                imgPriceDesc.setImageResource(R.drawable.verified);
//                imgDistance.setImageResource(R.drawable.verified);
//
//                break;
//        }
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnBack:
//                onBackPressed();
//                break;
//            case R.id.btnDistance:
//                chooseSort(0);
//                break;
//            case R.id.btnPriceAsc:
//                chooseSort(1);
//                break;
//            case R.id.btnPriceDesc:
//                chooseSort(2);
//                break;
//            case R.id.btnRating:
//                chooseSort(3);
//                break;
//            case R.id.btnClear:
//                chooseSort(0);
//                sbRange.setValue(0, 30);
//                tvPriceFrom.setText(Utils.formatCurrency(0));
//                tvPriceTo.setText(Utils.formatCurrency(30 * 100000));
//                break;
//        }
    }
}
