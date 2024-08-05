package com.sparta.fragmentstudy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sparta.fragmentstudy.presentation.search.User

@Database(entities = [User::class], version = 1)
abstract class FavoriteUserRoomDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteUserRoomDatabase ?= null

        fun getDatabase(context : Context) : FavoriteUserRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUserRoomDatabase::class.java,
                    "favoriteUserDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}