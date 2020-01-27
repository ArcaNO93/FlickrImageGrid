package com.example.ilcarro.utils

import android.animation.TimeInterpolator
import android.content.Context
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.google.android.material.animation.AnimationUtils

class HideBottomViewOnScrollBehavior<V : View>(context: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<V>(context, attributeSet) {

    private val ENTER_ANIMATION_DURATION: Long = 225
    private val EXIT_ANIMATION_DURATION: Long = 175

    private val STATE_SCROLLED_DOWN = 1
    private val STATE_SCROLLED_UP = 2

    private var height = 0
    private var currentState = STATE_SCROLLED_UP
    private var additionalHiddenOffsetY = 0
    private var currentAnimator: ViewPropertyAnimator? = null

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        val paramsCompat: ViewGroup.MarginLayoutParams = child.layoutParams as ViewGroup.MarginLayoutParams
        height = child.measuredHeight + paramsCompat.bottomMargin
        slideDown(child, 5)
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) =
        axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        val view = target as NestedScrollView
        if (view.scrollY == view.getChildAt(0).measuredHeight - view.measuredHeight)
            slideUp(child)
        else
            slideDown(child, EXIT_ANIMATION_DURATION)
    }

    private fun slideUp(child: V) {
        if (currentState == STATE_SCROLLED_UP)
            return

        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }

        currentState = STATE_SCROLLED_UP
        animateChildTo(
            child,
            0,
            ENTER_ANIMATION_DURATION,
            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
    }

    private fun slideDown(child: V, duration: Long) {
        if (currentState == STATE_SCROLLED_DOWN)
            return

        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }

        currentState = STATE_SCROLLED_DOWN
        animateChildTo(
            child,
            height + additionalHiddenOffsetY,
            duration,
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
    }

    private fun animateChildTo(child: V, targetY: Int, duration: Long, interpolator: TimeInterpolator) {
        currentAnimator =
            child
                .animate()
                .translationY(targetY.toFloat())
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setUpdateListener {
                    currentAnimator = null
                }
    }
}
