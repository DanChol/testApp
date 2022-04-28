package kg.unicapp.botttomtest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kg.unicapp.botttomtest2.airdrops.AirdropFragment
import kg.unicapp.botttomtest2.databinding.ActivityMainBinding
import kg.unicapp.botttomtest2.InfoFragment
import kg.unicapp.botttomtest2.miningapps.MiningFragment

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bNav.setOnNavigationItemSelectedListener {
            handleBottomNavigation(
                it.itemId
            )
        }
        binding.bNav.selectedItemId = R.id.airdrops
    }
    private fun handleBottomNavigation(
        menuItemId: Int
    ): Boolean = when (menuItemId) {
        R.id.airdrops ->  {
            swapFragments(AirdropFragment())
            true
        }

        R.id.mining_apps -> {
            swapFragments(MiningFragment())
            true
        }

        R.id.about_app -> {
            swapFragments(InfoFragment())
            true
        }
        else -> false
    }

    private fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container, fragment)
            .commit()
    }
}

