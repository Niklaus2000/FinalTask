package com.example.myapplication.Data.Remote.Dto

import com.example.myapplication.Domain.Model.NoteRequest
import com.google.gson.annotations.SerializedName

data class NoteRequestDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String
)

fun NoteRequest.toDto(): NoteRequestDto {
    return NoteRequestDto(
        title = title,
        content = content
    )
}
