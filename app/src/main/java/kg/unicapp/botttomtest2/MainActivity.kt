package kg.unicapp.botttomtest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kg.unicapp.botttomtest2.airdrops.AirdropFragment
import kg.unicapp.botttomtest2.miningapps.MiningFragment

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, AirdropFragment()).commit()
        val bNav: NavigationBarView = findViewById(R.id.bNav)
        bNav.setOnItemSelectedListener(navListener)
    }

    val navListener = NavigationBarView.OnItemSelectedListener{
        when(it.itemId){
            R.id.mining_apps -> {
                currentFragment = MiningFragment()
            }
            R.id.airdrops -> {
                currentFragment = AirdropFragment()
            }
            R.id.about_app -> {
                currentFragment = InfoFragment()
            }
        }
        true

    }

}