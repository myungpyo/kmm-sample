package com.smp.moviediary.androidApp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

enum class MainTabDef(
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
    val fragmentFactory: () -> Fragment
) {
    SCRAP(R.drawable.ic_scrap_tab, R.string.com_scrap, { ScrapFragment() }),
    SEARCH(R.drawable.ic_search_tab, R.string.com_search, { SearchFragment() })
}