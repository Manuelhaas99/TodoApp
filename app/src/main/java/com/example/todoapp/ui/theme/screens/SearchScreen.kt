package com.example.todoapp.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.ui.theme.components.FilterChipComponent
import com.example.todoapp.ui.theme.components.SimpleSearchBar
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel


@Composable
fun SearchScreen(navController: NavController, viewModel: TodoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SimpleSearchBar()
        FilterChipComponent()
    }
}
