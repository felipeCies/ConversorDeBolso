package com.example.conversordebolso.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.conversordebolso.api.ExchangeResponse;

public interface ExchangeRateApi {
    @GET("latest")
    Call<ExchangeResponse> getRates(
            @Query("base") String base,
            @Query("symbols") String symbols
    );
}
