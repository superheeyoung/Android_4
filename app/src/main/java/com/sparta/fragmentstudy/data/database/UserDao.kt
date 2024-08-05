package com.sparta.fragmentstudy.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sparta.fragmentstudy.presentation.search.User

@Dao
interface UserDao {
    @Query("SELECT * FROM favorite_user_table")
    suspend fun getUsers() : List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteUser(user: User)
}