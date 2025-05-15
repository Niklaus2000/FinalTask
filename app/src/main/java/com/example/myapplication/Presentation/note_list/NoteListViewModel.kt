package com.example.myapplication.Presentation.note_list


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Common.Resource
import com.example.myapplication.Data.Remote.Dto.toNote
import com.example.myapplication.Domain.Use_case.Delete_Notes.DeleteNoteUseCase
import com.example.myapplication.Domain.Use_case.Get_Notes.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NoteListState())
    val state: State<NoteListState> = _state

    init {
        getNotes()
    }

    fun getNotes() {
        getNotesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NoteListState(notes = result.data?.map { it.toNote() } ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NoteListState(error = result.message ?: "შეცდომა")
                }
                is Resource.Loading -> {
                    _state.value = NoteListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId).collect { result ->
                if (result is Resource.Success) {
                    getNotes()
                }
            }
        }
    }
}


