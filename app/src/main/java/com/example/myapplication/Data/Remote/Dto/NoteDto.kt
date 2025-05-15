package com.example.myapplication.Data.Remote.Dto

import com.example.myapplication.Domain.Model.Note
import com.google.gson.annotations.SerializedName

data class NoteDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String
)

fun NoteDto.toNote(): Note = Note(
    id = id,
    title = title,
    content = content
)