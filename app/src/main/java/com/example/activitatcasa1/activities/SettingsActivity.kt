package com.example.activitatcasa1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.activitatcasa1.R
import com.example.activitatcasa1.databinding.ActivitySettingsBinding

private lateinit var binding: ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }


        binding.button3.setOnClickListener {

            val intent = Intent(this, MenuInternoActivity::class.java)
            startActivity(intent)

        }

    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey)

        }

    }

}
