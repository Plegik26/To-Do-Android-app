package com.example.todolist

import java.time.Instant
import java.util.Date

data class Todo(
    var id : Int,
    var title : String,
    var createdAt : Date
)

fun getFakeTodo() : List<Todo>  {
    return listOf<Todo>(
        Todo(1, "First Todo", Date.from(Instant.now())),
        Todo(2, "Second Todo", Date.from(Instant.now())),
        Todo(3, "Third Todo", Date.from(Instant.now())),
        Todo(4, "Mantas Todo", Date.from(Instant.now())),
        Todo(5, "Mantas Todo", Date.from(Instant.now())),
        Todo(6, "Mantas Todo", Date.from(Instant.now())),
        Todo(7, "Mantas Todo", Date.from(Instant.now())),
        Todo(8, "Mantas Todo", Date.from(Instant.now())),
        Todo(9, "Mantas Todo", Date.from(Instant.now())),
        Todo(10, "Mantas Todo", Date.from(Instant.now())),
        Todo(11, "Mantas Todo", Date.from(Instant.now())),
        Todo(12, "Mantas Todo", Date.from(Instant.now())),
        Todo(13, "Mantas Todo", Date.from(Instant.now())),
        Todo(14, "Mantas Todo", Date.from(Instant.now())),
        Todo(15, "Mantas Todo", Date.from(Instant.now())),
        );
}