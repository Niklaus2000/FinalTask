package com.example.myapplication.Presentation.note_create


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Common.Resource
import com.example.myapplication.Domain.Model.NoteRequest
import com.example.myapplication.Domain.Use_case.Create_Notes.CreateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteCreateViewModel @Inject constructor(
    private val addNoteUseCase: CreateNoteUseCase
) : ViewModel() {

    var title by mutableStateOf("")
    var content by mutableStateOf("")

    private val _state = mutableStateOf(NoteCreateState())
    val state: State<NoteCreateState> = _state

    fun createNote() {
        viewModelScope.launch {
            addNoteUseCase(NoteRequest(title, content)).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = NoteCreateState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = NoteCreateState(isSuccess = true)
                        Log.d("NOTE_ADD", "POST წარმატებით: title=$title, content=$content")
                    }
                    is Resource.Error -> {
                        _state.value = NoteCreateState(error = result.message ?: "დაფიქსირდა შეცდომა")
                        Log.e("NOTE_ADD", "შეცდომა: ${result.message}")
                    }
                }
            }
        }
    }
}