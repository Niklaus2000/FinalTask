package com.example.myapplication.Domain.Use_case.Create_Notes



import com.example.myapplication.Common.Resource
import com.example.myapplication.Domain.Model.NoteRequest
import com.example.myapplication.Domain.Repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(note: NoteRequest): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.createNote(note)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("ჩანაწერის დამატება ვერ მოხერხდა"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("სერვერის შეცდომა: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("კავშირი არ არის: ${e.localizedMessage}"))
        }
    }
}