package com.sparta.fragmentstudy.data.database

import android.os.Parcelable
import com.sparta.fragmentstudy.presentation.base.MultiViewEnum
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class UserEntity(
    val name : String,
    val age : Int,
    val cardNumber : String,
    val cardPeriod : String,
    val balance : BigDecimal,
    val cardType : MultiViewEnum
) : Parcelable
