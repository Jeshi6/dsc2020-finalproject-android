package com.example.dsc2020_finalproject_android.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dsc2020_finalproject_android.ApiClient
import com.example.dsc2020_finalproject_android.R
import com.example.dsc2020_finalproject_android.home.adapter.ProvinceAdapter
import com.example.dsc2020_finalproject_android.home.model.Province
import com.example.dsc2020_finalproject_android.home.model.ProvincesResponse
import com.example.dsc2020_finalproject_android.home.model.TotalResponse
import com.example.dsc2020_finalproject_android.list.ListActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    lateinit var adapter : ProvinceAdapter
    private var provinces = mutableListOf<Province>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        adapter = ProvinceAdapter(provinces)
    }

    override fun onStart() {
        super.onStart()
        initListProvince()
        initDatas()

        tv_see_all.setOnClickListener {
           val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initListProvince()
        initDatas()
    }

    fun initDatas(){
        progress_bar.visibility = View.VISIBLE
        ll_data.visibility = View.GONE
        ApiClient.apiService.getTotal().enqueue(object : Callback<TotalResponse> {
            override fun onResponse(call: Call<TotalResponse>, response: Response<TotalResponse>) {
                progress_bar.visibility = View.GONE
                ll_data.visibility = View.VISIBLE
                if(response.isSuccessful){
                    val data = response?.body()
                    val d = data?.deaths?.value
                    val c = data?.confirmed?.value
                    val r = data?.recovered?.value
                    tv_death?.text = d.toString()
                    tv_positive?.text = c.toString()
                    tv_recovered?.text = r.toString()
                    if (d != null && c!=null && r!=null) {
                        tv_total?.text = (d+c+r).toString()
                    }

                    adapter = ProvinceAdapter(provinces)

                    rv_provinces.adapter = adapter
                    rv_provinces.layoutManager = LinearLayoutManager(this@HomeActivity)
                }

            }

            override fun onFailure(call: Call<TotalResponse>, t: Throwable) {
                progress_bar.visibility = View.GONE
                ll_data.visibility = View.VISIBLE
                Toast.makeText(this@HomeActivity,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }

    fun initListProvince(){
        ApiClient.apiService.getProvinces().enqueue(object : Callback<ProvincesResponse>{
            override fun onResponse(call: Call<ProvincesResponse>, response: Response<ProvincesResponse>) {
                if(response.isSuccessful){
                   response.body()?.let {
                       provinces = it?.data
                       provinces.sortByDescending { p -> p.kasusPosi }
                       provinces = provinces.subList(0,3)
                       adapter.setProvinces(provinces)
                   }
                }
            }

            override fun onFailure(call: Call<ProvincesResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
}