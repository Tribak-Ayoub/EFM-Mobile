package com.example.viewmodelcounterapp

import android.graphics.BitmapFactory

data class Todo(
    val id: Int = 0,
    val title: String,
    val completed: Boolean,
    val Priorite: String = "Moyenne",
)
