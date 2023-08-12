package com.eslemarik.mycurrencyapp.model

data class MyCurrencyModel (

    val code : String,
    val alphaCode : String,
    val numericCode : Int,
    val name : String,
    val rate : Double,
    val date : String,
    val inverseRate : Double
)