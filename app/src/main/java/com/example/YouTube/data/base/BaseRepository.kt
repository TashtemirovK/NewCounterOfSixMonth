package com.example.YouTube.data.base

import androidx.lifecycle.liveData
import com.example.YouTube.data.utils.Constants
import com.example.YouTube.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

abstract class BaseRepository {
    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            val response = request()

            if (response.body() != null && response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(Constants.UNKNOWN_ERROR))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: Constants.UNKNOWN_ERROR))
        }
    }

}
