package com.sparta.fragmentstudy.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.databinding.FragmentSearchBinding
import com.sparta.fragmentstudy.SpartaApplication
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {
    companion object {
        fun newInstance() = FirstFragment()
    }

    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter { user ->
            val favoriteUser = user.copy(isFavorite = true)
            //Case 1) Use Listnener
            /*val favoriteUser = user.copy(isFavorite = true)
            likeUserEvent?.likeUser(favoriteUser)*/
            //Case 2) Use Room
            //insertFavoriteUser(user)
            //Case 3) Use MVVM
            searchViewModel.saveFavoriteUser(favoriteUser)
            //Case 4) Use SharedViewModel
            //sharedViewModel.setFavoriteList(favoriteUser)
        }
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory((requireActivity()?.application as SpartaApplication).database)
    }

    private val sharedViewModel : FavoriteUserSharedViewModel by activityViewModels()

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
        initViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        likeUserEvent = context as LikeUserEvent
    }

    private fun initViewModel() {
        searchViewModel.getSearchList.observe(viewLifecycleOwner) {
            searchListAdapter.submitList(it)
            Log.d("debug2323",it.map {
                it.type.name
            }.toString())
        }
    }


    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchListAdapter
        editQuery.doAfterTextChanged { query ->
            searchViewModel.getImageList(query.toString())
        }
    }

    //TODO : View에서 Room 호출하기
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
