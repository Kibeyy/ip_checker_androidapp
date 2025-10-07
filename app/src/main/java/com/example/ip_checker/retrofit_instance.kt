package com.example.ip_checker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofit_instance {
    const val BASE_URL = "https://api.ipify.org"

    val api: api_service by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api_service::class.java)
    }

}