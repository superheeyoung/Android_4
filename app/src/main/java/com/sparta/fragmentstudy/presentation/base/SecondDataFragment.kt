package com.sparta.fragmentstudy.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sparta.fragmentstudy.data.database.UserEntity
import com.sparta.fragmentstudy.databinding.FragmentSecondBinding

class SecondDataFragment : Fragment() {
    companion object {
        fun newInstance(bundle: Bundle): SecondDataFragment {
            val secondDataFragment = SecondDataFragment()
            secondDataFragment.arguments = bundle
            return secondDataFragment
        }
    }

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        val user = arguments?.getParcelable<UserEntity>("user")
        txtTitle.text = user?.name
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}