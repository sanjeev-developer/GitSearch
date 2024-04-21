package com.git.gitsearch.network

import com.git.gitsearch.models.response.UserDataResponse
import com.git.gitsearch.models.response.UserRepoResponse
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchUserData(gitUsername: String): ApiState<UserDataResponse>
    {
        return try {
            val data = apiService.getUserData(gitUsername)
            ApiState.Success(data)
        } catch (e:Exception) {
            ApiState.Failure(e.message.toString())
        }
    }

    suspend fun fetchUserRepoData(gitUsername: String):ApiState<UserRepoResponse>
    {
        return try {
            val data = apiService.getUserRepo(gitUsername)
            ApiState.Success(data)
        } catch (e:Exception) {
            ApiState.Failure(e.message.toString())
        }
    }
}