package com.example.chatapp.ui.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.chatapp.R
import com.example.chatapp.ui.login.RegisterPhoneActivity.Companion.ARRAY_LIST_SIM_DATA_PARCELABLE_EXTRA
import com.example.chatapp.ui.model.SimData


class SelectPhoneNumberDialogFragment : DialogFragment(), ItemSelectPhoneNumber.PhoneNumberRadioButtonChangeListener {

    private lateinit var itemAdapter: ItemSelectPhoneNumber
    private var arrayList = ArrayList<SimData>()
    private lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogInterface, position: Int)
    }

    override fun onAttach(context: Context) {
        listener = context as NoticeDialogListener
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_select_phone_number, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arrayList = requireArguments().getParcelableArrayList(ARRAY_LIST_SIM_DATA_PARCELABLE_EXTRA)!!
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
        if (arrayList.size == 0) {
            alertDialogBuilder.setMessage("No SIM Data Available")
        } else {
            val view = View.inflate(activity, R.layout.dialog_select_phone_number,null)
            val listView = view.findViewById<ListView>(R.id.listViewPhoneNumberDialog)
            itemAdapter = ItemSelectPhoneNumber(
                this,
                requireContext(),
                arrayList
            )
            listView.adapter = itemAdapter
            alertDialogBuilder.setView(view)
        }

        alertDialogBuilder.setTitle(R.string.select_phone_number_dialog_title)
        alertDialogBuilder.setPositiveButton("Use"
        ) { dialog, _ ->
            // on success
            for ((i, simData) in arrayList.withIndex()) {
                if (simData.isSelected) {
                    listener.onDialogPositiveClick(dialog, i)
                }
            }
        }
        alertDialogBuilder.setNegativeButton("Cancel"
        ) { dialog, _ ->
            dialog.cancel()
        }

        return alertDialogBuilder.create()
    }


    override fun onClick(position: Int) {
        for (simData in arrayList)
            simData.isSelected = false
        arrayList[position].isSelected = true
        itemAdapter.notifyDataSetChanged()
    }


}

class ItemSelectPhoneNumber(
    private val listener: PhoneNumberRadioButtonChangeListener,
    context: Context,
    arrayList: ArrayList<SimData>
) : ArrayAdapter<SimData>(context, 0, arrayList) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val simData = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.select_phone_number_item,parent, false)

        val titlePhoneNumberItem =  view?.findViewById<TextView>(R.id.titlePhoneNumberItem)
        val subtitlePhoneNumberItem =  view?.findViewById<TextView>(R.id.subtitlePhoneNumberItem)
        val radioPhoneNumberItem =  view?.findViewById<RadioButton>(R.id.radioPhoneNumberItem)
        val linearLayoutPhoneNumberItem =  view?.findViewById<LinearLayout>(R.id.linearLayoutPhoneNumberItem)

        titlePhoneNumberItem?.text = simData?.number
        val subtitle = "SIM " + (position + 1) + ", " + simData?.carrierName
        subtitlePhoneNumberItem?.text = subtitle
            radioPhoneNumberItem?.isChecked = simData?.isSelected == true

        linearLayoutPhoneNumberItem?.setOnClickListener {
            listener.onClick(position)
        }

        return view
    }

    interface PhoneNumberRadioButtonChangeListener {
        fun onClick(position: Int)
    }
}