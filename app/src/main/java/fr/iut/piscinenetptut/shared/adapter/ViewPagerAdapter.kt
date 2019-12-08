package fr.iut.piscinenetptut.shared.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ViewPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val fragments: ArrayList<Fragment>,
    private val fragmentsTitle: ArrayList<String>?
): FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val TAG: String = "ViewPagerAdapter"

    override fun getItem(position: Int): Fragment {
        try {
            return fragments[position]
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }

        // not good
        return Fragment()
    }

    override fun getCount(): Int {
        try {
            return fragments.size
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }

        // not good
        return 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        try {
            if (null != fragmentsTitle) {
                return fragmentsTitle[position]
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }

        return null
    }
}