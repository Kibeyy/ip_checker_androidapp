package com.example.ip_checker

import ip_model
import retrofit2.http.GET

interface api_service {
    @GET("/?format=json")
    suspend fun get_ip(): ip_model
}