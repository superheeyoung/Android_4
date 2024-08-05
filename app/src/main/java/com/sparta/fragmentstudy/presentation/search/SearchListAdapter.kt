package com.sparta.fragmentstudy.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.databinding.SearchListImageItemBinding

//DiffUtil : 두 데이터셋을 받아서 차이를 계산 -> 변환된 부분만 파악하여 recyclerView에 반영
class SearchListAdapter(private val onClick: (User) -> Unit) :
    ListAdapter<User, SearchListAdapter.SearchViewHolder>(object :
        DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean =
            oldItem.uId == newItem.uId

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean =
            oldItem == newItem

    }) {
    class SearchViewHolder(val binding: SearchListImageItemBinding,
                           private val onClick: (User) -> Unit ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: User) = with(binding) {
            title.text = item.thumbnailUrl
            thumbnail.load(item.thumbnailUrl)
            favorite.setOnClickListener {
                if(item.isFavorite != favorite.isChecked) {
                    onClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            SearchListImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
