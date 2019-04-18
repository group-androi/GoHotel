package com.example.dangnguyenhai.gohotel;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.adapter.SearchAdapter;
import com.example.dangnguyenhai.gohotel.model.SearchForm;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rcvSearchHotel;
    private TextView btnBack;
    private List<SearchForm> searchFormList;
    private Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        resources = getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(resources.getColor(R.color.colorWhite));
            }
        }
        rcvSearchHotel = findViewById(R.id.rcvSearchHotel);
        rcvSearchHotel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvSearchHotel.setHasFixedSize(true);
        rcvSearchHotel.setAdapter(new SearchAdapter(this, searchFormList));

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }
}
