package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.util.Date
import kotlin.collections.map

data class Todo(
    var id : Int,
    var title : String,
    var notes : List<String>,
    var createdAt : Date
)

class MyViewModel : ViewModel() {
    private val _todoItems = MutableLiveData(getTodo())
    val todoItems : LiveData<List<Todo>> = _todoItems

    private val _selectedItem = MutableLiveData<Todo>()
    val selectedItem : LiveData<Todo> = _selectedItem

    fun setSelectedItem(item : Todo)   {
        _selectedItem.value = item
    }

    fun addNoteToSelectedItem(newNote: String) {
        val currentItem = _selectedItem.value
        val updatedNotes = currentItem.notes + newNote
        val updatedItem = currentItem.copy(notes = updatedNotes)

        _selectedItem.value = updatedItem

        // Update the main list too
        _todoItems.value?.map { item ->
            if (item.id == updatedItem.id) {
                item.notes = updatedNotes
            }

        }
    }
}
fun getTodo() : List<Todo>  {
    return listOf(
        Todo(1, "First Todo", listOf(""),Date.from(Instant.now())),
        Todo(2, "Second Todo", listOf(""),Date.from(Instant.now())),
        Todo(3, "Third Todo", listOf(""),Date.from(Instant.now())),
        Todo(4, "Fourth Todo", listOf(""),Date.from(Instant.now())),
        Todo(5, "Fifth Todo", listOf(""),Date.from(Instant.now())),
        Todo(6, "Sixth Todo", listOf(""),Date.from(Instant.now())),
        Todo(7, "Seventh Todo", listOf(""),Date.from(Instant.now())),
        Todo(8, "Eight Todo", listOf(""),Date.from(Instant.now())),
        Todo(9, "Ninth Todo", listOf(""), Date.from(Instant.now())),
        Todo(10, "Tenth Todo", listOf(""), Date.from(Instant.now())),
    )
}

