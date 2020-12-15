package com.smp.moviediary.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDefaultFragment()
    }

    private fun setupDefaultFragment() {
        supportFragmentManager.commit(allowStateLoss = true) {
            add(R.id.fragmentContainer, MainFragment(), MainFragment.TAG)
        }
    }
}
