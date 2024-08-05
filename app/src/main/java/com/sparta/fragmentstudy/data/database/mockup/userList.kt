package com.sparta.fragmentstudy.data.database.mockup

import com.sparta.fragmentstudy.presentation.base.MultiViewEnum

fun userList(): List<UserEntity> {
    return listOf(
        UserEntity(
            "ggilggil monster yongchan",
            24,
            "2323-4343-5555",
            "02/11",
            332434.toBigDecimal(),
            MultiViewEnum.BLUE
        ),
        UserEntity(
            "jammin-school jiwon",
            18,
            "12323-4343-5555",
            "02/11",
            12324.toBigDecimal(),
            MultiViewEnum.ORANGE
        ),
        UserEntity(
            "psychopath kangjin",
            51,
            "768768-4343-5555",
            "04/11",
            64455.toBigDecimal(),
            MultiViewEnum.LIGHTBLUE
        ),
        UserEntity(
            "heeyoung tutor",
            21,
            "34768-4343-5555",
            "05/11",
            11233.toBigDecimal(),
            MultiViewEnum.BLUE
        )
    )
}