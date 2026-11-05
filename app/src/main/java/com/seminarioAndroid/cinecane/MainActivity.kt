package com.seminarioAndroid.cinecane

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.seminarioAndroid.cinecane.ui.navigation.CineCaneNavHost

class MainActivity () : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineCaneNavHost()
        }
    }
}
