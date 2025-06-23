package com.example.weatherapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.WeatherCard
import com.example.weatherapp.viewmodel.UiState
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val apiKey = "6ef044554e00015e0c85789011fbd48e"
    var city by remember { mutableStateOf("") }

    val citySuggestions = listOf("New York", "London", "Berlin", "Paris", "Tokyo", "Bhubaneswar", "Rourkela")
    val filteredCities = remember(city) {
        citySuggestions.filter {
            it.contains(city, ignoreCase = true) && city.isNotBlank()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🌦️ Weather App",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Input Row
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Search City") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        if (city.isNotBlank()) {
                            viewModel.loadWeatherByCity(city.trim(), apiKey)
                        }
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Search")
                }
            }

            // Autocomplete Suggestions
            if (filteredCities.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        filteredCities.take(4).forEach { suggestion ->
                            Text(
                                text = suggestion,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { city = suggestion }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        when (val state = viewModel.uiState) {
            is UiState.Idle -> {
                Text("Enter a city to see weather", color = Color.Gray)
            }

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Text("Error: ${state.message}", color = Color.Red)
            }

            is UiState.Success -> {
                val data = state.data
                WeatherCard(
                    city = data.name,
                    temp = data.main.temp,
                    feelsLike = data.main.feels_like,
                    condition = data.weather[0].description,
                    iconCode = data.weather[0].icon,
                    humidity = data.main.humidity,
                    wind = data.wind.speed
                )
            }
        }
    }
}
