package com.git.gitsearch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.gitsearch.models.response.UserDataResponse
import com.git.gitsearch.models.response.UserRepoResponse
import com.git.gitsearch.network.ApiRepository
import com.git.gitsearch.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    //state flow
    private var _userRepoFlowData = MutableStateFlow<ApiState<UserRepoResponse>>(ApiState.Loading())
    val userRepoFlowData : StateFlow<ApiState<UserRepoResponse>> get() = _userRepoFlowData

    private var _userFlowData = MutableStateFlow<ApiState<UserDataResponse>>(ApiState.Loading())
    val userFlowData : StateFlow<ApiState<UserDataResponse>> get() = _userFlowData

    var githubName by mutableStateOf("")


    fun getUserRepoData(gitUsername: String)
    {
        viewModelScope.launch {
            val result = apiRepository.fetchUserRepoData(gitUsername)

            when(result){
                is ApiState.Success -> {
                    _userRepoFlowData.emit(ApiState.Success(result.data))
                }

                is ApiState.Failure ->{
                    _userRepoFlowData.emit(ApiState.Failure(result.errorMessage.toString()))
                }
                else -> {}
            }
        }
    }


    fun getUserData(gitUsername: String)
    {
        viewModelScope.launch {
            val result = apiRepository.fetchUserData(gitUsername)

            when(result){
                is ApiState.Success ->{
                    _userFlowData.emit(ApiState.Success(result.data))
                }
                is ApiState.Failure ->{
                    _userFlowData.emit(ApiState.Failure(result.errorMessage.toString()))
                }
                else -> {}
            }
        }
    }
}
