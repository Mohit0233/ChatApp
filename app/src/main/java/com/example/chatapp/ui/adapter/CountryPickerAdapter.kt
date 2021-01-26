package com.example.chatapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.ui.model.CountryCallingCodeDetailModel

class CountryPickerAdapter(private var context: Context,var listener: CountryPickerOnItemClickListener) :
    RecyclerView.Adapter<CountryPickerAdapter.ViewHolder>() {


    private val diffUtilCallback: DiffUtil.ItemCallback<CountryCallingCodeDetailModel> =
        object : DiffUtil.ItemCallback<CountryCallingCodeDetailModel>() {
            override fun areItemsTheSame(
                newCountryCallingCodeDetailModel: CountryCallingCodeDetailModel,
                oldCountryCallingCodeDetailModel: CountryCallingCodeDetailModel
            ): Boolean {
                return newCountryCallingCodeDetailModel.ccc == oldCountryCallingCodeDetailModel.ccc
            }

            override fun areContentsTheSame(
                newCountryCallingCodeDetailModel: CountryCallingCodeDetailModel,
                oldCountryCallingCodeDetailModel: CountryCallingCodeDetailModel
            ): Boolean {
                return newCountryCallingCodeDetailModel == oldCountryCallingCodeDetailModel
            }
        }
    private var countryCallingCodeDetailModelList: AsyncListDiffer<CountryCallingCodeDetailModel> =
        AsyncListDiffer(this, diffUtilCallback)

    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var countryFlag: ImageView? = null
        var countryFirstName: TextView? = null
        var countrySecondName: TextView? = null
        var countryCode: TextView? = null
        var countryPickerCheckmark: ImageView? = null

        init {
            if (viewType == 1) {
                countryFlag = itemView.findViewById(R.id.country_flag)
                countryFirstName = itemView.findViewById(R.id.country_first_name)
                countrySecondName = itemView.findViewById(R.id.country_second_name)
                countryCode = itemView.findViewById(R.id.country_code)
                countryPickerCheckmark = itemView.findViewById(R.id.countrypicker_checkmark)
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(countryCallingCodeDetailModelList.currentList[position].ccc,
                    countryCallingCodeDetailModelList.currentList[position].countryFirstName
                    )
            }
        }
    }


    fun addData(countryCallingCodeDetailModelData: ArrayList<CountryCallingCodeDetailModel>) {

        countryCallingCodeDetailModelList.submitList(countryCallingCodeDetailModelData)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(requireLayout(viewType), parent, false)
        return ViewHolder(itemView, viewType)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (countryCallingCodeDetailModelList.currentList.size != 0) {
            val countryCallingCodeDetailModel: CountryCallingCodeDetailModel =
                countryCallingCodeDetailModelList.currentList[position]
            holder.countryFlag?.setImageDrawable(countryCallingCodeDetailModel.countryFlag)
            holder.countryFirstName?.text = countryCallingCodeDetailModel.countryFirstName
            holder.countrySecondName?.text = countryCallingCodeDetailModel.countrySecondName
            holder.countryCode?.text = countryCallingCodeDetailModel.ccc
            if (countryCallingCodeDetailModel.isSelected) {
                holder.countryPickerCheckmark?.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_action_search
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int) =
        if (countryCallingCodeDetailModelList.currentList.size == 0) 0 else 1

    private fun requireLayout(viewType: Int): Int =
        if (viewType == 1) R.layout.country_picker_row else R.layout.multiple_contact_picker_row_empty

    override fun getItemCount() =
        if (countryCallingCodeDetailModelList.currentList.size != 0) countryCallingCodeDetailModelList.currentList.size else 1

    interface CountryPickerOnItemClickListener {
        fun onItemClick(ccc: String, countryFirstName: String)
    }


}