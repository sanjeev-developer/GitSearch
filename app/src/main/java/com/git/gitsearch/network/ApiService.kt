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

//    // Retrofit API interface
//    interface ApiService {
//        @POST("endpoint") // Replace with your actual endpoint
//        fun sendData(@Body body: PostRequestBody): Call<PostResponse>
//    }
//
//    // Data class representing the body of your POST request
//    data class PostRequestBody(
//        val key1: String,
//        val key2: String
//    )
}