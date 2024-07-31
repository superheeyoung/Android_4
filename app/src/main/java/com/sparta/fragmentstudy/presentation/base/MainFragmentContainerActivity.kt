package com.sparta.fragmentstudy.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.sparta.fragmentstudy.R
import com.sparta.fragmentstudy.databinding.ActivityMainFragmentContainerBinding
import kotlinx.coroutines.launch

class MainFragmentContainerActivity : AppCompatActivity() {
    private val binding: ActivityMainFragmentContainerBinding by lazy {
        ActivityMainFragmentContainerBinding.inflate(layoutInflater)
    }

    private lateinit var userBundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFragment()

        lifecycleScope.launch {
            EventBus.events.collect { bundle ->
                userBundle = bundle
            }
        }
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FirstDataFragment.newInstance())
            .commit()

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text.toString()) {
                    "first" -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_view, FirstDataFragment.newInstance())
                            .commit()
                    }

                    "second" -> {
                        if (::userBundle.isInitialized) {
                            supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container_view,
                                    SecondDataFragment.newInstance(userBundle)
                                )
                                .commit()
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}