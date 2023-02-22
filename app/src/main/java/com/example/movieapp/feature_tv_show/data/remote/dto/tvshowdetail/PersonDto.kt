package com.example.movieapp.feature_tv_show.data.remote.dto.tvshowdetail

import android.os.Parcelable
import com.example.movieapp.feature_tv_show.data.remote.dto.tvshow.ImageDto
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.Person
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonDto(
    val id: Long,
    val image: ImageDto?,
    val name: String,
    val url: String,
    val updated: Long
) : Parcelable {

    fun toPerson(): Person {
        return Person(
            id,
            image?.toImage(),
            name,
            updated,
            url
        )
    }

//    fun toPersonEntity(): PersonEntity {
//        return PersonEntity(
//            personId = id,
//            name = name,
//            url = url,
//            images = image?.let { listOf(it.medium, it.original) } ?: emptyList(),
//            updated = updated
//        )
//    }
}