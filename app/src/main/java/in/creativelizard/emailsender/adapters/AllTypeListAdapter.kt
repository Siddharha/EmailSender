package `in`.creativelizard.emailsender.adapters

import `in`.creativelizard.emailsender.activities.EditTemplate
import `in`.creativelizard.emailsender.beans.AllTypeItem
import `in`.creativelizard.emailsender.db.DatabaseHandler
import `in`.creativelizard.emailsender.fragments.AllTypeFragment
import `in`.creativelizard.emailsender.interfaces.ApiInterface
import `in`.creativelizard.emailsender.models.SendMail
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
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import `in`.creativelizard.emailsender.models.OutputMail
import `in`.creativelizard.emailsender.utill.ApiClient
import android.app.AlertDialog
import android.content.DialogInterface
import io.reactivex.Observable


/**
 * Created by siddhartha on 26/12/17.
 */
class AllTypeListAdapter (private val arrayList: ArrayList<AllTypeItem>,
                          private val context: Context,
                          private val layout: Int,
                          private val fragment:AllTypeFragment) : RecyclerView.Adapter<AllTypeListAdapter.ViewHolder>() {
    private val to:Array<String>?=null

    private var apiServices:ApiInterface ?=null
    private var versionCheckResponseObservable: Observable<OutputMail>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTypeListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        apiServices = ApiClient.getClient().create(ApiInterface::class.java)
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
            showSendMailPopup()
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

    private fun showSendMailPopup() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmation!")
                .setMessage("Do you wanna send email?")
                .setPositiveButton("YES",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
                    sendMail()
                })
                .setNegativeButton("NO",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
                   //
                })
                .create()
                .show()
    }

    private fun sendMail() {

      /*  val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, to)
        email.putExtra(Intent.EXTRA_SUBJECT, "sdfsdf")
        email.putExtra(Intent.EXTRA_TEXT, "gdfgk;sl4096850968039eirsldkfj")

        //need this to prompts email client only
        email.type = "message/rfc822"

        context.startActivity(Intent.createChooser(email, "Choose an Email client :"))*/

        val dataBean = SendMail.DataBean()
        dataBean.to = "siddhartha.ee.13@gmail.com"
        dataBean.from = "siddhartha@creativelizard.in"
        dataBean.name = "Siddhartha Maji"
        dataBean.subject = "Test Message"
        dataBean.body = "Please don't bother about this mail. this is just a test mail."
        val sendMail = SendMail()
        sendMail.setData(dataBean)
        versionCheckResponseObservable = apiServices?.getMailResponse(sendMail)/*getVersionResponse(versionCheckInput)*/
        versionCheckResponseObservable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<OutputMail> {
                    override fun onSubscribe(d: Disposable) {
                        // d.dispose();
                    }

                    override fun onNext(response: OutputMail) {
                        Toast.makeText(context,response.getData()?.msg,Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {

                    }
                })

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