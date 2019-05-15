package com.example.dangnguyenhai.gohotel.api;

import com.example.dangnguyenhai.gohotel.model.api.ResponseUserCreate;
import com.example.dangnguyenhai.gohotel.model.api.SignUpForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceApi {

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @POST("/user/create.php")
    Call<ResponseUserCreate> updateUserReview(@Body SignUpForm signUpForm);
}
