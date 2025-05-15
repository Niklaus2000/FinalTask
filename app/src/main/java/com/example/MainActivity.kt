package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Presentation.Screen
import com.example.myapplication.Presentation.note_create.NoteCreateScreen
import com.example.myapplication.Presentation.note_list.NoteListScreen
import com.example.myapplication.Presentation.note_update.NoteUpdateScreen
import com.example.myapplication.Presentation.ui.Theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteListScreen.route
                    ) {
                        composable(
                            route = Screen.NoteListScreen.route
                         ) {
                            NoteListScreen(navController)
                        }
                        composable(
                            route = Screen.NoteUpdateScreen.route + "/{noteId}"
                        ) { backStackEntry ->
                            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
                            NoteUpdateScreen(navController = navController, noteId = noteId)
                        }

                        composable(
                            route = Screen.NoteCreateScreen.route
                        ) {
                            NoteCreateScreen(navController)
                        }
                    }
                }
            }
        }
    }
}