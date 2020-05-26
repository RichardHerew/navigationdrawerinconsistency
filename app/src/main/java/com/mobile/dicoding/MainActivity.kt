package com.mobile.dicoding

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.mobile.dicoding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity :
    AppCompatActivity(),
    AboutFragment.AboutFragmentListener,
    StoryDetailsFragment.StoryDetailsFragmentListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val onBackgroundColor  by lazy {
        val value = TypedValue()
        theme.resolveAttribute(R.attr.colorOnBackground, value, true)
        value.data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = act_main_nav_host_fragment.findNavController()

        setupDrawerLayout()
        setupActionBar()
        setupNavigationMenu()
        setupCollapsingToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun changeCollapsingToolbarState(expanded: Boolean) {
        binding.actMainAppbarlayout.setExpanded(expanded, true)
    }

    private fun setupActionBar() {
        val toolbar = binding.actMainToolbar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val menuIcon = ResourcesCompat.getDrawable(resources, R.drawable.drawable_menu_medium, null)
        menuIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(menuIcon)
    }

    private fun setupDrawerLayout() {
        val drawer = binding.actMainDrawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_dest, R.id.about_dest),
            drawer
        )
    }

    private fun setupNavigationMenu() {
        val navView = binding.actMainDrawerNavView
        navView.setupWithNavController(navController)
        changeMenuIconColor("white")
    }

    private fun setupCollapsingToolbar() {
        binding.actMainAppbarlayout.addOnOffsetChangedListener(
            OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) { // Collapsed
                    changeMenuIconColor("onBackground")
                } else if (verticalOffset == 0) { // Fully expanded
                    changeMenuIconColor("white")
                }
            }
        )
    }

    private fun changeMenuIconColor(color: String) {
        var tintColor = Color.WHITE
        when (color) {
            "white" -> tintColor = Color.WHITE
            "onBackground" -> tintColor = onBackgroundColor
        }
        binding.actMainToolbar.navigationIcon?.setTint(tintColor)
    }
}
