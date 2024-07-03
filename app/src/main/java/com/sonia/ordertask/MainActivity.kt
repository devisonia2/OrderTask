package com.sonia.ordertask

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.sonia.ordertask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var orderList= arrayListOf<Order>()
    lateinit var listadapter: listadapter
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController = findNavController(R.id.navController)
        navController.addOnDestinationChangedListener { navController, destination, argument ->

        }

        binding?.bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragmentOne -> navController.navigate(R.id.firstFragment)
                R.id.fragmentTwo -> navController.navigate(R.id.secondFragment)
            }
            return@setOnItemSelectedListener true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}