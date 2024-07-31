package com.sparta.fragmentstudy.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.databinding.SearchListImageItemBinding

class SearchListAdapter(private val onClick: (ImageDocumentResponse) -> Unit) :
    ListAdapter<ImageDocumentResponse, SearchListAdapter.SearchViewHolder>(object :
        DiffUtil.ItemCallback<ImageDocumentResponse>() {
        override fun areItemsTheSame(
            oldItem: ImageDocumentResponse,
            newItem: ImageDocumentResponse
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(
            oldItem: ImageDocumentResponse,
            newItem: ImageDocumentResponse
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()

    }) {
    class SearchViewHolder(val binding: SearchListImageItemBinding,
                           private val onClick: (ImageDocumentResponse) -> Unit ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ImageDocumentResponse) = with(binding) {
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
