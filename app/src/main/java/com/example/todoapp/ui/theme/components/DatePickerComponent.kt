package com.example.todoapp.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    initialDate: String,
    onDateSelected: (String) -> Unit,
    initialSelectedDate: Long = 0,
) {
    val calculatedInitialDate = if (initialSelectedDate != 0L) {
        initialSelectedDate
    }else {
        LocalDateTime.now().converterDateTimeToMillis()
    }
    val resolverInitialDate = if (initialDate.isNotBlank()){
        initialDate
    }else{
        calculatedInitialDate.formatDateFromMillis()
    }

    var selectedDate by remember(resolverInitialDate) { mutableStateOf(resolverInitialDate) }
    var showDatePicker by remember { mutableStateOf(false) }
    var datePickerState = rememberDatePickerState()


    LaunchedEffect(initialDate) {
        selectedDate = initialDate
    }

    LaunchedEffect(Unit) {
        datePickerState = DatePickerState(
            locale = CalendarLocale("es"),
            initialSelectedDateMillis = calculatedInitialDate,
        )
    }
    Box(
        modifier = Modifier
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            label = { Text("Date") },
            readOnly = true,
            trailingIcon = {

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Date"
                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(64.dp),
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    showDatePicker = true
                }
        )
    }
        if (showDatePicker == true) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false

                },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState?.selectedDateMillis?.let { millis ->
                            val formattedDate = millis.formatDateFromMillis()
                            selectedDate = formattedDate
                            onDateSelected(formattedDate)
                        }
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false

                    }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }

