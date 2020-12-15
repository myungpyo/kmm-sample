package com.smp.moviediary.androidApp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return MainTabDef.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return MainTabDef.values()[position].fragmentFactory()
    }
}