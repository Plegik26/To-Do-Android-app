package com.example.todolist

import android.R.attr.text
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Locale

@Composable
fun Screen1(navController : NavController)  {
    val todoList = getFakeTodo()

    Column(
        Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "To-Do List!",
            modifier = Modifier.padding(50.dp),
            fontSize = 60.sp,
        )

        LazyColumn(
            modifier = Modifier.height(430.dp)
        ) {
            items(todoList) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(0xFF2E6F40))
                        .padding(16.dp)
                        .clickable(onClick = { navController.navigate("screen2") })
                ) {
                    Column {
                        Text(
                            text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                            fontSize = 10.sp,
                            color = Color.LightGray
                        )
                        Text(
                            text = item.title,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}