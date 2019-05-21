package com.example.dangnguyenhai.gohotel.api;

import com.example.dangnguyenhai.gohotel.model.api.ResponseUserCreate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceApi {


    @POST("/API_GoHotel/user/create.php")
    @FormUrlEncoded
    Call<ResponseUserCreate> updateUserReview(@Field("phone") String phone, @Field("password") String password
            , @Field("birthday") String birthday, @Field("gender") String gender, @Field("device_id") String device_id
            , @Header("token") String token);
}
