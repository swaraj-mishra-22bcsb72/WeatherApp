# 🌦️ Weather App

A modern Android application built with **Jetpack Compose** that lets users search and view current weather conditions by city. Powered by the **OpenWeatherMap API**.

---

## 📱 APK Download

[Download APK](https://drive.google.com/file/d/1hD4AUUIHkJvAHFI3moIzUQMhCjwaC0hU/view?usp=sharing)

---

## 🧰 Tech Stack

- **Jetpack Compose** – Modern UI toolkit
- **MVVM Architecture** – Clean separation of concerns
- **Kotlin Coroutines** – Asynchronous network handling
- **Retrofit** – Networking
- **OpenWeatherMap API** – Weather data provider
- **Material3** – UI components
- **State Management** – `mutableStateOf`, `viewModel`

---

## 🌍 Features

- 🔍 **Search weather by city**
- 📍 Autocomplete suggestions for popular cities
- ☁️ **Emoji-based** weather icons (e.g., ☀️, 🌧️)
- 📊 Shows temperature, feels-like, humidity, and wind speed
- ⚡ Responsive UI with loading and error handling
- ✨ Clean and modern Material3 design

---

## 📦 Project Structure

com.example.weatherapp/
│
├── data/
│ ├── model/ # WeatherResponse, Main, Weather, Wind
│ ├── repository/ # WeatherRepository.kt
│
├── network/
│ └── RetrofitInstance.kt
│
├── ui/
│ ├── screens/ # WeatherScreen.kt
│ └── WeatherCard.kt
│
├── viewmodel/
│ └── WeatherViewModel.kt
│
└── MainActivity.kt


---

## 🔑 API Usage

This app uses the free tier of [OpenWeatherMap](https://openweathermap.org/current).  
Default API key used: `6ef044554e00015e0c85789011fbd48e`

> ⚠️ **Tip:** Replace it with your own API key in production.

---

## 🚀 Getting Started

1. Clone the project:
   ```bash
   git clone https://github.com/your-username/weather-app.git
