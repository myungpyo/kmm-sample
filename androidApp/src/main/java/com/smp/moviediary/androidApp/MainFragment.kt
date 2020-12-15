package com.smp.moviediary.androidApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.smp.moviediary.androidApp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        val TAG = "${MainFragment::class.qualifiedName}"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentPagerView.adapter = MainFragmentPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.fragmentPagerView) { tab, pos ->
            tab.setIcon(MainTabDef.values()[pos].iconResId)
            tab.setText(MainTabDef.values()[pos].titleResId)
        }.attach()
    }
}