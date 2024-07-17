package com.sparta.fragmentstudy.data

import android.os.Parcelable
import com.sparta.fragmentstudy.presentation.MultiViewEnum
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
