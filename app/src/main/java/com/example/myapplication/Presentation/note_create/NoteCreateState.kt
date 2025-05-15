package com.example.myapplication.Presentation.note_create

data class NoteCreateState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)