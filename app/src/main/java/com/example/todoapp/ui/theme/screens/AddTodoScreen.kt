package com.example.todoapp.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTodoScreen(navController: NavController, viewModel: TodoViewModel) {
    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("home_screen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
//            floatingActionButton = {
//                Box(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth(),
//                ) {
//                    ElevatedButton(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        onClick = {}
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .padding(horizontal = 8.dp)
//                        ) {
//                            Icon(Icons.Default.Save, contentDescription = "Saving todo button",)
//                            Text(
//                                modifier = Modifier
//                                    .padding(vertical = 3.dp),
//                                text = "Save",
//                            )
//                        }
//                    }
//                }
//            }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = "",
                onValueChange = { },
                label = { Text("Add a Title") },
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = "",
                onValueChange = { },
                label = { Text("Add a Todo") },
            )

            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                onClick = { /* Guardar */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Text("Save", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}