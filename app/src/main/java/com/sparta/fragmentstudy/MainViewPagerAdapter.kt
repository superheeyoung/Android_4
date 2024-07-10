package com.sparta.fragmentstudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity : FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        MainTabModel(FirstFragment.newInstance(),R.string.first_fragment_label),
        MainTabModel(SecondFragment.newInstance(), R.string.second_fragment_label)
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position].fragment

    fun getTitle(position : Int) : Int = fragments[position].title
}