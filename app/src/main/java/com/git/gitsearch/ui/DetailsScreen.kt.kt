package com.git.gitsearch.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.git.gitsearch.R
import com.git.gitsearch.models.response.UserRepoResponseItem
import com.git.gitsearch.utility.Routes

@Composable
fun RepoDetails(
    navController: NavHostController,
    result: UserRepoResponseItem?,
    totalForkCount: Int?
) {
    val contextResources = LocalContext.current.resources

    Column {

        Row {
            Text(
                text = contextResources.getString(R.string.id),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = result?.id.toString(),
                modifier = Modifier.padding(10.dp)
            )
        }
        Row {
            Text(
                text = contextResources.getString(R.string.name), fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.name.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.git_url),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.url.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.watchers_count),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.watchers_count.toString(), modifier = Modifier.weight(1f))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.current_repo_fork),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.forks_count.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.open_issues_count),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.open_issues_count.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.repo_type),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.visibility.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.created_at),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.created_at.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.stargazers_count),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.stargazers_count.toString(), modifier = Modifier.padding(10.dp))
        }
        Row {
            Text(
                text = contextResources.getString(R.string.has_downloads),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = result?.has_downloads.toString(), modifier = Modifier.padding(10.dp))
        }
        CheckTotalFork(contextResources, totalForkCount)

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                navController.navigate(Routes.homeScreen)
            },
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth()
        ) {
            Text(contextResources.getString(R.string.back_to_home))
        }
    }
}

@Composable
private fun CheckTotalFork(contextResources: Resources, totalForkCount: Int?) {
    if (totalForkCount != null) {

        Row {
            Text(
                text = contextResources.getString(R.string.Total_user_fork),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            if (totalForkCount >= 5000) {
                Text(
                    text = totalForkCount.toString(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    color = Color.Red
                )
            } else {
                Text(text = totalForkCount.toString(), modifier = Modifier.padding(10.dp))
            }
        }
    }
}