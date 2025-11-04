package com.example.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Screen2(navController: NavController, viewModel: MyViewModel) {
    val selectedItem by viewModel.selectedItem.observeAsState()
    var newNote by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "${selectedItem?.title}",
            fontSize = 36.sp,
            modifier = Modifier.padding(24.dp)
        )

        // Show existing notes if any
        if (!selectedItem?.notes.isNullOrEmpty()) {
            Text(
                text = "Existing Notes:",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 24.dp)
            ) {
                items(selectedItem!!.notes) { note ->
                    if (note.isNotBlank()) {
                        Text(
                            text = "- $note",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }

        // Input field to add new note
        OutlinedTextField(
            value = newNote,
            onValueChange = { newNote = it },
            label = { Text("Add a note") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Save note
        Button(
            onClick = {
                if (newNote.isNotBlank()) {
                    viewModel.addNoteToSelectedItem(newNote)
                    newNote = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save note")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Back button
        Button(onClick = { navController.navigate("screen1") }) {
            Text("Back to list")
        }
    }
}
