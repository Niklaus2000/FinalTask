package com.example.myapplication.Presentation.note_update

data class NoteUpdateState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
