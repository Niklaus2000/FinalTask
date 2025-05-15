package com.example.myapplication.Presentation.note_create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.Presentation.note_create.components.NoteAddItem

@Composable
fun NoteCreateScreen(
    navController: NavController,
    viewModel: NoteCreateViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.isSuccess) {
        LaunchedEffect(Unit) {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("refresh", true)

            navController.popBackStack()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        NoteAddItem(
            title = viewModel.title,
            content = viewModel.content,
            onTitleChange = { viewModel.title = it },
            onContentChange = { viewModel.content = it },
            onSaveClick = { viewModel.createNote() }
        )
    }
}
