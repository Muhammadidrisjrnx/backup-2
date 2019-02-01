package com.example.rplrus021.midsemester12rpl

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class adapter2 : RecyclerView.Adapter<adapter2.Holder>() {
    var context: Context? = null
    private var data: ArrayList<data>? = null

    fun adapter2(context: Context, data: ArrayList<data>) {
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return adapter2.Holder(view)

    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return data!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null
        var textView: TextView? = null
        var textView2: TextView? = null
        var button: Button? = null
        fun Holder(itemView: View) {
            imageView = itemView.findViewById(R.id.image_view) as ImageView
            textView = itemView.findViewById(R.id.textview_judul) as TextView
            textView2 = itemView.findViewById(R.id.textview_tanggal) as TextView
            button = itemView.findViewById(R.id.btn_detail) as Button
        }

    }
}