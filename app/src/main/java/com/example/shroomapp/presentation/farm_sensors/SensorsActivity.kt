package com.example.shroomapp.presentation.farm_sensors

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shroomapp.presentation.farm_sensors.adapters.SensorAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shroomapp.R
import com.example.shroomapp.databinding.ActivitySensorsBinding
import com.example.shroomapp.presentation.farm_notifications.NotificationsActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SensorsActivity : AppCompatActivity() {

    private lateinit var viewModel: SensorsViewModel
    private lateinit var binding: ActivitySensorsBinding
    private val mAdapter = SensorAdapter()

    private var farmId = 0
    private var farmName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        farmId = intent.getIntExtra(FARM_ID,0)
        farmName = intent.getStringExtra(FARM_NAME).toString()

        viewModel = ViewModelProvider(
            this,SensorsViewModelFactory(application, farmId = farmId))
            .get(SensorsViewModel::class.java)

        binding = ActivitySensorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureToolbarListeners()
        configureRecycler()
        configureDataDisplay()

        viewModel.shouldClose.observe(this){
            finish()
        }
    }

    private fun configureRecycler() {
        binding.recyclerSensors.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.recyclerSensors.adapter = mAdapter
        binding.recyclerSensors.itemAnimator = null
    }

    private fun configureDataDisplay() {
        viewModel.sensorsList.observe(this, Observer { data ->
            mAdapter.submitList(null)
            mAdapter.submitList(data)
        })
    }

    private fun configureToolbarListeners() {
        //back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val title = intent.getStringExtra(FARM_NAME)
        binding.toolbar.title = title

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_farm -> {
                    MaterialAlertDialogBuilder(this)
                        .setMessage("Удалить ферму?")
                        .setNegativeButton("Нет") { dialog, which -> }
                        .setPositiveButton("Да") { dialog, which ->
                            //delete th farm...
                            viewModel.deleteFarm()
                            finish()
                        }
                        .show()
                    true
                }
                R.id.notifications -> {
                    val intent = NotificationsActivity.newIntentFarmNotifications(this,farmName)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        private const val FARM_NAME = "FARM_NAME"
        private const val FARM_ID = "FARM_ID"

        fun newIntent(context: Context,farmName:String,farmId:Int):Intent {
            val intent = Intent(context,SensorsActivity::class.java)
            intent.putExtra(FARM_NAME,farmName)
            intent.putExtra(FARM_ID,farmId)
            return intent
        }
    }
}