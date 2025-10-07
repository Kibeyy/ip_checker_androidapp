package com.example.ip_checker

import com.example.ip_checker.retrofit_instance.api
import ip_model

class ip_repository {
    suspend fun get_ip_address():ip_model {
        return retrofit_instance.api.get_ip()
    }
}