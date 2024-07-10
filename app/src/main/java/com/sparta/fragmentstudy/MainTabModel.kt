package com.sparta.fragmentstudy

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

data class MainTabModel(
    val fragment : Fragment,
    @StringRes val title : Int
)
