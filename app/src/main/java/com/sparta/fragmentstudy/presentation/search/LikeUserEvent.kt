package com.sparta.fragmentstudy.presentation.search

import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse

interface LikeUserEvent {
    fun likeUser(item : User)
}