package com.sonia.ordertask

import android.media.RouteListingPreference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlin.collections.ArrayList

class listadapter(var list: ArrayList<Order>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.list, parent, false)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.setText(list[position].name.toString())
        val tvquantity = view.findViewById<TextView>(R.id.tvquantity)
        tvquantity.setText(list[position].quantity.toString())
        return view
    }
}