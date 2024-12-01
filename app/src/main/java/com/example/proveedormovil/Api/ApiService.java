package com.example.proveedormovil.Api;

import com.example.proveedormovil.models.LoginRequest;
import com.example.proveedormovil.models.LoginResponse;
import com.example.proveedormovil.models.ProveedoresResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @GET("proveedores")
    Call<ProveedoresResponse> getProveedores(@Header("Authorization") String token);
}