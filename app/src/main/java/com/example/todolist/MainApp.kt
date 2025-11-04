package com.example.todolist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todolist.screens.Screen1
import com.example.todolist.screens.Screen2
import com.example.todolist.screens.Screen3

// Define routes
sealed class Screens(val route: String, val title: String) {
    object Screen1 : Screens("screen1", "To-Do")
    object Screen2 : Screens("screen2", "Details")
    object Screen3 : Screens("screen3", "History")
}

@Composable
fun MainApp(navController: NavHostController, viewModel: MyViewModel) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Screen1.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Screen1.route) { Screen1(navController, viewModel) }
            composable(Screens.Screen2.route) { Screen2(navController, viewModel) }
            composable(Screens.Screen3.route) { Screen3(navController, viewModel) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screens.Screen1,
        Screens.Screen3
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    when (screen) {
                        Screens.Screen1 -> Icon(imageVector = Icons.Default.List, contentDescription = screen.title)
                        Screens.Screen3 -> Icon(imageVector = Icons.Default.DateRange, contentDescription = screen.title)
                        else -> {}
                    }
                },
                label = { Text(screen.title) }
            )
        }
    }
}
