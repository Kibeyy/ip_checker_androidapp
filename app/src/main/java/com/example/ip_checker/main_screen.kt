package com.example.ip_checker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(viewmodel: ip_viewmodel){
    val current_screen_state = viewmodel.state.value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("IP RECOGNIZER")}
            )
        }
    ) { paddingValues ->

        when(current_screen_state){
            is states.Error -> Error(current_screen_state.message)
            is states.Loading -> LoadingScreen()
            is states.Success -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp)
                ) {
                    Text("Your IP Address is:")
                    Spacer(Modifier.height(10.dp))
                    Text(current_screen_state.ip.ip, fontWeight = FontWeight.Bold, fontSize = 25.sp)


                }
            }
        }



    }
}

@Composable
fun LoadingScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LinearProgressIndicator()
        Spacer(Modifier.height(5.dp))
        Text("Getting IP Address...")
    }
}

@Composable
fun Error(message:String){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Text(message,
                modifier = Modifier)
            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {}
                , shape = RoundedCornerShape(10.dp)
            ) {
                Text("Retry", fontWeight = FontWeight.Bold)
            }
        }
    }

}