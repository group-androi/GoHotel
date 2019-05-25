package com.example.dangnguyenhai.gohotel.api;

import com.example.dangnguyenhai.gohotel.model.HotelForm;
import com.example.dangnguyenhai.gohotel.model.api.CityForm;
import com.example.dangnguyenhai.gohotel.model.api.ResponseUserCreate;
import com.example.dangnguyenhai.gohotel.model.api.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceApi {


    @POST("/API_GoHotel/user/create.php")
    @FormUrlEncoded
    Call<ResponseUserCreate> updateUserReview(@Field("phone") String phone, @Field("password") String password
            , @Field("birthday") String birthday, @Field("gender") String gender, @Field("device_id") String device_id
            , @Header("token") String token);

    @POST("/API_GoHotel/user/checkEqualPhone.php")
    @FormUrlEncoded
    Call<ResponseUserCreate> checkEqualPhone(@Field("phone") String phone, @Header("token") String token);

    @POST("/API_GoHotel/user/login.php")
    @FormUrlEncoded
    Call<UserInfo> login(@Field("phone") String phone, @Field("password") String pasword);



    @GET("/API_GoHotel/city/get.php")
    Call<List<CityForm>> getCity();

    @GET("/API_GoHotel/district/accordingToCityId.php")
    Call<List<CityForm>> accordingToCityId();

    @POST("/API_GoHotel/hotel/getHotelHome.php")
    @FormUrlEncoded
    Call<List<HotelForm>> getHotelHome(@Field("latitude") String latitude, @Field("longitude") String longitude, @Field("limitfrom") int limitfrom, @Field("limitcount") int limitcount);

    @POST("/API_GoHotel/hotel/getHotelMap.php")
    @FormUrlEncoded
    Call<List<HotelForm>> getHotelMap(@Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") double radius);

}
