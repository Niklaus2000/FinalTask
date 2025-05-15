package com.example.myapplication.Domain.Repository

import com.example.myapplication.Data.Remote.Dto.NoteDeleteDto
import com.example.myapplication.Data.Remote.Dto.NoteDto
import com.example.myapplication.Domain.Model.NoteRequest
import retrofit2.Response

interface NoteRepository {
    suspend fun getNotes(): List<NoteDto>
    suspend fun createNote(note: NoteRequest): Response<Unit>
    suspend fun updateNote(id: String, note: NoteRequest): Response<Unit>
    suspend fun deleteNote(id: String): Response<NoteDeleteDto>

}