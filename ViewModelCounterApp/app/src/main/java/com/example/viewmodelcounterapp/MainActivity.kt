package com.example.viewmodelcounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelcounterapp.Todo
import com.example.viewmodelcounterapp.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen()
            DropdownMenu()
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.collectAsState()
    var newTitle by remember { mutableStateOf("") }
    var newPriorite by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field for adding a new task
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("New Task") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newTitle.isNotBlank()) {
                    viewModel.addTask(Todo(title = newTitle, completed = false, Priorite = newPriorite))
                    newTitle = ""


                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(66.dp))

        // Display the list of tasks
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(todos.size) { index ->
                val todo = todos[index]
                var editedTitle by remember(todo.id) { mutableStateOf(todo.title) }
                val options = listOf("Haute", "Moyenne", "Basse")
                var selection by remember { mutableStateOf(todo.Priorite) }
                var expanded by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        Checkbox(
                            checked = todo.completed,
                            onCheckedChange = {
                                val updatedTodo = todo.copy(completed = it)
                                viewModel.updateTask(updatedTodo)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = editedTitle,
                            onValueChange = {
                                editedTitle = it
                            },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            label = { Text("Edit Task") }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val updatedTodo = todo.copy(title = editedTitle)
                                viewModel.updateTask(updatedTodo)
                            }
                        ) {
                            Text("Save")
                        }
                        Button(onClick = { expanded = true }) {
                            Text("Priorité : $selection")
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selection = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    IconButton(onClick = { viewModel.deleteTask(todo.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenu() {
    val options = listOf("Haute", "Moyenne", "Basse")
    var selection by remember { mutableStateOf("Moyenne") }
    var expanded by remember { mutableStateOf(false) }

    Column {
        Spacer(modifier = Modifier.height(76.dp))
        Button(onClick = { expanded = true }) {
            Text("Priorité : $selection")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selection = option
                        expanded = false
                    }
                )
            }
        }
    }
}

