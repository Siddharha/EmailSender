package `in`.creativelizard.emailsender.fragments

import `in`.creativelizard.emailsender.R
import `in`.creativelizard.emailsender.adapters.AllTypeListAdapter
import `in`.creativelizard.emailsender.beans.AllTypeItem
import `in`.creativelizard.emailsender.db.DatabaseHandler
import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.all_type.*

/**
 * Created by siddhartha on 21/12/17.
 */
class AllTypeFragment : Fragment() {
    private var rootview:View ?= null
    private var allTypeListAdapter:AllTypeListAdapter ?=null
    private var allTypeList:ArrayList<AllTypeItem> ?=null
    private var layoutManager:LinearLayoutManager ?=null
    private var db: DatabaseHandler? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootview = inflater?.inflate(R.layout.all_type,container,false)
        initialize()
        onActionPerform()
        return rootview
    }

    private fun onActionPerform() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Toast.makeText(activity,"hi",Toast.LENGTH_SHORT).show()
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                      allTypeListAdapter!!.removeItem(position)
                } else {
                    /* removeView()
                    edit_position = position
                    alertDialog!!.setTitle("Edit Name")
                    et_name!!.setText(names[position])
                    alertDialog!!.show()*/
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rlAllTypes)
    }

    private fun initialize() {
        db = DatabaseHandler(activity)
        layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        allTypeList = ArrayList()
        allTypeListAdapter = AllTypeListAdapter(allTypeList!!,context,R.layout.all_type_list_cell)
    }

    override fun onResume() {
        super.onResume()
        allTypeList?.clear()
        rlAllTypes.layoutManager = layoutManager
        rlAllTypes.adapter = allTypeListAdapter
        loadList()

      //Log.e("array", allTypeList!![1].content)
        allTypeListAdapter?.notifyDataSetChanged()
    }

    private fun loadList() {
     /*   for (i in 1..5){
          val s =  AllTypeItem()
            s.title = "Sid_"+i
            s.content = "Test Sample for Email Sender"
            s.id= i
           // Log.e(">>",s.content)
           allTypeList?.add(s)
        }*/

        if(db?.getAllTemplateData()!=null) {
            for (i in 0..(db?.getAllTemplateData()?.size!!-1)) {
                allTypeList?.add(db?.getAllTemplateData()?.get(i)!!)
            }
        }

        allTypeListAdapter?.notifyDataSetChanged()
    }


}
