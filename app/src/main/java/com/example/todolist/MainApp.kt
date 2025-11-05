package com.example.todolist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist.screens.Screen1
import com.example.todolist.screens.Screen2
import com.example.todolist.screens.Screen3

// routes
sealed class Screens(val route: String, val title: String) {
    object Screen1 : Screens("screen1", "To-Do")
    object Screen2 : Screens("screen2", "Details")
    object Screen3 : Screens("screen3", "Notes")
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val viewModel: MyViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Hide bottom bar on Screen2
            if (currentRoute != Screens.Screen2.route) {
                BottomNavigationBar(navController, currentRoute)
            }
        },
        containerColor = Color(0xFFF8F8F8)
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
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    val items = listOf(
        Screens.Screen1,
        Screens.Screen3
    )

    NavigationBar(
        containerColor = Color.White,
    ) {
        items.forEach { screen ->
            val selected = currentRoute == screen.route

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    when (screen) {
                        Screens.Screen1 -> Icon(imageVector = Icons.Default.List, contentDescription = screen.title)
                        Screens.Screen3 -> Icon(imageVector = Icons.Default.MailOutline, contentDescription = screen.title)
                        else -> {}
                    }
                },
                label = {
                    Text(
                        text = screen.title,
                        color = if (selected) Color(0xFF2E6F40) else Color.Gray,
                    )
                }
            )
        }
    }
}
