package com.eslemarik.mycurrencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslemarik.mycurrencyapp.R
import com.eslemarik.mycurrencyapp.adapter.MyCurrencyAdapter
import com.eslemarik.mycurrencyapp.databinding.ActivityMainBinding
import com.eslemarik.mycurrencyapp.model.MyCurrencyModel
import com.eslemarik.mycurrencyapp.service.Api
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://www.floatrates.com/"

    private lateinit var job : Job

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }


    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {

            val response = retrofit.getTry()

            withContext(Dispatchers.Main + exceptionHandler){
                if(response.isSuccessful){

                    response.body()?.let {

                        val myCurrencyModels : ArrayList<MyCurrencyModel> = ArrayList()
                        myCurrencyModels.add(it.usd)
                        myCurrencyModels.add(it.eur)
                        myCurrencyModels.add(it.chf)
                        myCurrencyModels.add(it.gbp)

                        binding.rvMyCurrencyApp.layoutManager = LinearLayoutManager(this@MainActivity.baseContext)
                        binding.rvMyCurrencyApp.adapter = MyCurrencyAdapter(myCurrencyModels)

                    }

                }
            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}