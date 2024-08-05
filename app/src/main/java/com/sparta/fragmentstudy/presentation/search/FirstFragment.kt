package com.sparta.fragmentstudy.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sparta.fragmentstudy.databinding.FragmentSearchBinding
import com.sparta.fragmentstudy.network.RetrofitClient
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {
    companion object {
        fun newInstance() = FirstFragment()
    }

    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter { user ->
            val favoriteUser = user.copy(isFavorite = true)
            likeUserEvent?.likeUser(favoriteUser)
        }
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var likeUserEvent : LikeUserEvent? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    //fragment와 activity간의 통신을 하기 위해 context 주입
    //라이프사이클 초기단계에서 리스너 설정
    override fun onAttach(context: Context) {
        super.onAttach(context)
        likeUserEvent = context as LikeUserEvent
    }

    private fun getUserImageList(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            val userList = RetrofitClient.searchUserImageList.getSearchImage(query)
            searchListAdapter.submitList(userList.documents)
        }
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchListAdapter
        editQuery.doAfterTextChanged { query ->
            getUserImageList(query.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}