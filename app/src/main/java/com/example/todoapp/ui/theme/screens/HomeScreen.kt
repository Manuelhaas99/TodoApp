package com.example.todoapp.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.ui.theme.components.TodoCard
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: TodoViewModel) {
    val todos = viewModel.todos.value
    val navItems = listOf("Home", "Search", "DateRange")
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Username", fontSize = 15.sp)
                        Row {
                            Icon(
                                imageVector = Icons.Filled.AttachMoney,
                                contentDescription = "",
                            )
                            Text("1999", fontSize = 15.sp)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier
                                .size(128.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .widthIn(min = 100.dp)
                    .padding(16.dp),
                onClick = { navController.navigate("add_todo_screen") },
                contentColor = Color.Black,
                containerColor = Color.White,
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .offset(x = (-6).dp)
                ) {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "Add"
                    )
                    Text(
                        text = "Compose",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .offset(x = 10.dp)
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(
                tonalElevation = 0.dp,
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                contentDescription = item,
                                imageVector = when (index) {
                                    0 -> if (selectedItem == 0) Icons.Filled.Home else Icons.Outlined.Home
                                    1 -> if (selectedItem == 1) Icons.Filled.Search else Icons.Outlined.Search
                                    2 -> if (selectedItem == 2) Icons.Filled.DateRange else Icons.Outlined.DateRange
                                    else -> Icons.Default.Home
                                },
                                tint = if (selectedItem == index) Color.White else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(todos) { todo ->
                TodoCard(todo = todo)
            }
        }
    }
}