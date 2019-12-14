package fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Thanks to umarhussain15
 * https://gist.github.com/umarhussain15/b661ee44d196b1b6216fa6495b125343
 */

class SwipeDisabledViewPager(context: Context, attributeSet: AttributeSet?): ViewPager(context, attributeSet) {
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}