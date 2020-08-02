package com.yagr.myapplication

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_scrolling.*


class ScrollingActivity : AppCompatActivity() {

    val frag1 = BlankFragment()
    val frag2 = BlankFragment2()
    var using1 = false

    lateinit var containerView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        containerView = fragment_container_view

        fragment_switcher.setOnClickListener { switchFragment() }
    }


    private fun switchFragment() {
        val toolbarParams = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        val fragContainerParams = (fragment_container_view.layoutParams as CoordinatorLayout.LayoutParams)
        lateinit var fragToUse: Fragment

        if (using1) {
            fragToUse = frag2
            toolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
            fragContainerParams.behavior = ScrollingViewBehavior()
            app_bar.setBackgroundColor(resources.getColor(R.color.colorPrimary, this.theme))

        } else {
            fragToUse = frag1
            toolbarParams.scrollFlags = 0
            fragContainerParams.behavior = null
            app_bar.setBackgroundColor(resources.getColor(R.color.translucentBlack, this.theme))
        }

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left,
                        R.animator.slide_out_right, 0, 0)
                .replace(R.id.fragment_container_view, fragToUse)
                .commit()

        toolbar_layout.layoutParams = toolbarParams
        fragment_container_view.layoutParams = fragContainerParams
        using1 = !using1

    }
}