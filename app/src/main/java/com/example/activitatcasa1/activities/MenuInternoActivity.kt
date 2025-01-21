package com.example.activitatcasa1.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.activitatcasa1.R
import com.example.activitatcasa1.databinding.ActivityMenuInternoBinding
import com.example.activitatcasa1.fragments.fragment_aboutus
import com.example.activitatcasa1.fragments.fragment_home
import com.example.activitatcasa1.fragments.fragment_menuprincipal
import com.example.activitatcasa1.fragments.fragment_settings
import com.example.activitatcasa1.fragments.fragment_share
import com.example.activitatcasa1.pojos.Cliente
import com.google.android.material.navigation.NavigationView

class MenuInternoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMenuInternoBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMenuInternoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.appbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val cliente = intent.getSerializableExtra("cliente") as Cliente?

        val fragment = fragment_menuprincipal().apply {
            arguments = Bundle().apply {
                putSerializable("cliente", cliente)
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment).commit()
        }

        binding.ayuda.setOnNavigationItemSelectedListener {

            it.isChecked = true
            when (it.itemId) {

                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment_menuprincipal()).commit()}
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment_settings()).commit()}
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment_share()).commit()}

            }
            false

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_home()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_settings()).commit()
            R.id.nav_share -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_settings()).commit()
            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_aboutus()).commit()
            R.id.nav_logout -> {
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
                finish()

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onBackPressed() {

        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else { super.onBackPressed() }

    }

}