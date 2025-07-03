package com.example.todoapp.ui.theme.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.screens.AddTodoScreen
import com.example.todoapp.ui.theme.screens.HomeScreen
import com.example.todoapp.ui.theme.screens.MainScreen
import com.example.todoapp.ui.theme.screens.NoteScreen
import com.example.todoapp.ui.theme.screens.SearchScreen
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val viewModel = remember { TodoViewModel() }

    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = currentBackStackEntry?.destination?.route
    val isAddTodoScreen = currentDestination == AppScreens.AddTodoScreen.route

    if (isAddTodoScreen){
        AddTodoScreen(navController, viewModel)
    } else {
        MainScreen(navController, viewModel) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppScreens.HomeScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(AppScreens.HomeScreen.route) {
                    HomeScreen(navController, viewModel)
                }
                composable(AppScreens.AddTodoScreen.route) {
                    AddTodoScreen(navController, viewModel)
                }
                composable(AppScreens.SearchScreen.route) {
                    SearchScreen(navController, viewModel)
                }
                composable(AppScreens.NoteScreen.route) {
                    NoteScreen(navController, viewModel)
                }
            }
        }
    }
}





