package com.eslemarik.mycurrencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eslemarik.mycurrencyapp.R
import com.eslemarik.mycurrencyapp.model.MyCurrencyModel

class MyCurrencyAdapter(val currencyList : ArrayList<MyCurrencyModel>) : RecyclerView.Adapter<MyCurrencyAdapter.RowHolder>(){

    class RowHolder(view : View): RecyclerView.ViewHolder(view){


        val tvInverseRate : TextView = view.findViewById(R.id.tv_inverse_rate)
        val tvCode : TextView = view.findViewById(R.id.tv_code)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCurrencyAdapter.RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_currency,parent,false)
        return RowHolder(view)

    }

    override fun onBindViewHolder(holder: MyCurrencyAdapter.RowHolder, position: Int) {

        val tempCr = currencyList.get(position)

        holder.tvCode.text = tempCr.code
        holder.tvInverseRate.text = "%.2f".format(tempCr.inverseRate)

    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

}