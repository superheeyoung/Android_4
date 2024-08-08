package com.sparta.fragmentstudy.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.databinding.FragmentLikeUserBinding
import com.sparta.fragmentstudy.SpartaApplication
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {
    companion object {
        var favoriteItem: User? = null
        fun newInstance(item: User?): SecondFragment {
            item?.let {
                favoriteItem = item
            }
            return SecondFragment()
        }
    }

    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter { }
    }

    private val sharedViewModel : FavoriteUserSharedViewModel by activityViewModels()

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory((requireActivity()?.application as SpartaApplication).database)
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
        //initView()
        //Case : Use Room without MVVM
        //getFavoriteUser()
        initView()
        //Case 1 : Use MVVM
        /*searchViewModel.getFavoriteUser()
        getFavoriteUserToDB()*/
        //Case 2 : Use SharedViewModel
        getSharedData()
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchListAdapter
    }

    private fun getSharedData() {
        sharedViewModel.favoriteUserLiveData.observe(viewLifecycleOwner) {
            searchListAdapter.submitList(it)
        }
    }

    //Case 2 : Room 사용해서
    //RecyclerView에 붙이면 됨!
    private fun getFavoriteUser() : List<User> {
        var userList = listOf<User>()
        viewLifecycleOwner.lifecycleScope.launch {
            val userDb = FavoriteUserRoomDatabase.getDatabase(requireContext())
            userList = userDb.userDao().getUsers()
        }
        return userList
    }

    //Case 3 : Use MVVM
    private fun getFavoriteUserToDB(){
        searchViewModel.getFavoriteUserList.observe(viewLifecycleOwner) {
            //TODO : RecyclerView 연결
            searchListAdapter.submitList(it)
            Log.d("debug555", it.toString())
        }
    }
}