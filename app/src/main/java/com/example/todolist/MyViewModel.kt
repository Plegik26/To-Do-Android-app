package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.util.Date

class MyViewModel : ViewModel() {
    private val _todoItems = MutableLiveData<List<Todo>>()
    val todoItems : LiveData<List<Todo>> = _todoItems

    private val _selectedItem = MutableLiveData<Todo>()
    val selectedItem : LiveData<Todo> = _selectedItem

    fun setSelectedItem(item : Todo)   {
        _selectedItem.value = item
    }

}

data class Todo(
    var id : Int,
    var title : String,
    var note : String,
    var createdAt : Date
)

fun getTodo() : List<Todo>  {
    return listOf(
        Todo(1, "First Todo", "",Date.from(Instant.now())),
        Todo(2, "Second Todo", "",Date.from(Instant.now())),
        Todo(3, "Third Todo", "",Date.from(Instant.now())),
        Todo(4, "Fourth Todo", "",Date.from(Instant.now())),
        Todo(5, "Fifth Todo", "",Date.from(Instant.now())),
        Todo(6, "Sixth Todo", "",Date.from(Instant.now())),
        Todo(7, "Seventh Todo", "",Date.from(Instant.now())),
        Todo(8, "Eight Todo", "",Date.from(Instant.now())),
        Todo(9, "Ninth Todo", "", Date.from(Instant.now())),
        Todo(10, "Tenth Todo", "", Date.from(Instant.now())),
    )
}

