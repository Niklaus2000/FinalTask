package com.example.myapplication.Data.Remote.Dto

import com.example.myapplication.Domain.Model.NoteDelete
import com.google.gson.annotations.SerializedName

data class NoteDeleteDto(
    @SerializedName("success")
    val success: Boolean
)

fun NoteDeleteDto.toNoteDelete(): NoteDelete = NoteDelete(success)
