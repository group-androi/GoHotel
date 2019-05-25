package com.example.dangnguyenhai.gohotel.model;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelForm {
    @SerializedName("id_hotel")
    @Expose
    private String idHotel;
    @SerializedName("name_hotel")
    @Expose
    private String nameHotel;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("price_room_per_day")
    @Expose
    private int priceRoomPerDay;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("name_image")
    @Expose
    private String nameImage;
    @SerializedName("hotel_id")
    @Expose
    private int hotelId;
    @SerializedName("room_id")
    @Expose
    private Object roomId;
    @SerializedName("distance")
    @Expose
    private String distance;

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getNameHotel() {
        if (nameHotel == null) return "";
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getPriceRoomPerDay() {
        return priceRoomPerDay;
    }

    public void setPriceRoomPerDay(int priceRoomPerDay) {
        this.priceRoomPerDay = priceRoomPerDay;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNameImage() {
        if (nameImage == null) return "";
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Object getRoomId() {
        return roomId;
    }

    public void setRoomId(Object roomId) {
        this.roomId = roomId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
