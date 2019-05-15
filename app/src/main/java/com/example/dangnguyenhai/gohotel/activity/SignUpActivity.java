package com.example.dangnguyenhai.gohotel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.model.api.ResponseUserCreate;
import com.example.dangnguyenhai.gohotel.model.api.SignUpForm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        SignUpForm signUpForm=new SignUpForm();
        signUpForm.setPhone("01228204451");
        signUpForm.setBirthday("1996-09-06");
        signUpForm.setPassword("7avodoilc");
        signUpForm.setGender("name");
        signUpForm.setDevice_id("72e116071e342af0c3e565c9b516fbde");
        GoHotelApplication.serviceApi.updateUserReview(signUpForm).enqueue(new Callback<ResponseUserCreate>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUserCreate> call, @NonNull Response<ResponseUserCreate> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUserCreate> call, @NonNull Throwable t) {

            }
        });

    }
}
