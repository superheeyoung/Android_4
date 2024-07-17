package com.sparta.fragmentstudy.data


//TODO : 방법 1) synchronized Singleton Pattern
class CacheDataSource {
    companion object {
        private var INSTANCE: CacheDataSource? = null
        fun getCacheDataSource(): CacheDataSource {
            return synchronized(CacheDataSource::class) {
                val newInstance = INSTANCE ?: CacheDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    fun getUserList(): List<UserEntity> {
        return userList()
    }
}

//TODO : 방법 2) object
/*
object CacheDataSource {
    fun getUserList() : List<UserEntity> {
        return userList()
    }
}
*/
