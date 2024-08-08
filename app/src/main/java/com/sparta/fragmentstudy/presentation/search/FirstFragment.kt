package com.sparta.fragmentstudy.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.databinding.FragmentSearchBinding
import com.sparta.fragmentstudy.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {
    companion object {
        fun newInstance() = FirstFragment()
    }

    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter { user ->
            //Case 1) Use Listnener
            val favoriteUser = user.copy(isFavorite = true)
            likeUserEvent?.likeUser(favoriteUser)
            //Case 2) Use Room
            //insertFavoriteUser(user)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        likeUserEvent = context as LikeUserEvent
    }

    private fun getUserImageList(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            val userList = RetrofitClient.searchUserImageList.getSearchImage(query).documents.orEmpty()
            searchListAdapter.submitList(toUser(userList))
        }
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchListAdapter
        editQuery.doAfterTextChanged { query ->
            getUserImageList(query.toString())
        }
    }

    private fun insertFavoriteUser(user : User) {
        viewLifecycleOwner.lifecycleScope.launch {
            val userDb = FavoriteUserRoomDatabase.getDatabase(requireContext())
            userDb.userDao().insertFavoriteUser(user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
