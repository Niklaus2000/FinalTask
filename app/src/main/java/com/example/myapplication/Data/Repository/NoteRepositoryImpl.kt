package com.example.myapplication.Data.Repository

import com.example.myapplication.Data.Remote.Dto.NoteDeleteDto
import com.example.myapplication.Data.Remote.Dto.NoteDto
import com.example.myapplication.Data.Remote.Dto.toDto
import com.example.myapplication.Data.Remote.NoteApiService
import com.example.myapplication.Domain.Model.NoteRequest
import com.example.myapplication.Domain.Repository.NoteRepository
import retrofit2.Response
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val api: NoteApiService
) : NoteRepository {
    override suspend fun getNotes(): List<NoteDto> {
        return api.getNotes()
    }

    override suspend fun createNote(note: NoteRequest): Response<Unit> {
        return api.createNote(note.toDto())
    }

    override suspend fun updateNote(id: String , note: NoteRequest): Response<Unit> {
        return api.updateNote(id, note.toDto())
    }

    override suspend fun deleteNote(id: String): Response<NoteDeleteDto> {
        return api.deleteNote(id)
    }
}