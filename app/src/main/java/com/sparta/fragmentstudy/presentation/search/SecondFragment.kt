package com.sparta.fragmentstudy.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.databinding.FragmentLikeUserBinding

class SecondFragment : Fragment() {
    companion object {
        var favoriteItem: ImageDocumentResponse? = null
        fun newInstance(item: ImageDocumentResponse?): SecondFragment {
            item?.let {
                favoriteItem = item
            }
            return SecondFragment()
        }
    }

    private var _binding: FragmentLikeUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikeUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding.favoriteLayout) {
        title.text = favoriteItem?.thumbnailUrl
        thumbnail.load(favoriteItem?.thumbnailUrl)
    }
}