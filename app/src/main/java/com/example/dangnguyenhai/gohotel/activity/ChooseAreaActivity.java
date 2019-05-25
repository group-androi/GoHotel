package com.example.dangnguyenhai.gohotel.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.adapter.ProvinceAdapter;
import com.example.dangnguyenhai.gohotel.model.api.CityForm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseAreaActivity extends AppCompatActivity {

    RecyclerView lvProvinces, lvHotelArea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_setting_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }

        addview();
        getListCity();

    }

    private void getListCity() {
        GoHotelApplication.serviceApi.getCity().enqueue(new Callback<List<CityForm>>() {
            @Override
            public void onResponse(Call<List<CityForm>> call, Response<List<CityForm>> response) {
                if (response.code() == 200) {
                    List<CityForm> cityForms = response.body();
                    if (cityForms != null && cityForms.size() > 0) {
                        handleListCity(cityForms);
                    }
                } else {
                    Toast.makeText(ChooseAreaActivity.this, "Không thể lấy danh sách thành phố", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CityForm>> call, Throwable t) {

            }
        });
    }

    private void handleListCity(List<CityForm> cityForms) {
        lvProvinces.setAdapter(new ProvinceAdapter(this, cityForms));
    }

    private void getDistrict(int provine){

    }

    private void addview() {
        lvProvinces = findViewById(R.id.lvProvinces);
        lvProvinces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lvProvinces.setHasFixedSize(true);

        lvHotelArea = findViewById(R.id.lvHotelArea);
        lvHotelArea.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lvHotelArea.setHasFixedSize(true);
    }
}
