package com.example.ip_checker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(viewmodel: ip_viewmodel) {
    val current_screen_state = viewmodel.state.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("IP RECOGNIZER") }
            )
        }
    ) { paddingValues ->
        when (current_screen_state) {
            is states.Home -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        ),
                        onClick = { viewmodel.get_ip() }
                    ) {
                        Text("FETCH IP ADDRESS", color = Color.White)
                    }
                }
            }

            is states.Error -> Error(current_screen_state.message)
            is states.Loading -> LoadingScreen()

            is states.Success -> {
                SuccessScreen(
                    state = current_screen_state,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(state: states.Success, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(10.dp))
        Text("Your IP Address is:")
        Spacer(Modifier.height(20.dp))
        Text(
            text = state.ip.ip,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LinearProgressIndicator()
        Spacer(Modifier.height(5.dp))
        Text("Getting IP Address...")
    }
}

@Composable
fun Error(message: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(message)
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Retry", fontWeight = FontWeight.Bold)
        }
    }
}
