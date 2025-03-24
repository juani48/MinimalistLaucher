package com.juani48.minimalistlaucher

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.juani48.minimalistlaucher.view.HomeActivity
import com.juani48.minimalistlaucher.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = AppViewModel()
            HomeActivity(this, appViewModel)
        }
    }
}