package com.example.todoapp.ui.theme.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.screens.AddTodoScreen
import com.example.todoapp.ui.theme.screens.HomeScreen
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val viewModel = TodoViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route ) {
            HomeScreen(navController, viewModel)
        }
        composable(route = AppScreens.AddTodoScreen.route){
            AddTodoScreen(navController, viewModel)
        }

    }
}