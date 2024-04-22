package com.git.gitsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.git.gitsearch.models.response.UserRepoResponse
import com.git.gitsearch.models.response.UserRepoResponseItem
import com.git.gitsearch.network.ApiState
import com.git.gitsearch.utility.Routes
import com.git.gitsearch.viewmodel.HomeViewModel

@Composable
fun SetRepoList(homeViewModel: HomeViewModel, navController: NavController) {

    val result = homeViewModel.userRepoFlowData.collectAsState().value
    when (result) {
        is ApiState.Loading -> {

        }

        is ApiState.Success -> {
            SetLazyList(result.data, navController, calculateTotalForks(result.data))
        }

        is ApiState.Failure -> {
            println("failure ${result.errorMessage}")
        }
    }
}

@Composable
fun SetLazyList(data: UserRepoResponse?, navController: NavController, calculateTotalForks: Int) {
    LazyColumn {
        if (data != null) {
            items(data.size) {
                LazyListItem(data[it], navController, calculateTotalForks)
            }
        }
    }
}

@Composable
fun LazyListItem(
    userRepoResponseItem: UserRepoResponseItem,
    navController: NavController,
    calculateTotalForks: Int
) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("data", userRepoResponseItem)
                    set("total_no_forks", calculateTotalForks)
                }

                navController.navigate(Routes.detailScreen)
            },

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
    )
    {
        Column {
            Text(
                text = userRepoResponseItem.name ?: "n/a",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = userRepoResponseItem.description ?: "n/a",
                color = Color.Black,
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp
                )
            )
        }
    }
}

private fun calculateTotalForks(userRepoResponse: UserRepoResponse?): Int {
    var totalCount = 0
    if (userRepoResponse != null) {
        for (i in userRepoResponse) {
            totalCount += i.forks_count
        }
    }
    return totalCount
}


