package com.example.movieapp.feature_tv_show.data.remote.dto.tvshowdetail

import android.os.Parcelable
import com.example.movieapp.feature_tv_show.data.remote.dto.tvshow.ImageDto
import com.example.movieapp.feature_tv_show.data.remote.dto.tvshow.RatingDto
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetailDto(
    val id: Long,
    val url: String,
    val name: String,
    val genres: List<String>,
    val premiered: String,
    val officialSite: String?,
    val rating: RatingDto,
    val image: ImageDto,
    val summary: String,
    val updated: Long,
    @SerializedName("_embedded") val embedded: EmbeddedDto,
): Parcelable{

    fun toTvShowDetail(): TvShowDetail {
        return TvShowDetail(
            id = id,
            url = url,
            name = name,
            genres = genres,
            premiered = premiered,
            officialSite = officialSite,
            rating = rating.toRating(),
            image =  image.toImage(),
            summary = summary,
            updated = updated,
            actors = embedded.cast.map { it.person.toPerson() }
        )
    }

}