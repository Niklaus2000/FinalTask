package com.example.myapplication.Data.Remote

import com.example.myapplication.Data.Remote.Dto.NoteDeleteDto
import com.example.myapplication.Data.Remote.Dto.NoteDto
import com.example.myapplication.Data.Remote.Dto.NoteRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface NoteApiService {

    @GET("?path=list")
    suspend fun getNotes(): List<NoteDto>

    @POST("?path=create")
    suspend fun createNote(
        @Body note: NoteRequestDto
    ): Response<Unit>

    @PUT("?path=update")
    suspend fun updateNote(
        @Query("id") id: String ,
        @Body note: NoteRequestDto
    ): Response<Unit>

    @DELETE("?path=delete")
    suspend fun deleteNote(
        @Query("id") id: String
    ): Response<NoteDeleteDto>
}