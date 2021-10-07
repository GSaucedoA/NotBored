package dev.cynomys.notbored

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(private val array: Array<String>) :
    RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val constraint = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_category, parent, false)
        return ViewHolder(constraint)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val headerTitle = array.get(position)
        holder.headerTitle.text = headerTitle
    }

    override fun getItemCount(): Int {
        return array.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTitle = view.findViewById<TextView>(R.id.header_title)
    }
}