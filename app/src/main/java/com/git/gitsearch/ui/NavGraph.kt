package com.git.gitsearch.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.git.gitsearch.models.response.UserRepoResponseItem
import com.git.gitsearch.utility.Routes

@Composable
fun SetupNavGraph()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.homeScreen, builder = {
        composable(Routes.homeScreen)
        {
            SetupUi(navController)
        }
        composable(Routes.detailScreen)
        {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<UserRepoResponseItem>("data")
            RepoDetails(navController, result)
        }
    })
}