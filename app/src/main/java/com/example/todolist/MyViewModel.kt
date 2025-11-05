package com.example.todolist

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.collections.map

data class Todo(
    var id: Int,
    var title: String,
    var notes: List<String>,
    var priority: Long
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

    fun changePriority(newPrio : Long)  {
        val currentItem = _selectedItem.value
        val updatedItem = currentItem.copy(priority = newPrio)
        _selectedItem.value = updatedItem

        // Update the main list too
        _todoItems.value?.map { item ->
            if (item.id == updatedItem.id) {
                item.priority = newPrio
            }
        }
    }
}
fun getTodo() : List<Todo>  {
    return listOf(
        Todo(1, "Shopping", listOf(""),0xFF2E6F40),
        Todo(2, "Notes", listOf(""),0xFF2E6F40),
        Todo(3, "Movies", listOf(""),0xFF2E6F40),
        Todo(4, "Music", listOf(""),0xFF2E6F40),
        Todo(5, "Friends", listOf(""),0xFF2E6F40),
        Todo(6, "Assignment", listOf(""),0xFF2E6F40),
        Todo(7, "Games", listOf(""),0xFF2E6F40),
        Todo(8, "Recipes", listOf(""),0xFF2E6F40),
        Todo(9, "College", listOf(""), 0xFF2E6F40),
        Todo(10, "Books", listOf(""), 0xFF2E6F40),
    )
}

