package com.example.mittechapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.R
import com.example.mittechapp.View.fragment.*
import com.example.mittechapp.databinding.ActivityNavigationBinding
import com.example.mittechapp.databinding.CommonHedderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    lateinit var bottomnavview: BottomNavigationView
    lateinit var binding: ActivityNavigationBinding
    lateinit var commonheaderbinding: CommonHedderBinding
    var backToExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        commonheaderbinding = CommonHedderBinding.bind(binding.root)
        setContentView(binding.root)

        onClickOnUi()
    }

    private fun onClickOnUi() {

        commonheaderbinding.ibBack.setOnClickListener {
            onBackPressed()
        }
        commonheaderbinding.ibHome.setOnClickListener {
            CommonUtils.snakeBarPopUp(binding.linnavigation, "Notification Empty")
        }
        commonheaderbinding.tvHeaderText.text = "Computer Nation"


        var Informantion = Information()
        var Home = HomeFragment()
        var Details = DetailsFragment()
        var Setting = SettingFragment()
        var checking = FragmentChecking()

        setFragment(Informantion)
        bottomnavview = binding.topNavigation

        bottomnavview.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.info -> {
                    setFragment(Informantion)
                }
                R.id.home -> {
                    setFragment(Home)
                }
                R.id.details -> {
                    setFragment(Details)
                }
                R.id.setting -> {
                    setFragment(Setting)
                }
                R.id.checking -> {
                    setFragment(checking)
                }
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }

    override fun onBackPressed() {
        if (backToExit) ActivityCompat.finishAffinity(this)
        this.backToExit = true
        CommonUtils.snakeBarPopUp(binding.linnavigation, "Press BACK again to exit")
        Handler().postDelayed(Runnable { backToExit = false }, 2000)
    }

}