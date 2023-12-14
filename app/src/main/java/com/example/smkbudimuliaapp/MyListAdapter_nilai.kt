package com.example.smkbudimuliaapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter_nilai(private val context: Activity,
                          private val id: Array<String>,
                          private val harian: Array<String>, private val uts: Array<String>, private val uas: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list_nilai, harian) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_nilai, null, true)

        val idText = rowView.findViewById(R.id.textView_nis) as TextView
        val harianText = rowView.findViewById(R.id.textView_nilaiulhar) as TextView
        val utsText = rowView.findViewById(R.id.textView_uts) as TextView
        val uasText = rowView.findViewById(R.id.textView_uas) as TextView

        idText.text = "  Nis : ${id[position]}"
        harianText.text = "  Ulangan Harian : ${harian[position]}"
        utsText.text = "  UTS : ${uts[position]}"
        uasText.text = "  UAS : ${uas[position]}"
        return rowView
    }
}