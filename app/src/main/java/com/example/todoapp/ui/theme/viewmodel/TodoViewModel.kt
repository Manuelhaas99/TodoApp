package com.example.todoapp.ui.theme.viewmodel

import android.text.BoringLayout
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

data class Todo (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val tag: String = "",
    val isFavorite: Boolean = false,
    val isChecked: Boolean = false,
)

class TodoViewModel: ViewModel() {
    private val _state = MutableStateFlow(Todo())
    val state = _state.asStateFlow()

    private val _todos = MutableStateFlow<MutableList<Todo>>(mutableListOf())
    val todos: StateFlow<List<Todo>> = _todos

    fun addTodo(){
        val newTodo = Todo(
            id = todos.value.size + 1,
            title = _state.value.title,
            description = _state.value.description
        )
        _todos.update { todos ->
            todos.toMutableList().apply {
                add(newTodo)
            }
        }

    }
}