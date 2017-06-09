package com.elpassion.crweather

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LifecycleRegistryOwner {

    val registry = LifecycleRegistry(this);

    override fun getLifecycle() = registry // can not use LifecycleActivity (it does not have setSupportActionBar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)
        initModel()
    }

    override fun onBackPressed() = if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START)
    } else {
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.w("CRW", "TODO: convert \"$item\" to user action and send to view model")
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initModel() {
        val model = ViewModelProviders.of(this).get(MainModel::class.java)
        model.loading.observe(this, Observer { Log.w("CRW", "TODO: display loading: $it") })
        model.city.observe(this, Observer { Log.w("CRW", "TODO: display city: $it") })
        model.charts.observe(this, Observer { Log.w("CRW", "TODO: display charts:\n$it") })
    }
}
