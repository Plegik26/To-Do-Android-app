package com.example.todolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavController, viewModel: MyViewModel) {
    val selectedItem by viewModel.selectedItem.observeAsState()
    val prioColor = Color(selectedItem!!.priority)
    var newNote by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.navigate("screen1") }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Arrow",
                            )
                        }
                        Text(
                            text = selectedItem!!.title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title label
            Text(
                text = "Add Notes",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )

            // Input field
            OutlinedTextField(
                value = newNote,
                onValueChange = { newNote = it },
                label = { Text("Add a note") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )

            // Add note button
            Button(
                onClick = {
                    if (newNote.isNotBlank()) {
                        viewModel.addNoteToSelectedItem(newNote)
                        newNote = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E6F40),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Add Note",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Priority toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Priority:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (selectedItem?.priority == 0xFF9E1C03) {
                            viewModel.changePriority(0xFF2E6F40)
                        } else {
                            viewModel.changePriority(0xFF9E1C03)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = prioColor,
                        uncheckedTrackColor = prioColor,
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White
                    )
                )
            }
        }
    }
}
