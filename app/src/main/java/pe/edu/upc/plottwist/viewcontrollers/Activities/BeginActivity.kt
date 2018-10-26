package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_begin.*
import kotlinx.android.synthetic.main.activity_register.*
import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.viewcontrollers.fragments.HomeFragment
import pe.edu.upc.plottwist.viewcontrollers.fragments.WriteFragment

class BeginActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
    }

    private fun fragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment()

                homeFragment.client = intent.getStringExtra("client")
                homeFragment.expiry = intent.getStringExtra("expiry")
                homeFragment.uid = intent.getStringExtra("uid")
                homeFragment.tokenUser = intent.getStringExtra("token")

                return homeFragment
            }

            R.id.navigation_write -> {
                val wirteFragment = WriteFragment()
                wirteFragment.client = intent.getStringExtra("client")
                wirteFragment.expiry = intent.getStringExtra("expiry")
                wirteFragment.uid = intent.getStringExtra("uid")
                wirteFragment.token = intent.getStringExtra("token")
                wirteFragment.userid = intent.getStringExtra("userId")
                return wirteFragment
            }
        }
        return HomeFragment()
    }


    private fun navigateTo(item: MenuItem): Boolean {
        item.setChecked(true)
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.beginContent, fragmentFor(item))
                .commit() > 0
    }


}
