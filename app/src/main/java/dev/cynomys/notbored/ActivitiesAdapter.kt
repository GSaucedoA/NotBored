package dev.cynomys.notbored

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(
    private val array: Array<String>,
    private val sharedPreferences: SharedPreferences,
    private val context: Context
) :
    RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val constraint = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_category, parent, false)
        return ViewHolder(constraint)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val headerTitle = array.get(position)
        holder.headerTitle.text = headerTitle
        holder.headerContainer.setOnClickListener {
            sharedPreferences.edit().apply {
                putString(Const.SUGGESTION_TITLE, headerTitle)
            }.commit()
            context.startActivity(Intent(context, SuggestionActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTitle = view.findViewById<TextView>(R.id.header_title)
        val headerContainer = view.findViewById<ConstraintLayout>(R.id.header_container)
    }
}