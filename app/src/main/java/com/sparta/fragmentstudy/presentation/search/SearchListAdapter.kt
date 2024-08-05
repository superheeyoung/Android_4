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
class SearchListAdapter(private val onClick: (ImageDocumentResponse) -> Unit) :
    ListAdapter<ImageDocumentResponse, SearchListAdapter.SearchViewHolder>(object :
        DiffUtil.ItemCallback<ImageDocumentResponse>() {
        override fun areItemsTheSame(
            oldItem: ImageDocumentResponse,
            newItem: ImageDocumentResponse
        ): Boolean =
            //unique key 없을 시 hashcode(해시 알고리즘에 생성된 정수 값)로 비교
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
