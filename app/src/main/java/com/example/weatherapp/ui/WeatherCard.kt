package com.example.weatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WeatherCard(
    city: String,
    temp: Double,
    feelsLike: Double,
    condition: String,
    iconCode: String,
    humidity: Int,
    wind: Double
) {
    val emoji = getEmojiForIcon(iconCode)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // City name
            Text(
                text = "📍 $city",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Temperature row with emoji
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$emoji  ${temp}°C",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Feels like: ${feelsLike}°C",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Weather condition
            Text(
                text = "Condition: ${condition.replaceFirstChar { it.uppercase() }}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Humidity and wind in a row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("💧 Humidity: $humidity%", style = MaterialTheme.typography.bodyMedium)
                Text("🌬️ Wind: $wind m/s", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}



fun getEmojiForIcon(icon: String): String {
    return when (icon) {
        "01d", "01n" -> "☀️"
        "02d", "02n" -> "🌤️"
        "03d", "03n", "04d", "04n" -> "☁️"
        "09d", "09n" -> "🌧️"
        "10d", "10n" -> "🌦️"
        "11d", "11n" -> "⛈️"
        "13d", "13n" -> "❄️"
        "50d", "50n" -> "🌫️"
        else -> "🌈"
    }
}
