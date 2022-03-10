package com.example.instagramui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.instagramui.ui.theme.InstagramUITheme

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramUITheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProfileScreen()
                }
            }
        }
    }
}