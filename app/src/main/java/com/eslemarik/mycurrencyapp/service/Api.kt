package com.eslemarik.mycurrencyapp.service

import com.eslemarik.mycurrencyapp.model.MyCurrencyDataModel
import retrofit2.Response
import retrofit2.http.GET

interface Api{

    @GET("daily/try.json")
    suspend fun getTry() : Response<MyCurrencyDataModel>

}