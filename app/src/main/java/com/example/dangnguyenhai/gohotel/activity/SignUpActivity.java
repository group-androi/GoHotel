package com.example.dangnguyenhai.gohotel.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
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
import com.example.dangnguyenhai.gohotel.utils.UtilityValidate;
import com.example.dangnguyenhai.gohotel.widgets.dialog.DateTimeDialogUtils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText edtPhone, edtEmail, edtPassword, edtConfirmPassword, edtBirthday;
    RadioButton rdNam, rdNu;
    Button btnRegister;
    TextInputLayout inputLayoutMail, inputLayoutPhone, inputLayoutConfirm, inputLayoutPassword;

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
        inputLayoutPassword = findViewById(R.id.inputLayoutPassword);
        inputLayoutConfirm = findViewById(R.id.inputLayoutConfirm);
        inputLayoutPhone = findViewById(R.id.inputLayoutPhone);
        inputLayoutMail = findViewById(R.id.inputLayoutMail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPhone.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                String phone=edtPhone.getText().toString();
                if(phone.isEmpty()){
                    inputLayoutPhone.setError("Số điện thoại không được để trống");
                    inputLayoutPhone.setErrorEnabled(true);
                }else {
                checkPhoneAlready(new CheckPhoneListener() {
                    @Override
                    public void onAlreadyExists() {
                        inputLayoutPhone.setError("Số điện thoại đã tồn tại");
                        inputLayoutPhone.setErrorEnabled(true);
                    }

                    @Override
                    public void onNotAlreadyExists() {
                        inputLayoutPhone.setError("");
                        inputLayoutPhone.setErrorEnabled(false);
                    }

                    @Override
                    public void onError() {

                    }
                });
            }}
        });
        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                if (!UtilityValidate.isEmailValid(edtEmail.getText().toString())) {
                    inputLayoutMail.setError("Định dạng email không hợp lệ");
                    inputLayoutMail.setErrorEnabled(true);
                } else {
                    inputLayoutMail.setError("");
                    inputLayoutMail.setErrorEnabled(false);
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
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String pass = edtPassword.getText().toString();
                    if (!pass.equals(edtConfirmPassword.getText().toString())) {
                        inputLayoutConfirm.setError("Không trùng với mật khẩu");
                        inputLayoutConfirm.setErrorEnabled(true);
                    } else {
                        inputLayoutConfirm.setError("");
                        inputLayoutConfirm.setErrorEnabled(false);
                    }
                }
            }
        });
        edtBirthday = findViewById(R.id.edtBirthday);
        edtBirthday.setOnClickListener(view -> {
            Calendar minYear = Calendar.getInstance();
            minYear.set(Calendar.YEAR, 1950);

            Calendar maxYear = Calendar.getInstance();
            maxYear.set(Calendar.YEAR, maxYear.get(Calendar.YEAR) - 18);
            DateTimeDialogUtils.showDatePickerDialog(SignUpActivity.this, edtBirthday, minYear, maxYear);
        });
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> registerUser(SignUpActivity.this));
    }

    private void checkPhoneAlready(CheckPhoneListener checkPhoneListener) {
        String phone = edtPhone.getText().toString();
        GoHotelApplication.serviceApi.checkEqualPhone(phone, "").enqueue(new Callback<ResponseUserCreate>() {
            @Override
            public void onResponse(Call<ResponseUserCreate> call, Response<ResponseUserCreate> response) {
                if (response.code() == 200) {
                    ResponseUserCreate responseUserCreate = response.body();
                    if (responseUserCreate.getResult() == 1) {
                        checkPhoneListener.onNotAlreadyExists();
                        PreferenceUtils.setToken(SignUpActivity.this, responseUserCreate.getToken());
                    } else {
                        checkPhoneListener.onAlreadyExists();
                        Toast.makeText(SignUpActivity.this, responseUserCreate.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    checkPhoneListener.onError();
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại vui lòng thử lại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserCreate> call, Throwable t) {

            }
        });
    }

    private void registerUser(Context context) {
        edtPhone.requestFocus();
        edtConfirmPassword.requestFocus();
        edtPassword.requestFocus();
        edtEmail.requestFocus();
        if (inputLayoutMail.isErrorEnabled() || inputLayoutPhone.isErrorEnabled() || inputLayoutConfirm.isErrorEnabled()) {
            return;
        }
        String pass = edtPassword.getText().toString();
        if (!pass.equals(edtConfirmPassword.getText().toString())) {
            inputLayoutConfirm.setError("Không trùng với mật khẩu");
            inputLayoutConfirm.setErrorEnabled(true);
        } else {
            inputLayoutConfirm.setError("");
            inputLayoutConfirm.setErrorEnabled(false);
        }

        String phone = edtPhone.getText().toString();

        String email = edtEmail.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String gender = "nam";
        if (!rdNam.isChecked())
            gender = "nữ";
        GoHotelApplication.serviceApi.createUser(phone, pass, birthday,email, gender, GoHotelApplication.DEVICE_ID, "").enqueue(new Callback<ResponseUserCreate>() {
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

    private interface CheckPhoneListener {
        void onAlreadyExists();

        void onNotAlreadyExists();

        void onError();
    }
}
