package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.network.WeatherApi

class WeatherRepository(private val api: WeatherApi) {
    suspend fun fetchCurrentWeatherByCity(city: String, apiKey: String): WeatherResponse {
        return api.getCurrentWeatherByCity(city, apiKey)
    }
}
