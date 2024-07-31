package com.sparta.fragmentstudy.presentation.base

import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val _events = MutableSharedFlow<Bundle>()
    val events = _events.asSharedFlow()

    suspend fun produceEvent(event : Bundle) {
        _events.emit(event)
    }
}