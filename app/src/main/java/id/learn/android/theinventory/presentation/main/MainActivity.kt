package id.learn.android.theinventory.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        changeToolbarTitle()

        //init navController
        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.dataBarangFragment, R.id.peminjamanFragment, R.id.historyFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun changeToolbarTitle(){
        navController.addOnDestinationChangedListener{
                controller, destination, arguments ->
            if (navController.currentDestination!!.label!!.equals("HomeFragment")){
                binding.toolbar.title = "Home"
            } else if (navController.currentDestination!!.label!!.equals("DataBarangFragment")) {
                binding.toolbar.title = "Data Barang"
            }
            else if (navController.currentDestination!!.label!!.equals("PeminjamanFragment")) {
                binding.toolbar.title = "Peminjaman "
            }
            else if (navController.currentDestination!!.label!!.equals("HistoryFragment")) {
                binding.toolbar.title = "History"
            }
            else if (navController.currentDestination!!.label!!.equals("WelcomeFragment")) {
                binding.toolbar.title = "Welcome"
            }
            else if (navController.currentDestination!!.label!!.equals("DaftarFragment")) {
                binding.toolbar.title = "Buat Akun"
            }
            else if (navController.currentDestination!!.label!!.equals("LoginFragment")) {
                binding.toolbar.title = "Masuk"
            }
            else if (navController.currentDestination!!.label!!.equals("IsiDataPeminjamanFragment")) {
                binding.toolbar.title = "Peminjaman"
            }
            else if (navController.currentDestination!!.label!!.equals("ListBarangFragment")) {
                binding.toolbar.title = "Pilih Barang"
            }
            else if (navController.currentDestination!!.label!!.equals("ListStatusPeminjamanFragment")) {
                binding.toolbar.title = "Status Peminjaman"
            }
            else if (navController.currentDestination!!.label!!.equals("DetailStatusPeminjamanFragment")) {
                binding.toolbar.title = "Detail"
            }
            else if (navController.currentDestination!!.label!!.equals("ProfileFragment")) {
                binding.toolbar.title = "Profile Saya"
            }
            else if (navController.currentDestination!!.label!!.equals("UbahPassFragment")) {
                binding.toolbar.title = "Ubah Password"
            } else {
                binding.toolbar.title = "Default"
            }

        }
    }


    fun setBottomNavViewVisibility(visibility: Boolean){
        binding.navView.visibility = if(visibility) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}