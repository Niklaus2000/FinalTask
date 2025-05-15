package com.example.myapplication.Presentation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.Presentation.Screen
import com.example.myapplication.Presentation.note_list.components.NoteListItem


@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value

    LaunchedEffect(currentBackStackEntry) {
        val shouldRefresh = currentBackStackEntry
            ?.savedStateHandle
            ?.get<Boolean>("refresh") ?: false

        if (shouldRefresh) {
            viewModel.getNotes()
            currentBackStackEntry?.savedStateHandle?.set("refresh", false)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.notes, key = { it.id }) { note ->
                NoteListItem(
                    note = note,
                    onItemClick = {
                        navController.navigate(Screen.NoteUpdateScreen.route + "/${note.id}")
                    },
                    onDelete = { noteId ->
                        viewModel.deleteNote(noteId)
                    }
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.NoteCreateScreen.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "დამატება",
                tint = Color.White
            )
        }
    }
}



