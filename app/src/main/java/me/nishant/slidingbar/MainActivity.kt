package me.nishant.slidingbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.nishant.sliding_bar.SlidingBar
import me.nishant.sliding_bar.SlidingBarDefaults
import me.nishant.slidingbar.ui.theme.SlidingbarTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlidingbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var value by remember {
                        mutableStateOf(0f)
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = value.toString(),
                            style = MaterialTheme.typography.h1
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SlidingBar(
                            value = value,
                            onValueChanged = { value = it },
                            modifier = Modifier
                                .padding(horizontal = 64.dp)
                                .fillMaxWidth(),
                            colors = SlidingBarDefaults.colors(
                                colorPrimary = Color.Green,
                                colorTrack = Color.Blue
                            ),
                            valueRange = 0f..30f,
                            stepSize = 1f
                        )
                    }
                }
            }
        }
    }
}