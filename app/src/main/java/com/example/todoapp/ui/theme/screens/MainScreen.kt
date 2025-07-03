package com.example.todoapp.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todoapp.ui.theme.components.ExpandableFABMenu
import com.example.todoapp.ui.theme.components.FabActionItem
import com.example.todoapp.ui.theme.navigation.AppScreens
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: TodoViewModel,
    content: @Composable (PaddingValues) -> Unit
) {
    val todos = viewModel.todos.value
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val navItems = listOf(
        AppScreens.HomeScreen,
        AppScreens.SearchScreen,
        AppScreens.NoteScreen
    )
    var isFabMenuExpanded by remember { mutableStateOf(false) }
    val fabActions = listOf(
        FabActionItem(Icons.Filled.Create, "Create a Todo"){},
        FabActionItem(Icons.Filled.EditNote, "Create a Note"){}
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp),
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Manuel haas", fontSize = 15.sp)
                                Icon(
                                    imageVector = Icons.Filled.EmojiEvents,
                                    contentDescription = "",
                                )
                                Text("0", fontSize = 15.sp)
                            }
                        }
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                )
            )
        },
        floatingActionButton = {
            ExpandableFABMenu(
                modifier = Modifier.padding(12.dp),
                actions = fabActions,
                isExpanded = isFabMenuExpanded,
                onToggle = { isFabMenuExpanded = !isFabMenuExpanded }
            )
        },
        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {
            NavigationBar(
                tonalElevation = 0.dp,
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                navItems.forEachIndexed { index, screen ->
                    val isSelected = currentRoute == screen.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if(!isSelected){
                                navController.navigate(screen.route){
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState = true
                                    }
                                }
                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                contentDescription = "",
                                imageVector = when (screen) {
                                    AppScreens.HomeScreen -> if (isSelected) Icons.Filled.Home else Icons.Outlined.Home
                                    AppScreens.SearchScreen -> if (isSelected) Icons.Filled.Search else Icons.Outlined.Search
                                    AppScreens.NoteScreen -> if (isSelected) Icons.Filled.EditNote else Icons.Outlined.EditNote
                                    else -> Icons.Default.Home
                                },
                                tint = if (isSelected) Color.White else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ){
                content(PaddingValues())
                if (isFabMenuExpanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable { isFabMenuExpanded = false }
                    )
                }
            }
        }
    )
}