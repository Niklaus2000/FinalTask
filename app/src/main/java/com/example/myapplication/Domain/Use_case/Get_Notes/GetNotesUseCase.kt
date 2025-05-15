package com.example.myapplication.Domain.Use_case.Get_Notes

import com.example.myapplication.Common.Resource
import com.example.myapplication.Data.Remote.Dto.NoteDto
import com.example.myapplication.Domain.Repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
){

    operator fun invoke(): Flow<Resource<List<NoteDto>>> = flow {
        try {
            emit(Resource.Loading())
            val notes = repository.getNotes()
            emit(Resource.Success(notes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn 't reach server. check your internet"))
        }
    }

}