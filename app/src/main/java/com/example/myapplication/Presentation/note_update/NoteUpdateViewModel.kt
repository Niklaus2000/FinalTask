package com.example.myapplication.Presentation.note_update

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Common.Resource
import com.example.myapplication.Domain.Model.NoteRequest
import com.example.myapplication.Domain.Use_case.Update_Notes.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

@HiltViewModel
class NoteUpdateViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    var title by mutableStateOf("")
    var content by mutableStateOf("")

    private val _state = mutableStateOf(NoteUpdateState())
    val state: State<NoteUpdateState> = _state

    fun updateNote(noteId: String) {
        updateNoteUseCase(noteId, NoteRequest(title, content)).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = NoteUpdateState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = NoteUpdateState(isSuccess = true)
                }
                is Resource.Error -> {
                    _state.value = NoteUpdateState(error = result.message ?: "შეცდომა განახლებისას")
                }
            }
        }.launchIn(viewModelScope)
    }
}
