package id.learn.android.theinventory.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.ActivityMainBinding
import id.learn.android.theinventory.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val vm: MainViewModel by viewModel()
    private lateinit var userManager: UserManager
    var userRole: String? = null
    var userNim: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        //init navController
        navController = findNavController(R.id.nav_host_fragment)

        userManager = UserManager(this)

        runBlocking {
            launch(context = Dispatchers.Default) {
                userRole = userManager.userRole.first()

                Log.d("TAG", "role akun = $userRole")
            }
        }

        runBlocking {
            launch(context = Dispatchers.Default) {
                if (userRole.equals("Admin")) {
                    userNim = 0
                } else {
                    userNim = userManager.userNim.first()
                }
            }
        }

        vm.user.observe(this, Observer {
            userRole = it.role
            setMenuBottomView()
            setAppbarSetting()
            invalidateOptionsMenu()
            saveUserRole(it.role)
            userNim = it.nim
            saveUserNim(it.nim)
        })

        setAppbarSetting()


        setMenuBottomView()

    }


    fun saveUserRole(value: String) {
        lifecycleScope.launch {
            userManager.saveUserRole(value)
        }
    }

    fun saveUserNim(value:Long){
        lifecycleScope.launch {
            userManager.saveUserNim(value)
        }
    }

    private fun setAppbarSetting(){
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.dataBarangFragment,
                if (userRole.equals("Admin")) R.id.peminjamanAdminFragment else R.id.peminjamanFragment,
                R.id.historyFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }


    fun updateUserRole() {
        lifecycleScope.launch {
            userRole = userManager.userRole.first()
        }
    }

    @SuppressLint("ResourceType")
    private fun setMenuBottomView(){
        if (userRole.equals("Admin")){
            //listPeminjamaman
            binding.navView.menu.findItem(R.id.peminjamanAdminFragment).isVisible = true
            binding.navView.menu.findItem(R.id.peminjamanFragment).isVisible = false
        } else {
            //listPeminjamaman
            binding.navView.menu.findItem(R.id.peminjamanAdminFragment).isVisible = false
            binding.navView.menu.findItem(R.id.peminjamanFragment).isVisible = true
        }
    }


    fun setBottomNavViewVisibility(visibility: Boolean) {
        binding.navView.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.menu_admin).isVisible = userRole.equals("Admin")
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                navController.navigate(R.id.profileFragment)
            }
            R.id.menu_logout -> {
                vm.logout()
                navController.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}