package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel : MyViewModel = viewModel()
            NavHost(navController = navController, startDestination = "screen1") {
                composable("screen1")    {
                    Screen1(navController, viewModel)
                }
                composable("screen2")    {
                    Screen2(navController, viewModel)
                }
                composable("screen3")    {
                    Screen3(navController, viewModel)
                }
            }


        }
    }
}

