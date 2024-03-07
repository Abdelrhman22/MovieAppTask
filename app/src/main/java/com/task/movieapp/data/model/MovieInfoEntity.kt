package com.task.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.movieapp.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME)
data class MovieInfoEntity(
    val title: String? = null,
    val voteAverage: Double? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val genreIds: ArrayList<Int> = arrayListOf(),
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uniqueId: Int = 0
}