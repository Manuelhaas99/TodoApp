package com.example.todoapp.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.ui.theme.components.formatDayWithYear
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

data class Todo(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = LocalDateTime.now().formatDayWithYear(),
    val tag: String = "",
    val isFavorite: Boolean = false,
    val isChecked: Boolean = false,
)

class TodoViewModel : ViewModel() {
    private val _state = MutableStateFlow(Todo())
    val state = _state.asStateFlow()

    private val _todos = MutableStateFlow<MutableList<Todo>>(mutableListOf())
    val todos: StateFlow<List<Todo>> = _todos

    fun updateTodoTitle(newTodoTitle: String) {
        _state.update {
            it.copy(title = newTodoTitle)
        }
    }

    fun updateTodoDescription(newTodoDescription: String) {
        _state.update {
            it.copy(description = newTodoDescription)
        }
    }

    fun updateDate(newDate: String){
        _state.update {
            it.copy(date = newDate)
        }
    }

    fun addTodo() {
        val newTodo = Todo(
            id = todos.value.size + 1,
            title = _state.value.title,
            description = _state.value.description,
            date = _state.value.date
        )
        _todos.update { todos ->
            todos.toMutableList().apply {
                add(newTodo)
            }
        }
        clearTodoFields()
    }

    fun editTodo(todoId: Int) {
        _todos.update { todos ->
            todos.map {
                if (it.id == todoId) {
                    it.copy(
                        title = _state.value.title,
                        description = _state.value.description,
                        date = _state.value.date
                    )
                } else {
                    it
                }
            }.toMutableList()
        }
        clearTodoFields()
    }

    private fun clearTodoFields() {
        _state.update {
            it.copy(
                title = "",
                description = "",
                date = LocalDateTime.now().formatDayWithYear()
            )
        }
    }

    enum class TodosChips(val value: String){
        TodoDone("TodoDone"),
        FavoriteTodo("Favorites"),
        NoSeAlv("No se alv")
    }

    fun getAllTodosChips(): List<TodosChips>{
        return listOf(TodosChips.TodoDone, TodosChips.FavoriteTodo, TodosChips.NoSeAlv)
    }


}