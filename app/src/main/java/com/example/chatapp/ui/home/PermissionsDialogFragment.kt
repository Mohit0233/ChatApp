package com.example.chatapp.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import com.example.chatapp.R

class PermissionsDialogFragment : DialogFragment() {

    internal lateinit var listener: PermissionsDialogListener
    internal lateinit var lis: PermissionsDialogBackListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            /*builder.setOnKeyListener(DialogInterface.OnKeyListener { dialogInterface, keyCode, keyEvent ->
                // getAction to make sure this doesn't double fire
                if (keyCode == KeyEvent.KEYCODE_BACK ) {
                    // Your code here
                    Log.e("hi","setonkey listner")
                    true // Capture onKey
                } else false
                // Don't capture
            })*/
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.permissions_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.positive_button,
                    DialogInterface.OnClickListener { _, _ ->
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(R.string.negative_button,
                    DialogInterface.OnClickListener { _, _ ->
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(this)
                    })
            isCancelable = false
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }


    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface PermissionsDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Override the Fragment.onAttach() method to instantiate the PermissionsDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the PermissionsDialogListener so we can send events to the host
            listener = context as PermissionsDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement PermissionsDialogListener")
            )
        }
    }

    interface PermissionsDialogBackListener {
        fun backPress(dialog: DialogInterface)
    }
    override fun onResume() {
        super.onResume()
        (dialog ?: return).setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                //This is the filter
                if (event.action != KeyEvent.ACTION_DOWN) true else {
                    //Hide your keyboard here!!!!!!
                    lis = context as PermissionsDialogBackListener
                    lis.backPress(dialog)
                    Log.e("hi","setonkey listner on resume")

                    true // pretend we've processed it
                }
            } else false // pass on to be processed as normal
        }
    }

}