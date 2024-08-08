package com.sparta.fragmentstudy

import android.app.Application
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase

class SpartaApplication : Application() {
    //applicationContext : application에 관한 정보를 담음
    //room은 어플리케이션 실행되는 동안 지속적으로 사용되는 싱글톤 객체이기 때문에 applicationContext 사용
    val database by lazy { FavoriteUserRoomDatabase.getDatabase(this) }
    val applicationContext by lazy { this }
}