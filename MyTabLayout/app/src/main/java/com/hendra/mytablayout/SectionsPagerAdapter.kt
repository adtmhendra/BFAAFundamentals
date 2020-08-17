package com.hendra.mytablayout

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3)

    override fun getItem(position: Int): Fragment {
        val fragment = HomeFragment.newInstance(position + 1)
        return fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}