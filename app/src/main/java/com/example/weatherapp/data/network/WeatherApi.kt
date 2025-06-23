package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "6ef044554e00015e0c85789011fbd48e",
        @Query("units") units: String = "metric"
    ): WeatherResponse
}