package com.example.shroomapp.presentation.farms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shroomapp.R
import com.example.shroomapp.databinding.ActivityMyFarmsBinding
import com.example.shroomapp.domain.Farm
import com.example.shroomapp.presentation.add_new_farm.AddFarmActivity
import com.example.shroomapp.presentation.farm_sensors.SensorsActivity
import com.example.shroomapp.presentation.farms.adapters.FarmAdapter
import com.example.shroomapp.presentation.profile.ProfileActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MyFarmsActivity : AppCompatActivity() {

    private lateinit var viewModel: MyFarmsViewModel
    private lateinit var binding: ActivityMyFarmsBinding
    private val mAdapter = FarmAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyFarmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyFarmsViewModel::class.java)

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, AddFarmActivity::class.java)
            startActivity(intent)
        }

        configureToolbarListeners()
        configureRecycler()
        configureDataDisplay()
    }

    private fun configureToolbarListeners() {
        binding.toolbar.setNavigationOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage("Выйти из аккаунта?")
                .setNegativeButton("Нет") { dialog, which ->

                }
                .setPositiveButton("Да") { dialog, which ->
                    val pref = getSharedPreferences("ACCOUNT_DATA", MODE_PRIVATE)
                    val editor = pref.edit()
                    editor.putString("LOGIN",null)
                    editor.putString("PASSWORD",null)
                    editor.apply()
                    finish()
                }
                .show()
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }


    private fun configureRecycler() {
        mAdapter.onItemClickListener =object:FarmAdapter.OnItemClickListener{
            override fun onItemClick(farm: Farm) {
                val intent = SensorsActivity.newIntent(applicationContext,farm.name,farm.id)
                startActivity(intent)
            }
        }
        binding.recyclerFarms.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.recyclerFarms.adapter = mAdapter
    }

    private fun configureDataDisplay() {
        viewModel.farmsList.observe(this, Observer { data ->
            Log.d("TEST","Size of farms list = ${data.size}")
            mAdapter.submitList(data)
        })
    }
}