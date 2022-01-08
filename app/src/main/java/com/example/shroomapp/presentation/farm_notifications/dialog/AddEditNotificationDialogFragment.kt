package com.example.shroomapp.presentation.farm_notifications.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.shroomapp.R
import com.example.shroomapp.databinding.DialogFragmentAddEditNotificationBinding
import com.example.shroomapp.presentation.farm_notifications.NotificationsViewModel

class AddEditNotificationDialogFragment: DialogFragment() {

    private lateinit var binding:DialogFragmentAddEditNotificationBinding
    private lateinit var viewModel: DialogViewModel

    private var farmId=-1
    private var notificationId=-1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        if(arguments?.getString(EXTRA_MODE) == MODE_EDIT_NOTIFICATION){
            notificationId = arguments?.getInt(NOTIFICATION_ID) ?:0
        }
        farmId = arguments?.getInt(FARM_ID) ?: 0

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_add_edit_notification,container,
            false)

        binding.buttonSaveNotification.setOnClickListener {
            dismiss()
        }

        binding.buttonDialogClose.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    companion object {
        const val TAG = "AddEditNotificationDialogFragment"
        private const val EXTRA_MODE = "MODE"
        private const val MODE_ADD_NOTIFICATION = "MODE_ADD_NOTIFICATION"
        private const val MODE_EDIT_NOTIFICATION = "MODE_EDIT_NOTIFICATION"
        private const val FARM_ID = "FARM_ID"
        private const val NOTIFICATION_ID = "NOTIFICATION_ID"

        fun newInstanceAdd(farmId:Int): AddEditNotificationDialogFragment {
            val args = Bundle()
            args.putString(EXTRA_MODE, MODE_ADD_NOTIFICATION)
            args.putInt(FARM_ID,farmId)
            val fragment = AddEditNotificationDialogFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstanceEdit(farmId:Int,notificationId:Int): AddEditNotificationDialogFragment {
            val args = Bundle()
            args.putString(EXTRA_MODE, MODE_EDIT_NOTIFICATION)
            args.putInt(NOTIFICATION_ID,notificationId)
            args.putInt(FARM_ID,farmId)
            val fragment = AddEditNotificationDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}