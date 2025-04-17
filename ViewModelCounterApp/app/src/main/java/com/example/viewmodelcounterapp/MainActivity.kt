package com.example.viewmodelcounterapp

//import CompteurViewModel
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MonApp()
//        }
//    }
//}
//
//@Composable
//fun MonApp(viewModel: CompteurViewModel = viewModel()) {
//    val compteur by viewModel.compteur.collectAsState() // Observe state
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Compteur : $compteur", style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = { viewModel.incrementer() }) {
//                Text("Incrémenter")
//            }
//        }
//    }
//}

// tuto 2

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TodoApp()
//        }
//    }
//}
//
//@Composable
//fun TodoApp() {
//    var titre by remember { mutableStateOf("Loading...") }
//
//    // API Call using enqueue() (asynchronous but without coroutines)
//    LaunchedEffect(Unit) {
//        val call = RetrofitClient.api.getTodo()
//        call.enqueue(object : Callback<Todo> {
//            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
//                if (response.isSuccessful) {
//                    val todo = response.body()
//                    titre = todo?.title ?: "Empty response"
//                } else {
//                    titre = "HTTP Error ${response.code()}"
//                }
//            }
//
//            override fun onFailure(call: Call<Todo>, t: Throwable) {
//                titre = "Network Error: ${t.message}"
//            }
//        })
//    }
//
//    // Display the fetched title
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier.fillMaxSize().padding(24.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = titre, style = MaterialTheme.typography.titleLarge)
//        }
//    }
//}

// tuto 3


//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            CounterApp()
//        }
//    }
//}
//
//@Composable
//fun CounterApp() {
//    var compteur by remember { mutableStateOf(0) }
//
//    // ✅ Safe Coroutine: Automatically runs when the Composable is shown
//    LaunchedEffect(Unit) {
//        while (true) {
//            kotlinx.coroutines.delay(1000) // ✅ Non-blocking delay
//            compteur++  // ✅ Safe state update in Compose
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Compteur : $compteur", style = MaterialTheme.typography.headlineLarge)
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { compteur = 0 }) {
//            Text("Réinitialiser")
//        }
//    }
//}


// tuto 4


//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TodoScreen()  // Composable function to display the task
//        }
//    }
//}
//
//@Composable
//fun TodoScreen() {
//    // State to hold the title of the task, initially set to "Loading..."
//    var title by remember { mutableStateOf("Chargement...") }
//
//    // Launch a coroutine to fetch the task title asynchronously
//    LaunchedEffect(Unit) {
//        try {
//            // Perform the network call on the IO dispatcher for good practice
//            val todo = withContext(Dispatchers.IO) {
//                RetrofitClient.api.getTodo()  // Fetch the task from API
//            }
//            title = todo.title  // Update the UI with the task title
//        } catch (e: Exception) {
//            title = "Erreur : ${e.message}"  // Handle any errors
//        }
//    }
//
//    // Layout of the UI using Compose
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(24.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = title, style = MaterialTheme.typography.titleLarge)  // Display the title
//        }
//    }
//}


// tuto 5

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TodoScreen()
//        }
//    }
//}
//
//@Composable
//fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
//    val todos by viewModel.todos.collectAsState()
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        LazyColumn(
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(todos) { todo ->
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .padding(16.dp)
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Checkbox(
//                            checked = todo.completed,
//                            onCheckedChange = null // No action on check for now
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(text = todo.title)
//                    }
//                }
//            }
//        }
//    }
//}


// todoApp

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
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.collectAsState()
    var newTitle by remember { mutableStateOf("") }

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
                    viewModel.addTask(Todo(title = newTitle, completed = false))
                    newTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of tasks
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(todos.size) { index ->
                val todo = todos[index]
                var editedTitle by remember(todo.id) { mutableStateOf(todo.title) }

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
                    }
                    IconButton(onClick = { viewModel.deleteTask(todo.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}
