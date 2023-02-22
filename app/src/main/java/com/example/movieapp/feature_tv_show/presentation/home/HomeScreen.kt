package com.example.movieapp.feature_tv_show.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.R
import com.example.movieapp.ui.component.PaginationStateHolder
import com.example.movieapp.ui.component.TvShowItem
import com.example.movieapp.ui.component.WarningMessage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onTvShowClick: (Long) -> Unit
) {
    val tvShowState = viewModel.tvShowState.collectAsLazyPagingItems()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start) {
                Image(
                    painter = painterResource(id = R.drawable.tv_mania),
                    contentDescription = "Tv Mania",
                    modifier = Modifier
                        .requiredWidth(120.dp)
                        .padding(top = 12.dp)
                )
            }

        }
        items(
            items = tvShowState,
            key = { it.id }
        ) {
            it?.let { tvShowItem ->
                TvShowItem(
                    tvShow = tvShowItem,
                    modifier = Modifier.requiredHeight(height = 150.dp),
                    onClick = { onTvShowClick(it) }
                )

            }
        }

        item {
            PaginationStateHolder(
                paginationState = tvShowState,
                loadingComponent = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                errorComponent = {
                    WarningMessage(
                        text = stringResource(id = R.string.err_loading_tv_shows),
                        trailingContent = {
                            Text(
                                text = stringResource(id = R.string.lbl_retry),
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .clickable(role = Role.Button) { tvShowState.retry() },
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onBackground
                            )
                        })
                })
        }
    }
}

//@Preview
//@Composable
//fun TvShowPreview(){
//    HomeScreen(viewModel = viewModel())
//}