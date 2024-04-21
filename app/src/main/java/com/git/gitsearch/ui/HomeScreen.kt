package com.git.gitsearch.ui

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.git.gitsearch.R
import com.git.gitsearch.models.response.UserDataResponse
import com.git.gitsearch.network.ApiState
import com.git.gitsearch.viewmodel.HomeViewModel


@Composable
fun SetupUi(navController: NavController) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val senData = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth()) {
        val contextResources = LocalContext.current.resources
        Row(modifier = Modifier.fillMaxWidth()) {
            val useText = remember { mutableStateOf("") }
            OutlinedTextField(
                value = useText.value,
                onValueChange = { useText.value = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(all = 10.dp),
                label = { Text(contextResources.getString(R.string.enter_user_hint)) }
            )
            Button(
                onClick = {
                    keyboardController?.hide()
                    homeViewModel.getUserRepoData(useText.value.trimEnd())
                    homeViewModel.getUserData(useText.value.trimEnd())
                    senData.value = true
                },
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        end = 10.dp,
                    )
            ) {
                Text(contextResources.getString(R.string.search))
            }
        }

        if (senData.value) {
            SetData(homeViewModel, contextResources)
            SetRepoList(homeViewModel, navController)
        }
    }
}

@Composable
fun SetData(homeViewModel: HomeViewModel, contextResources: Resources) {
    val result = homeViewModel.userFlowData.collectAsState().value
    when (result) {
        is ApiState.Loading -> {

        }

        is ApiState.Success -> {
            InflateUi(result.data, contextResources)

        }

        is ApiState.Failure -> {
            println("failure ${result.errorMessage}")
        }
    }
}

@Composable
fun InflateUi(data: UserDataResponse?, contextResources: Resources) {
    Image(
        painter = rememberAsyncImagePainter(data?.avatar_url),
        contentDescription = contextResources.getString(R.string.user_image),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .width(150.dp)
            .padding(all = 10.dp)
    )

    Text(
        text = data?.name.toString(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
    )
}