package com.example.todoapp.ui.theme.navigation

import android.health.connect.datatypes.ExerciseRoute

sealed class AppScreens(val route: String){
    object HomeScreen : AppScreens("home_screen")
    object AddTodoScreen: AppScreens("add_todo_screen")

}