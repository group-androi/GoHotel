package com.example.dangnguyenhai.gohotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.model.api.UserInfo;
import com.example.dangnguyenhai.gohotel.utils.ParamConstants;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    TextView btnLogin;
    TextView tvSignup;
    ImageView btnClose;
    private TextInputLayout inputLayoutPhone, inputLayoutPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        inputLayoutPassword = findViewById(R.id.inputLayoutPassword);
        inputLayoutPhone = findViewById(R.id.inputLayoutPhone);
        btnClose = findViewById(R.id.btnClose);
        edtPhone = findViewById(R.id.edtPhone);
        edtPhone.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                String phone = edtPhone.getText().toString();
                if (phone.isEmpty()) {
                    inputLayoutPhone.setError("Số điện thoại không được để trống");
                    inputLayoutPhone.setErrorEnabled(true);
                } else {
                    inputLayoutPhone.setError("");
                    inputLayoutPhone.setErrorEnabled(false);
                }
            }
        });
        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String pass = edtPassword.getText().toString();
                    if (pass.isEmpty()) {
                        inputLayoutPassword.setError("Mật khẩu không được để trống");
                        inputLayoutPassword.setErrorEnabled(true);
                    } else {
                        inputLayoutPassword.setError("");
                        inputLayoutPassword.setErrorEnabled(false);
                    }
                }
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
        tvSignup = findViewById(R.id.tvSignup);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, ParamConstants.REQUEST_SIGNUP_LOGIN);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void handleLogin() {
        edtPhone.requestFocus();
        edtPassword.requestFocus();
        if (inputLayoutPassword.isErrorEnabled() || inputLayoutPhone.isErrorEnabled()) {
            return;
        }
        String phone = edtPhone.getText().toString();
        String pass = edtPassword.getText().toString();

        GoHotelApplication.serviceApi.login(phone,pass).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() == 200) {
                    UserInfo userInfo = response.body();
                    if (userInfo.getResult() == 1) {
                        String json=new Gson().toJson(userInfo);
                        PreferenceUtils.setToken(LoginActivity.this,userInfo.getToken());
                        PreferenceUtils.setUserInfo(LoginActivity.this, json);
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });

    }
}
