package com.example.movieapp.feature_tv_show.presentation.show

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.component.BackButton
import com.example.movieapp.ui.component.Chip
import com.example.movieapp.ui.component.NetworkImage
import com.example.movieapp.ui.component.TopBar
import com.example.movieapp.ui.component.WarningMessage
import com.example.movieapp.ui.theme.Gray400
import com.example.movieapp.ui.theme.RedErrorLight
import com.example.movieapp.util.DEFAULT_ACTOR_IMAGE_URL
import com.example.movieapp.util.removeHtmlTags
import com.gowtham.ratingbar.RatingBar

@Composable
fun TvShowDetailScreen(
    viewModel: TvShowDetailViewModel,
    onBackPress: () -> Unit,
    onWatchNowClick: (String) -> Unit
) {

    val isLoading by viewModel.isLoading
        .collectAsState()

    val tvShowDetail by viewModel.tvShowState
        .collectAsState()

    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        if (isLoading) {
            TopBar(
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 18.dp),
                leadingIcon = {
                    BackButton(onBackPress = { onBackPress() })
                }
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            tvShowDetail?.let { tvShow ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = screenHeight * 0.8f)
                ) {
                    NetworkImage(
                        url = tvShow.image.original,
                        alignment = Alignment.TopCenter,
                        crossFade = 1000,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        onLoading = { CircularProgressIndicator() },
                        onError = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_circle_info_solid),
                                contentDescription = "",
                                tint = RedErrorLight
                            )
                        }
                    )
                    TopBar(
                        modifier = Modifier.padding(vertical = 30.dp, horizontal = 18.dp),
                        leadingIcon = {
                            BackButton(onBackPress = { onBackPress() })
                        }
                    )
                }

                Text(
                    text = tvShow.name,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 10.dp, bottom = 5.dp),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    text = tvShow.premiered.split("-").first(),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primaryVariant
                )

                RatingBar(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    inactiveColor = Gray400,
                    value = tvShow.rating.average.toFloat() / 2,
                    size = 20.dp,
                    onValueChange = {},
                    onRatingChanged = {}
                )

                Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    tvShow.genres.forEach { genre ->
                        Chip(modifier = Modifier.padding(horizontal = 5.dp)) {
                            Text(
                                text = genre,
                                modifier = Modifier.padding(all = 5.dp),
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = stringResource(id = R.string.summary),
                    modifier = Modifier.padding(all = 10.dp),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    text = tvShow.summary.removeHtmlTags(),
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = stringResource(id = R.string.cast_and_crew),
                    modifier = Modifier.padding(all = 10.dp),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    items(items = tvShow.actors) { actor ->
                        Column(
                            modifier = Modifier
                                .requiredHeight(height = 200.dp)
                                .requiredWidth(width = 160.dp)
                        ) {
                            NetworkImage(
                                url = actor.image?.medium ?: DEFAULT_ACTOR_IMAGE_URL,
                                modifier = Modifier.requiredHeight(height = 160.dp)
                                    .clip(shape = MaterialTheme.shapes.large),
                                crossFade = 1000,
                                contentScale = ContentScale.Crop,
                                onLoading = { CircularProgressIndicator() },
                                onError = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_circle_info_solid),
                                        contentDescription = "",
                                        tint = RedErrorLight
                                    )
                                }
                            )
                            Text(
                                text = actor.name,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                color = MaterialTheme.colors.primary,
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }

                Button(
                    onClick = { onWatchNowClick(tvShow.url) },
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.watch_now)
                    )
                }

            } ?: WarningMessage(text = stringResource(id = R.string.err_tv_show_not_found))
        }
    }
}