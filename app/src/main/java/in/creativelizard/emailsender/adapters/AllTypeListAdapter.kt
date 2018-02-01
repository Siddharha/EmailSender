package `in`.creativelizard.emailsender.adapters

import `in`.creativelizard.emailsender.activities.EditTemplate
import `in`.creativelizard.emailsender.beans.AllTypeItem
import `in`.creativelizard.emailsender.db.DatabaseHandler
import `in`.creativelizard.emailsender.fragments.AllTypeFragment
import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.all_type.*
import kotlinx.android.synthetic.main.all_type_list_cell.view.*







/**
 * Created by siddhartha on 26/12/17.
 */
class AllTypeListAdapter (private val arrayList: ArrayList<AllTypeItem>,
                          private val context: Context,
                          private val layout: Int,
                          private val fragment:AllTypeFragment) : RecyclerView.Adapter<AllTypeListAdapter.ViewHolder>() {
    private val to:Array<String>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTypeListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllTypeListAdapter.ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnLongClickListener {
            val i = Intent(context,EditTemplate::class.java)
            i.putExtra("title",arrayList[position].title)
            i.putExtra("content",arrayList[position].content)
            i.putExtra("id",arrayList[position].id)
            context.startActivity(i)
            true
        }

        holder.itemView.setOnClickListener {
           /* to?.set(0,"siddhartha@creativelizard.in")
            val i = Intent(Intent.ACTION_SEND)
            //i.type = "message/rfc822"
           // i.data = Uri.parse("mailto:")
            i.type = "text/plain"
            i.data = Uri.parse("siddhartha@creativelizard.in")
            i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
            i.putExtra(Intent.EXTRA_EMAIL  ,to)
            i.putExtra(Intent.EXTRA_SUBJECT, arrayList[position].title)
            i.putExtra(Intent.EXTRA_TEXT   , arrayList[position].content)

            try {
                context.startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
            }
*/

            sendMail()
           // context.startActivity(i)
        }

        holder.itemView.imgDelete.setOnClickListener{
            holder.db?.deleteTamplate(arrayList[position].id!!)
            holder.itemView.animate().translationX(holder.itemView.width.toFloat()).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    arrayList.clear()
                    fragment.rlAllTypes.layoutManager = fragment.layoutManager
                    fragment.rlAllTypes.adapter = fragment.allTypeListAdapter
                    holder.itemView.clearAnimation()
                    fragment.loadList()
                   // notifyDataSetChanged()

                }

                override fun onAnimationCancel(animation: Animator?) {}
            })

        }
    }

    private fun sendMail() {

        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, to)
        email.putExtra(Intent.EXTRA_SUBJECT, "sdfsdf")
        email.putExtra(Intent.EXTRA_TEXT, "gdfgk;sl4096850968039eirsldkfj")

        //need this to prompts email client only
        email.type = "message/rfc822"

        context.startActivity(Intent.createChooser(email, "Choose an Email client :"))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var db: DatabaseHandler? = null
        fun bindItems(items: AllTypeItem) {
            itemView.tvMainContent.text = items.title
            db = DatabaseHandler(context)
        }
    }
}