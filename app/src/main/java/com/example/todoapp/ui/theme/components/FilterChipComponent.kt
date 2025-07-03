package com.example.todoapp.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.viewmodel.TodoViewModel

@Composable
fun FilterChipComponent(
) {
    val todosChips = listOf(
        "Todos Done",
        "Favorite Todos",
        "No se alv"
    )
    var selectedIndex by remember { mutableIntStateOf(-1) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        todosChips.forEachIndexed { index, label ->
            FilterChip(
                modifier = Modifier
                    .padding(end = 8.dp),
                onClick = {
                    selectedIndex = if (selectedIndex == index) -1 else index
                },
                label = { Text(label) },
                selected = selectedIndex == index,
                leadingIcon = if (selectedIndex == index) {
                    {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Filter chip",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                colors = FilterChipDefaults.filterChipColors(
                    //Selected Chip color
                    selectedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    selectedLabelColor = MaterialTheme.colorScheme.background,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}