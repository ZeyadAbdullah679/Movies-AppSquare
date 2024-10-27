package com.company.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.company.movies.presentation.navigation.NavGraph
import com.company.movies.presentation.theme.AppTheme
import com.company.movies.util.ConnectivityObserver
import com.company.movies.util.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            val isNetworkAvailable by connectivityObserver.observe()
                .collectAsStateWithLifecycle(
                    initialValue = ConnectivityObserver.Status.Unavailable
                )
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavGraph(
                        isNetworkAvailable = isNetworkAvailable,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}