package fr.iut.piscinenetptut.shared.customView

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import java.util.concurrent.atomic.AtomicInteger


/**
 * This class is a replacement for android RadioGroup - it supports
 * child layouts which standard RadioGroup doesn't.
 */
class RecursiveRadioGroup : LinearLayout {
    interface OnCheckedChangeListener {
        fun onCheckedChanged(group: RecursiveRadioGroup?, @IdRes checkedId: Int)
    }

    var checkedItem: CompoundButton? = null
        private set
    private var childOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null
    /**
     * When this flag is true, onCheckedChangeListener discards events.
     */
    private var mProtectFromCheckedChange = false
    private var onCheckedChangeListener: OnCheckedChangeListener? = null
    private var mPassThroughListener: PassThroughHierarchyChangeListener? = null

    constructor(context: Context?) : super(context) {
        orientation = HORIZONTAL
        init()
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        childOnCheckedChangeListener = CheckedStateTracker()
        mPassThroughListener = PassThroughHierarchyChangeListener()
        super.setOnHierarchyChangeListener(mPassThroughListener)
    }

    override fun setOnHierarchyChangeListener(listener: OnHierarchyChangeListener) {
        mPassThroughListener!!.mOnHierarchyChangeListener = listener
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        // checks the appropriate radio button as requested in the XML file
        if (checkedItem != null) {
            mProtectFromCheckedChange = true
            setCheckedStateForView(checkedItem, true)
            mProtectFromCheckedChange = false
            setCheckedView(checkedItem)
        }
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
        parseChild(child)
        super.addView(child, index, params)
    }

    private fun parseChild(child: View) {
        if (child is CompoundButton) {
            val checkable = child as CompoundButton
            if (checkable.isChecked) {
                mProtectFromCheckedChange = true
                if (checkedItem != null) {
                    setCheckedStateForView(checkedItem, false)
                }
                mProtectFromCheckedChange = false
                setCheckedView(checkable)
            }
        } else if (child is ViewGroup) {
            parseChildren(child as ViewGroup)
        }
    }

    private fun parseChildren(child: ViewGroup) {
        for (i in 0 until child.childCount) {
            parseChild(child.getChildAt(i))
        }
    }

    /**
     *
     * Sets the selection to the radio button whose identifier is passed in
     * parameter. Using -1 as the selection identifier clears the selection;
     * such an operation is equivalent to invoking [.clearCheck].
     *
     * @param view the radio button to select in this group
     * @see .getCheckedItemId
     * @see .clearCheck
     */
    fun check(view: CompoundButton?) {
        if (checkedItem != null) {
            setCheckedStateForView(checkedItem, false)
        }
        if (view != null) {
            setCheckedStateForView(view, true)
        }
        setCheckedView(view)
    }

    private fun setCheckedView(view: CompoundButton?) {
        checkedItem = view
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener!!.onCheckedChanged(this, checkedItem!!.id)
        }
    }

    private fun setCheckedStateForView(checkedView: View?, checked: Boolean) {
        if (checkedView != null && checkedView is CompoundButton) {
            (checkedView as CompoundButton).isChecked = checked
        }
    }

    /**
     *
     * Returns the identifier of the selected radio button in this group.
     * Upon empty selection, the returned value is -1.
     *
     * @return the unique id of the selected radio button in this group
     * @attr ref android.R.styleable#RadioGroup_checkedButton
     * @see .check
     * @see .clearCheck
     */
    @get:IdRes
    val checkedItemId: Int?
        get() = checkedItem?.id

    /**
     *
     * Clears the selection. When the selection is cleared, no radio button
     * in this group is selected and [.getCheckedItemId] returns
     * null.
     *
     * @see .check
     * @see .getCheckedItemId
     */
    fun clearCheck() {
        check(null)
    }

    /**
     *
     * Register a callback to be invoked when the checked radio button
     * changes in this group.
     *
     * @param listener the callback to call on checked state change
     */
    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        onCheckedChangeListener = listener
    }

    private inner class CheckedStateTracker : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(view: CompoundButton, b: Boolean) {
            if (mProtectFromCheckedChange) {
                return
            }
            mProtectFromCheckedChange = true
            if (checkedItem != null) {
                setCheckedStateForView(checkedItem, false)
            }
            mProtectFromCheckedChange = false
            val id = view.id
            setCheckedView(view)
        }
    }

    private inner class PassThroughHierarchyChangeListener :
        OnHierarchyChangeListener {
        var mOnHierarchyChangeListener: OnHierarchyChangeListener? = null
        override fun onChildViewAdded(parent: View?, child: View) {
            if (child is CompoundButton) {
                val id: Int = child.getId()
                if (id == View.NO_ID) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        child.setId(generateViewId())
                    } else {
                        child.setId(View.generateViewId())
                    }
                }
                (child as CompoundButton).setOnCheckedChangeListener(childOnCheckedChangeListener)
                mOnHierarchyChangeListener?.onChildViewAdded(parent, child)
            } else if (child is ViewGroup) { // View hierarchy seems to be constructed from the bottom up,
// so all child views are already added. That's why we
// manually call the listener for all children of ViewGroup.
                for (i in 0 until (child as ViewGroup).childCount) {
                    onChildViewAdded(child, (child as ViewGroup).getChildAt(i))
                }
            }
        }

        override fun onChildViewRemoved(parent: View?, child: View) {
            if (child is RadioButton) {
                (child as CompoundButton).setOnCheckedChangeListener(null)
            }
            mOnHierarchyChangeListener?.onChildViewRemoved(parent, child)
        }
    }

    companion object {
        /**
         * For generating unique view IDs on API < 17 with [.generateViewId].
         */
        private val sNextGeneratedId: AtomicInteger = AtomicInteger(1)

        /**
         * Generate a value suitable for use in [.setId].
         * This value will not collide with ID values generated at build time by aapt for R.id.
         *
         * @return a generated ID value
         */
        fun generateViewId(): Int {
            while (true) {
                val result: Int = sNextGeneratedId.get()
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                var newValue = result + 1
                if (newValue > 0x00FFFFFF) newValue = 1 // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(
                        result,
                        newValue
                    )
                ) {
                    return result
                }
            }
        }
    }
}