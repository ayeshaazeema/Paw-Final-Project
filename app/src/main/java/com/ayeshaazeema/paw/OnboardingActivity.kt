package com.ayeshaazeema.paw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.ayeshaazeema.paw.adapter.SliderAdapter
import com.ayeshaazeema.paw.db.AppPreferences
import com.ayeshaazeema.paw.ui.SignInActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: SliderAdapter
    private var points: Array<TextView?>? = null
    private lateinit var layouts: Array<Int>
    private val sliderChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            TODO("Not yet implemented")
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            TODO("Not yet implemented")
        }

        override fun onPageSelected(position: Int) {
            addBottomPoint(position)

            if (position == layouts.size.minus(1)) {
                tvSkip.show()
                tvStart.show()
                tvNext.hide()

            } else {
                tvSkip.show()
                tvNext.show()
                tvStart.hide()

            }
        }

    }

    private fun addBottomPoint(lastPosition: Int) {
        points = arrayOfNulls(layouts.size)

        llDot.removeAllViews()
        for (i in 0 until points!!.size) {
            points!![i] = TextView(this)
            points!![i]?.text = getColor(R.color.yellow).toString()
            points!![i]?.textSize = 35f
            points!![i]?.setTextColor(resources.getColor(R.color.yellow_transparent))
            llDot.addView(points!![i])
        }
        if (points!!.isNotEmpty()) {
            points!![lastPosition]?.setTextColor(resources.getColor(R.color.yellow))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        init()
        dataSet()
        interactions()
    }

    private fun interactions() {
        tvSkip.setOnClickListener {
            navigateToLogin()
        }
        tvStart.setOnClickListener {
            navigateToLogin()
        }
        tvNext.setOnClickListener {
            val currentPage = getCurrentScreen(+1)
            if (currentPage < layouts.size) {
                vpSlider.currentItem = currentPage

            } else {
                navigateToLogin()
            }
        }
    }

    private fun getCurrentScreen(i: Int): Int = vpSlider
        .currentItem.plus(i)

    private fun navigateToLogin() {
        AppPreferences(this).setFirstLaunch(false)
        startActivity(
            Intent(
                this,
                SignInActivity::class.java
            )
        )
        finish()
    }

    private fun dataSet() {
        addBottomPoint(0)
        vpSlider.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun init() {
        layouts = arrayOf(
            R.layout.first_on_boarding,
            R.layout.second_on_boarding,
            R.layout.third_on_boarding
        )
        sliderAdapter = SliderAdapter(this, layouts)
    }
}

private fun View.show() {
    visibility = View.VISIBLE
}

private fun View.hide() {
    visibility = View.GONE
}