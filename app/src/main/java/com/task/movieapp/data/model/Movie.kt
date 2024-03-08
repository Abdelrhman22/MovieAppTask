package com.task.movieapp.data.model

import com.google.gson.annotations.SerializedName
import com.task.movieapp.utils.Constants
import java.io.Serializable

data class Movie(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("media_type") val mediaType: String? = null,
    @SerializedName("genre_ids") val genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
) : Serializable {

    fun asEntity(): MovieInfoEntity {
        return MovieInfoEntity(
            title = title,
            voteAverage = voteAverage,
            backdropPath = Constants.BACKDROP_URL + backdropPath,
            posterPath = Constants.POSTER_URL + posterPath,
            releaseDate = releaseDate,
            overview = overview,
            genreIds = genreIds
        )
    }
}