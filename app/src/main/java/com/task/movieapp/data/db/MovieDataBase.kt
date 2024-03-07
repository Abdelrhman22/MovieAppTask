package com.task.movieapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.movieapp.data.model.MovieInfoEntity
import com.task.movieapp.utils.Constants


@Database(entities = [MovieInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDataBase? = null

        private fun create(context: Context): MovieDataBase {
            return Room.databaseBuilder(
                context,
                MovieDataBase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(mContext: Context): MovieDataBase {
            synchronized(this) {
                var instance: MovieDataBase? = INSTANCE
                if (instance == null)
                    instance = create(context = mContext)
                INSTANCE = instance
                return instance
            }
        }

    }

}