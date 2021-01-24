package com.example.dsc2020_finalproject_android.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc2020_finalproject_android.R
import com.example.dsc2020_finalproject_android.home.model.Province
import kotlinx.android.synthetic.main.province_item.view.*

class ProvinceAdapter(private var provinces : MutableList<Province>) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {


    inner class ProvinceViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val tvName : TextView = view.tv_province_name
        private val tvNumber : TextView = view.tv_number

        fun bindProvince(number : String ,p : Province){
            tvName.text = p.provinsi
            tvNumber.text = number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.province_item, parent, false)
        return ProvinceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val p = provinces[position]
        holder.bindProvince("#"+(position+1),p)
    }

    override fun getItemCount(): Int = provinces.size
fun setProvinces(provinces : MutableList<Province>){
    this.provinces.apply {
        clear()
        addAll(provinces)
    }
    notifyDataSetChanged()
}
}