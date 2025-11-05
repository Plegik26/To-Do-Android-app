package com.example.todolist.screens

import android.R.attr.right
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { navController.navigate("screen1") },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back Arrow"
                                )
                            }
                        )
                        Text(
                            text = "${selectedItem?.title}",
                            fontSize = 36.sp,
                            /* TODO: Try align text center */
                        )
                    }
                }
            )

        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // add new note field
            OutlinedTextField(
                value = newNote,
                onValueChange = { newNote = it },
                label = { Text("Add a note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // add note
            Button(
                onClick = {
                    if (newNote.isNotBlank()) {
                        viewModel.addNoteToSelectedItem(newNote)
                        newNote = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Add note")
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Priority:",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    fontSize = 20.sp
                )

                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (selectedItem!!.priority == 0xFF9E1C03) {
                            viewModel.changePriority(0xFF2E6F40)
                        } else {
                            viewModel.changePriority(0xFF9E1C03)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = prioColor,
                        uncheckedTrackColor = prioColor
                    )
                )
            }
        }
    }
}