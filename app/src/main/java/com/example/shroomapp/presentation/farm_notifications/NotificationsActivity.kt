package com.example.shroomapp.presentation.farm_notifications

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shroomapp.R
import com.example.shroomapp.databinding.ActivityNotificationsBinding
import com.example.shroomapp.presentation.farm_notifications.adapters.NotificationAdapter
import com.example.shroomapp.presentation.farm_notifications.dialog.AddEditNotificationDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotificationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsBinding
    private lateinit var viewModel: NotificationsViewModel
    private val mAdapter= NotificationAdapter()

    private var farmId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        farmId = intent.getIntExtra(FARM_ID,0)
        viewModel = ViewModelProvider(this,NotificationsViewModelFactory(application,farmId,null))
            .get(NotificationsViewModel::class.java)

        binding.fab.setOnClickListener { view ->
            val fragment = AddEditNotificationDialogFragment.newInstanceAdd(farmId = farmId)
            fragment.show(supportFragmentManager, AddEditNotificationDialogFragment.TAG)
        }

        configureToolbarListeners()
        configureRecycler()
        configureDataDisplay()
    }

    private fun configureRecycler(){
        binding.recyclerNotifications.layoutManager=
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        binding.recyclerNotifications.adapter=mAdapter
    }

    private fun configureDataDisplay(){
        viewModel.notifications.observe(this){
            mAdapter.submitList(it)
        }
    }

    private fun configureToolbarListeners() {
        //back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_notification -> {
                    MaterialAlertDialogBuilder(this)
                        .setMessage("Delete the notifications?")
                        .setNegativeButton("No") { dialog, which ->

                        }
                        .setPositiveButton("Yes") { dialog, which ->
                            //delete marked notifications...
                        }
                        .show()
                    true
                }
                else -> false
            }
        }
    }

    companion object{
        private const val EXTRA_MODE = "EXTRA_MODE"
        private const val MODE_FARM = "MODE_FARM_NOTIFICATIONS"
        private const val MODE_SENSOR = "MODE_SENSOR_NOTIFICATIONS"
        private const val FARM_ID = "FARM_ID"
        private const val SENSOR_NAME = "SENSOR_NAME"

        fun newIntentFarmNotifications(context: Context, farmName: String):Intent{
            val intent = Intent(context,NotificationsActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_FARM)
            intent.putExtra(FARM_ID,farmName)
            return intent
        }

        fun newIntentSensorNotifications(context: Context, farmName: String, sensorName: String):Intent{
            val intent = Intent(context,NotificationsActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_SENSOR)
            intent.putExtra(FARM_ID,farmName)
            intent.putExtra(SENSOR_NAME,sensorName)
            return intent
        }
    }
}