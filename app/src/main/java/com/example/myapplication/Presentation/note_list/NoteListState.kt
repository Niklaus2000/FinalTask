package com.example.myapplication.Presentation.note_list

import com.example.myapplication.Domain.Model.Note

data class NoteListState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val error: String = ""
)

