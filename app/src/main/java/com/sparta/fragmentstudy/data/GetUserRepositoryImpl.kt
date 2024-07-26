package com.sparta.fragmentstudy.data

import com.sparta.fragmentstudy.presentation.UserRepository
/*
* 비지니스 로직을 표현하는데 필요한 DataSource가 몇개 든 client쪽에서는 이를 알 필요가 없다.
* DataSource의 변경이 발생하더라도 repository 외부 layer로 전파되지 않는다.
* 중간에 추상화된 Repository 클래스를 두어 모듈화가 명확해지고, 유지보수성이 향상된다.
* interface인 repository를 실제로 동작하는 구조로 만든 클래스 (repository interface는 메소드만 정의되어 있음)
* */
class GetUserRepositoryImpl(
    private val cacheDataSource: CacheDataSource
) : UserRepository {
    override fun getUserList(): List<UserEntity> {
        return cacheDataSource.getUserList()
    }
}