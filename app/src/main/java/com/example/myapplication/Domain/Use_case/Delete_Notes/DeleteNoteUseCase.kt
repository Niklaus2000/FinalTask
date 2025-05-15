package com.example.myapplication.Domain.Use_case.Delete_Notes

import com.example.myapplication.Common.Resource
import com.example.myapplication.Domain.Repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(id: String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.deleteNote(id)
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("წაშლა ვერ მოხერხდა"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("სერვერის შეცდომა: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("ინტერნეტის პრობლემა: ${e.localizedMessage}"))
        }
    }
}