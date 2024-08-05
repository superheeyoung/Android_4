package com.sparta.fragmentstudy.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.data.database.mockup.userList
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.databinding.FragmentLikeUserBinding
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
}