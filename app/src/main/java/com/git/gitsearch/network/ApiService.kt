package com.git.gitsearch.network

import com.git.gitsearch.models.response.UserDataResponse
import com.git.gitsearch.models.response.UserRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{userId}")
    suspend fun getUserData(@Path("userId") gitUsername: String?): UserDataResponse

    @GET("{userId}/repos")
    suspend fun getUserRepo(@Path("userId") user: String?): UserRepoResponse
}