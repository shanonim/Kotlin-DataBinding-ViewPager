package com.shanonim.viewpagersample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.shanonim.viewpagersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit private var binding: ActivityMainBinding
    private var isLastPage: Boolean = false
    private val images: IntArray = intArrayOf(
            R.drawable.sushi_01,
            R.drawable.sushi_02,
            R.drawable.sushi_03
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.owner = this
        binding.viewPager.adapter = CustomPagerAdapter(this, images)
        binding.pageIndicatorView.setViewPager(binding.viewPager)
        binding.buttonNext.setOnClickListener {
            onClickNext()
        }

        binding.viewPager.addOnPageChangeListener(
                object : ViewPager.OnPageChangeListener {
                    override fun onPageSelected(p0: Int) {
                        // no-op
                    }

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                        // no-op
                    }

                    override fun onPageScrollStateChanged(p0: Int) {
                        when (p0) {
                            ViewPager.SCROLL_STATE_SETTLING -> {
                                if (binding.viewPager.currentItem == images.size - 1) {
                                    binding.buttonNext.text = "end"
                                } else {
                                    binding.buttonNext.text = "next"
                                }
                            }
                            ViewPager.SCROLL_STATE_IDLE -> {
                                isLastPage = binding.viewPager.currentItem == images.size - 1
                            }
                            else -> {
                                // no-op
                            }
                        }
                    }
                }
        )
    }

    fun onClickNext() {
        if (isLastPage) {
            finish()
        } else {
            binding.viewPager.arrowScroll(View.FOCUS_RIGHT)
        }
    }
}
