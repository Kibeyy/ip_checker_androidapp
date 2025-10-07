package com.example.ip_checker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip_model
import kotlinx.coroutines.launch

sealed class states {
    object Loading : states()
    class Success(val ip:ip_model) : states()
    class Error (val message:String) : states()


}

class ip_viewmodel: ViewModel() {
    private val repo = ip_repository()

    private var internalState: MutableState<states> = mutableStateOf(states.Loading)
    val state: MutableState<states> = internalState
    init {
        get_ip()
    }

    fun get_ip(){
        viewModelScope.launch {
            internalState.value = states.Loading
            try {
                val ip_address_data: ip_model = repo.get_ip_address()
                internalState.value = states.Success(ip_address_data)

            }catch (e: Exception){
                internalState.value = states.Error("An error occurred:${e.message}")

            }
        }
    }
}