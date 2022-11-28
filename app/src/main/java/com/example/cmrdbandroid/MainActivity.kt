package com.example.cmrdbandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.cmrdbandroid.presentation.FirstScreen
import com.example.cmrdbandroid.ui.theme.CmrdbAndroidTheme
import com.example.cmrdbandroid.viewmodel.FirstViewModel

class MainActivity : ComponentActivity() {

    private val firstViewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CmrdbAndroidTheme {
                FirstScreen(firstViewModel)
            }
        }
    }
}