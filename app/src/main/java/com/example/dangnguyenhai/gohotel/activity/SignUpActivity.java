package com.example.dangnguyenhai.gohotel.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.model.api.ResponseUserCreate;
import com.example.dangnguyenhai.gohotel.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpActivity extends AppCompatActivity {
    EditText edtPhone, edtEmail, edtPassword, edtConfirmPassword, edtBirthday;
    RadioButton rdNam, rdNu;
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        addViews();

//        SignUpForm signUpForm = new SignUpForm();
//        signUpForm.setPhone("0778204451");
//        signUpForm.setBirthday("1996-09-06");
//        signUpForm.setPassword("dang");
//        signUpForm.setGender("nam");
//        signUpForm.setDevice_id(GoHotelApplication.DEVICE_ID);


    }

    private void addViews() {
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtBirthday = findViewById(R.id.edtBirthday);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(SignUpActivity.this);
            }
        });
    }

    private void registerUser(Context context) {
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String gender = "nam";
        if (!rdNam.isChecked())
            gender = "nữ";
        GoHotelApplication.serviceApi.updateUserReview(phone, pass, birthday, gender, GoHotelApplication.DEVICE_ID, "").enqueue(new Callback<ResponseUserCreate>() {
            @Override
            public void onResponse(Call<ResponseUserCreate> call, retrofit2.Response<ResponseUserCreate> response) {
                if (response.code() == 200) {
                    ResponseUserCreate responseUserCreate = response.body();
                    if (responseUserCreate.getResult() == 1) {
                        PreferenceUtils.setToken(context, responseUserCreate.getToken());
                    } else {
                        Toast.makeText(context, responseUserCreate.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Đăng ký thất bại vui lòng thử lại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserCreate> call, Throwable t) {

            }
        });

    }
}
