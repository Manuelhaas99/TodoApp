package com.example.todoapp.ui.theme.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel

@Composable
fun NoteScreen(navController: NavController, viewModel: TodoViewModel){
    Text(
        text = "Notes Screen",
        color = Color.White
    )
}