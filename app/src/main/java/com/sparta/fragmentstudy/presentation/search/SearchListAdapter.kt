package com.sparta.fragmentstudy.presentation.search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.databinding.ItemDefaultBinding
import com.sparta.fragmentstudy.databinding.SearchListImageItemBinding
import com.sparta.fragmentstudy.databinding.SearchListVideoItemBinding
import com.sparta.fragmentstudy.presentation.base.MultiCardAdapter.UnknownViewHolder
import com.sparta.fragmentstudy.presentation.base.MultiViewEnum

//DiffUtil : 두 데이터셋을 받아서 차이를 계산 -> 변환된 부분만 파악하여 recyclerView에 반영
class SearchListAdapter(private val onClick: (User) -> Unit) :
    ListAdapter<User, SearchListAdapter.ViewHolder>(object :
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

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {
        abstract fun onBind(item: User)
    }

    class SearchImageViewHolder(
        val binding: SearchListImageItemBinding,
        private val onClick: (User) -> Unit
    ) :
        ViewHolder(binding.root) {
        override fun onBind(item: User) = with(binding) {
            title.text = item.thumbnailUrl
            thumbnail.load(item.thumbnailUrl)
            favorite.setOnClickListener {
                if (item.isFavorite != favorite.isChecked) {
                    onClick(item)
                }
            }
        }
    }

    class SearchVideoViewHolder(
        val binding: SearchListVideoItemBinding,
        private val onClick: (User) -> Unit
    ) :
        ViewHolder(binding.root) {
        override fun onBind(item: User) = with(binding) {
            title.text = item.thumbnailUrl
            thumbnail.load(item.thumbnailUrl)
            favorite.setOnClickListener {
                if (item.isFavorite != favorite.isChecked) {
                    onClick(item)
                }
            }
        }
    }

    class UnknownViewHolder(
        binding: ItemDefaultBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: User) = Unit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val muiltiViewType = SearchType.entries.find { it.viewType == viewType }
        return when (muiltiViewType) {
            SearchType.VIDEO -> {
                SearchVideoViewHolder(
                    SearchListVideoItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )
            }

            SearchType.IMAGE -> {
                SearchImageViewHolder(
                    SearchListImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )
            }

            else -> {
                val binding =
                    ItemDefaultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                UnknownViewHolder(
                    binding
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).type) {
            SearchType.IMAGE -> SearchType.IMAGE.ordinal
            SearchType.VIDEO -> SearchType.VIDEO.ordinal
        }
    }
}
