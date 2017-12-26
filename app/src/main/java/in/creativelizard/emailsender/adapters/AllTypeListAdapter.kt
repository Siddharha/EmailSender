package `in`.creativelizard.emailsender.adapters

import `in`.creativelizard.emailsender.activities.EditTemplate
import `in`.creativelizard.emailsender.activities.MainActivity
import `in`.creativelizard.emailsender.beans.AllTypeItem
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.all_type_list_cell.view.*

/**
 * Created by siddhartha on 26/12/17.
 */
class AllTypeListAdapter (private val arrayList: ArrayList<AllTypeItem>,
                          private val context: Context,
                          private val layout: Int) : RecyclerView.Adapter<AllTypeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTypeListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllTypeListAdapter.ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnLongClickListener {
            val i = Intent(context,EditTemplate::class.java)
            i.putExtra("title",arrayList[position].title)
            i.putExtra("id",arrayList[position].id)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(items: AllTypeItem) {
            itemView.tvMainContent.text = items.content
        }
    }
}