package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_begin.*
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
    }

    private fun fragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_home -> {
                return HomeFragment()
            }

            R.id.navigation_write -> {
                return WriteFragment()
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
