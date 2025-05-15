package com.example.myapplication.Presentation.note_update


import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.Presentation.note_update.components.NoteUpdateForm

@Composable
fun NoteUpdateScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteUpdateViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        NoteUpdateForm(
            title = viewModel.title,
            onTitleChange = { viewModel.title = it },
            content = viewModel.content,
            onContentChange = { viewModel.content = it },
            onUpdateClick = {
                viewModel.updateNote(noteId)
            }
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        if (state.isSuccess) {
            LaunchedEffect(Unit) {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("refresh", true)

                navController.popBackStack()
            }
        }
    }
}
