package com.example.didinoapp.view.fragments.mymovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.didinoapp.view.fragments.gallery.GalleryFragment
import com.example.didinoapp.view.fragments.markedmovie.MarkedMoviesFragment
import com.example.didinoapp.view.fragments.viewsmovie.ViewsMoviesFragment

class MyMoviesFragmentAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MarkedMoviesFragment()
            1 -> return ViewsMoviesFragment()
            2 -> return GalleryFragment()
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "نشان شده ها"
            1 -> return "مشاهده شده ها"
            2 -> return "گالری آفلاین"
        }
        return null!!
    }
}