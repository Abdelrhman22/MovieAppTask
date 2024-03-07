package com.task.movieapp.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsConverter {

    @TypeConverter
    fun convertJsonToList(json: String?): ArrayList<Int>? {
        if (json.isNullOrEmpty()) {
            return null
        }
        val type = object : TypeToken<ArrayList<Int>>() {}
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun convertListToJson(list: ArrayList<Int>?): String? {
        if (list == null)
            return null
        return Gson().toJson(list)
    }

}