package com.sparta.fragmentstudy.presentation.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//viewholder 추상화 해서 상속받은 클래스는 동일한 메서드를 구현해서 각각의 viewholder에 맞는 기능 추가하면 됨
abstract class ViewHolder<T>(
    root: View
) : RecyclerView.ViewHolder(root) {
    abstract fun onBind(item: T)
}