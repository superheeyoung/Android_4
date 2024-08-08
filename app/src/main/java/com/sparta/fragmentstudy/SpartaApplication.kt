package com.sparta.fragmentstudy

import android.app.Application
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase

class SpartaApplication : Application() {
    val database by lazy { FavoriteUserRoomDatabase.getDatabase(this) }
}