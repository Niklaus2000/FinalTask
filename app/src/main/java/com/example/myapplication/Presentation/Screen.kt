package com.example.myapplication.Presentation

sealed class Screen(val route: String) {
    object NoteListScreen: Screen("note_list")
    object NoteUpdateScreen: Screen("update_screen")
    object NoteCreateScreen : Screen("note_create_screen")
}