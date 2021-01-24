package com.example.dsc2020_finalproject_android.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc2020_finalproject_android.R
import com.example.dsc2020_finalproject_android.home.model.Province
import kotlinx.android.synthetic.main.province_detail_item.view.*

class ListAdapter(val provinces : MutableList<Province>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val tvNumber = view.tv_number
        private val tvName = view.tv_province_name
        private val tvPositive = view.tv_positive
        private val tvDeath = view.tv_death
        private val tvRecovered = view.tv_recovered

        fun onBind(position : String,province : Province){
            tvNumber.text = position
            tvName.text = province.provinsi
            tvPositive.text = province.kasusPosi.toString()
            tvRecovered.text = province.kasusSemb.toString()
            tvDeath.text = province.kasusMeni.toString()
        }

    }

    fun setProvinces(provinces : MutableList<Province>){
        this.provinces.apply {
            clear()
            addAll(provinces)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.province_detail_item,parent,false)
        return ListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val province = provinces[position]
        holder.onBind("#"+(position+1),province)
    }

    override fun getItemCount(): Int = provinces.size

}