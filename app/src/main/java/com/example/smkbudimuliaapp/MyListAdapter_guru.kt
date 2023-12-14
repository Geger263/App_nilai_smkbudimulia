package com.example.smkbudimuliaapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter_guru(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val email: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list_guru, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_guru, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val emailText = rowView.findViewById(R.id.textViewEmail) as TextView

        idText.text = "Nip : ${id[position]}"
        nameText.text = "Nama : ${name[position]}"
        emailText.text = "Alamat : ${email[position]}"
        return rowView
    }
}