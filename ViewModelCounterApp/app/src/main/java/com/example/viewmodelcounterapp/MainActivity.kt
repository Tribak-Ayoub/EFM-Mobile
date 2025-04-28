package com.example.viewmodelcounterapp

import android.os.Bundle
import android.renderscript.Sampler.Value
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
import com.example.viewmodelcounterapp.Inscrite
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
    val inscrites by viewModel.inscrites.collectAsState()
    var newName by remember { mutableStateOf("") }
    var newStatut by remember { mutableStateOf("") }
    var newPriorite by remember { mutableStateOf("") }

    val options = listOf("En cours", "Terminé", "Annulé")
    val prioriteOptions = listOf("Haute", "Moyenne", "Basse")

    var expanded_add by remember { mutableStateOf(false) }
    var expanded_inscrite by remember { mutableStateOf(false) }
    var expanded_add_priorite by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field for adding a new inscrite
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newName,
                onValueChange = { newName = it },
                modifier = Modifier.weight(1f),
                label = { Text("New Inscrite") }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded_add = true }) {
                Text("Statut : $newStatut")
            }

            DropdownMenu(
                expanded = expanded_add,
                onDismissRequest = { expanded_add = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            newStatut = option
                            expanded_add = false
                        }
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded_add_priorite = true }) {
                Text("Priorité : $newPriorite")
            }

            DropdownMenu(
                expanded = expanded_add_priorite,
                onDismissRequest = { expanded_add_priorite = false }
            ) {
                prioriteOptions.forEach { prioriteOptions ->
                    DropdownMenuItem(
                        text = { Text(prioriteOptions) },
                        onClick = {
                            newPriorite = prioriteOptions
                            expanded_add_priorite = false
                        }
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()){
            Button(onClick = {
                if (newName.isNotBlank()) {
                    viewModel.addInscrite(Inscrite(nom = newName, statut = newStatut, priorite = newPriorite))
                    newName = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of inscrites
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(inscrites.size) { index ->
                val inscrite = inscrites[index]
                var editedName by remember(inscrite.id) { mutableStateOf(inscrite.nom) }
                var editedStatut by remember(inscrite.id) { mutableStateOf(inscrite.statut) }
                var editedPriorite by remember(inscrite.id) { mutableStateOf(inscrite.priorite) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.weight(1f)) {

                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = editedName,
                            onValueChange = {
                                editedName = it
                            },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            label = { Text("Edit Inscrite") }
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(onClick = { viewModel.deleteInscrite(inscrite.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }

                    Row {
                        Button(onClick = { expanded_inscrite = true }) {
                            Text("Statut : $editedStatut")
                        }

                        DropdownMenu(
                            expanded = expanded_inscrite,
                            onDismissRequest = { expanded_inscrite = false }
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        editedStatut = option
                                        expanded_inscrite = false
                                    }
                                )
                            }
                        }
                    }

                    Row {
                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                val updateInscrite = inscrite.copy(nom = editedName, statut = editedStatut, priorite = editedPriorite)
                                viewModel.updateInscrite(updateInscrite)
                            }
                        ) {
                            Text("Save")
                        }
                    }

                }
            }
        }
    }
}
