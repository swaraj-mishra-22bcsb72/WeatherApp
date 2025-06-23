package com.example.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {
    var uiState by mutableStateOf<UiState>(UiState.Idle)
        private set

    fun loadWeatherByCity(city: String, apiKey: String) = viewModelScope.launch {
        uiState = UiState.Loading
        uiState = try {
            val response = repo.fetchCurrentWeatherByCity(city, apiKey)
            UiState.Success(response)
        } catch (e: Exception) {
            UiState.Error(e.message ?: "Something went wrong")
        }
    }
}

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val data: WeatherResponse) : UiState()
    data class Error(val message: String) : UiState()
}