package com.example.dsc2020_finalproject_android.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dsc2020_finalproject_android.ApiClient
import com.example.dsc2020_finalproject_android.ApiService
import com.example.dsc2020_finalproject_android.R
import com.example.dsc2020_finalproject_android.home.model.Province
import com.example.dsc2020_finalproject_android.home.model.ProvincesResponse
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    lateinit var adapter : ListAdapter
    private var provinces = mutableListOf<Province>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        adapter = ListAdapter(provinces)
    }

    override fun onStart() {
        super.onStart()
        initProvinces()

        btn_back.setOnClickListener {
            finish()
        }

        et_search.addTextChangedListener {
            var query = et_search?.text.toString()
            query = query.toString().toLowerCase()
            if(!query.isNullOrEmpty()){
                val newProvinces = mutableListOf<Province>()
                provinces.filterTo(newProvinces) { e -> e.provinsi.toString().toLowerCase().contains(query) }
                Log.d("<Result>",newProvinces.size.toString())
                adapter?.setProvinces(newProvinces)
            }else{
                adapter?.setProvinces(provinces)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initProvinces()
    }

    fun initProvinces(){
        adapter = ListAdapter(provinces)
        rv_provinces.layoutManager = LinearLayoutManager(this)
        rv_provinces.adapter = adapter
        progress_bar.visibility = View.VISIBLE
        ll_data.visibility = View.GONE
        ApiClient.apiService.getProvinces().enqueue(object : Callback<ProvincesResponse> {
            override fun onResponse(call: Call<ProvincesResponse>, response: Response<ProvincesResponse>) {
                progress_bar.visibility = View.GONE
                ll_data.visibility = View.VISIBLE
                if(response.isSuccessful){
                    response.body()?.let {
                        provinces = it.data
                        adapter.setProvinces(provinces)
                    }
                }
            }

            override fun onFailure(call: Call<ProvincesResponse>, t: Throwable) {
                progress_bar.visibility = View.GONE
                ll_data.visibility = View.VISIBLE
                Toast.makeText(this@ListActivity,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
}