package com.sparta.fragmentstudy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.sparta.fragmentstudy.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    companion object {
        fun newInstance() = SecondFragment()
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
        /*setFragmentResultListener("bundle_key") { requestKey, bundle ->
            txtBundleNum.text = bundle.getInt("bundle_key").toString()
        }*/
        setFragmentResultListener("bundle_key_2") { requestKey, bundle ->
            val user = bundle.getParcelable<User>("bundle_key_2")
            Log.d("debug23323", user!!.name)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}