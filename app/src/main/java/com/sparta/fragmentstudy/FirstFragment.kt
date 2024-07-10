package com.sparta.fragmentstudy

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.sparta.fragmentstudy.databinding.FragmentFirstBinding
import kotlinx.parcelize.Parcelize

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    companion object {
        fun newInstance() = FirstFragment()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        buttonNext.setOnClickListener {
            //TODO step 1
            //setFragmentResult("bundle_key",bundleOf("bundle_key" to 88))
            //TODO step 2
            sendObject()
        }
    }

    private fun sendObject() {
        val user = User("sparta", 4)
        setFragmentResult("bundle_key_2", bundleOf("bundle_key_2" to user))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

