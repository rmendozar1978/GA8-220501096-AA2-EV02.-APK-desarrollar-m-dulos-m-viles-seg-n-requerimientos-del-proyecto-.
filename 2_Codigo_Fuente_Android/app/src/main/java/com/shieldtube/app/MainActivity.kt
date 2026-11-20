package com.shieldtube.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shieldtube.app.core.navigation.ShieldTubeNavHost
import com.shieldtube.app.core.ui.theme.ShieldTubeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShieldTubeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ShieldTubeNavHost()
                }
            }
        }
    }
}
