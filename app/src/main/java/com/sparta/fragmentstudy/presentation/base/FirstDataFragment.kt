package com.sparta.fragmentstudy.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sparta.fragmentstudy.data.database.CacheDataSource
import com.sparta.fragmentstudy.data.database.GetUserRepositoryImpl
import com.sparta.fragmentstudy.data.database.UserEntity
import com.sparta.fragmentstudy.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch


class FirstDataFragment : Fragment() {
    companion object {
        fun newInstance() = FirstDataFragment()
    }

    private val userRepository = GetUserRepositoryImpl(CacheDataSource.getCacheDataSource())
    private lateinit var userEntityList : List<UserEntity>

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val multiCardAdapter: MultiCardAdapter by lazy {
        MultiCardAdapter { user ->
            adapterOnClick(user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUser()
        initView()
    }


    private fun initView() = with(binding) {
        buttonNext.setOnClickListener {
            lifecycleScope.launch {
                EventBus.produceEvent(makeBundleUserObject())
            }
        }
        rvItem.adapter = multiCardAdapter
        multiCardAdapter.userList = userEntityList
    }

    //TODO bundle로 object 넘기기
    private fun makeBundleUserObject(): Bundle {
        return Bundle().apply {
            putParcelable("user", getUser())
        }
    }

    private fun getUser(): UserEntity {
        userEntityList = userRepository.getUserList()
        //TODO : use Singleton Class Instance
        return userRepository.getUserList().first()
        //TODO : use Object
        //return CacheDataSource.getUserList()
    }

    private fun adapterOnClick(user: UserEntity) {
        Toast.makeText(requireContext(), "유저 이름 : " + user.name, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}