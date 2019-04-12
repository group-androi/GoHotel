package com.example.dangnguyenhai.gohotel;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.Enums.TypeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Resources resources;
    private RelativeLayout rltHome, rltSearch, rltMap, rltAccount;
    private ImageView imgHome, imgSearch, imgMap, imgAccount;
    private TextView tvHome, tvSearch, tvMap, tvAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        resources = getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(resources.getColor(R.color.colorWhite));
            }
        }

        addViews();
    }

    private void addViews() {
        rltHome = findViewById(R.id.rltHome);
        rltSearch = findViewById(R.id.rltSearch);
        rltMap = findViewById(R.id.rltMap);
        rltAccount = findViewById(R.id.rltAccount);

        imgHome = findViewById(R.id.imgHome);
        imgSearch = findViewById(R.id.imgSearch);
        imgMap = findViewById(R.id.imgMap);
        imgAccount = findViewById(R.id.imgAccount);

        tvHome = findViewById(R.id.tvHome);
        tvSearch = findViewById(R.id.tvSearch);
        tvMap = findViewById(R.id.tvMap);
        tvAccount = findViewById(R.id.tvAccount);

        addEvents();
    }

    private void addEvents() {
        rltHome.setOnClickListener(this);
        rltSearch.setOnClickListener(this);
        rltMap.setOnClickListener(this);
        rltAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rltHome:
                changeTab(TypeFragment.HOME.getType());
                break;
            case R.id.rltSearch:
                changeTab(TypeFragment.SEARCH.getType());
                break;
            case R.id.rltMap:
                changeTab(TypeFragment.MAP.getType());
                break;
            case R.id.rltAccount:
                changeTab(TypeFragment.ACCOUNT.getType());
                break;
        }
    }

    private void changeTab(int potition) {

        if (potition == TypeFragment.HOME.getType()) {
            imgHome.setImageResource(R.drawable.home_selected);
            imgSearch.setImageResource(R.drawable.search);
            imgMap.setImageResource(R.drawable.map);
            imgAccount.setImageResource(R.drawable.user);

            tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvSearch.setTextColor(getResources().getColor(R.color.colorDefault));
            tvMap.setTextColor(getResources().getColor(R.color.colorDefault));
            tvAccount.setTextColor(getResources().getColor(R.color.colorDefault));

        } else if (potition == TypeFragment.SEARCH.getType()) {
            imgHome.setImageResource(R.drawable.home);
            imgSearch.setImageResource(R.drawable.search_selected);
            imgMap.setImageResource(R.drawable.map);
            imgAccount.setImageResource(R.drawable.user);

            tvHome.setTextColor(getResources().getColor(R.color.colorDefault));
            tvSearch.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvMap.setTextColor(getResources().getColor(R.color.colorDefault));
            tvAccount.setTextColor(getResources().getColor(R.color.colorDefault));
        } else if (potition == TypeFragment.MAP.getType()) {
            imgHome.setImageResource(R.drawable.home);
            imgSearch.setImageResource(R.drawable.search);
            imgMap.setImageResource(R.drawable.map_selected);
            imgAccount.setImageResource(R.drawable.user);

            tvHome.setTextColor(getResources().getColor(R.color.colorDefault));
            tvSearch.setTextColor(getResources().getColor(R.color.colorDefault));
            tvMap.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvAccount.setTextColor(getResources().getColor(R.color.colorDefault));
        } else {
            imgHome.setImageResource(R.drawable.home);
            imgSearch.setImageResource(R.drawable.search);
            imgMap.setImageResource(R.drawable.map);
            imgAccount.setImageResource(R.drawable.user_selected);

            tvHome.setTextColor(getResources().getColor(R.color.colorDefault));
            tvSearch.setTextColor(getResources().getColor(R.color.colorDefault));
            tvMap.setTextColor(getResources().getColor(R.color.colorDefault));
            tvAccount.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
