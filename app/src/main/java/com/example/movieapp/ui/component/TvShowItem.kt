package com.example.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.feature_tv_show.domain.model.tvshow.Image
import com.example.movieapp.feature_tv_show.domain.model.tvshow.Rating
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.ui.theme.*
import com.gowtham.ratingbar.RatingBar

@Composable
fun TvShowItem(
    tvShow: TvShow,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(tvShow.id) },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = 0.35F),
            shape = MaterialTheme.shapes.large,
            elevation = 3.dp
        ) {
            NetworkImage(
                url = tvShow.image.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onLoading = { CircularProgressIndicator() },
                onError = {
                    Icon(
                        painter = painterResource(id = com.example.movieapp.R.drawable.ic_circle_info_solid),
                        contentDescription = "",
                        tint = RedErrorLight
                    )

                }
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = tvShow.name,
            color = RedErrorLight,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
            )
            RatingBar(modifier = Modifier.padding(vertical = 8.dp),
            inactiveColor = Gray400,
            value = tvShow.rating.average.toFloat() / 2,
                onValueChange = {},
                onRatingChanged = {}
            )
            Row() {
                tvShow.genres.forEach{ genre ->

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
        }
    }
}

@Composable
@Preview
fun PreviewTvShowItem() {
    TvShowItem(
        tvShow = TvShow(
            id = 1,
            name = "askar",
            genres = listOf("Hallo"),
            rating = Rating(100.0),
            image = Image("medium", original = "original"),
            updated = 100L
        ), Modifier,
        onClick = {}
    )
}