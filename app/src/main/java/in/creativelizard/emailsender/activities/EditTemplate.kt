package `in`.creativelizard.emailsender.activities

import `in`.creativelizard.emailsender.R
import `in`.creativelizard.emailsender.utill.ConstantClass
import `in`.creativelizard.emailsender.utill.Pref
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_template.*
import android.widget.MultiAutoCompleteTextView.Tokenizer



class EditTemplate : AppCompatActivity() {
    private var _pref: Pref? = null
    private val TAG:String = "response"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_template)
        initialize()
        loadData()
        onActionPerform()
    }

    private fun initialize() {
        _pref = Pref(this)
    }

    private fun onActionPerform() {
        etContent.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val s = etContent.text.toString()

               try {

                  // _pref?.setSession(ConstantClass.SPACIAL_WARDS)
                    val requiredString = s.substring(s.indexOf("#") -1, s.indexOf(")") +1)
                   /*etContent.setText(Html.fromHtml( "<font color=\"red\">" + requiredString + "</font>"))*/

                   // etContent.setText(Html.fromHtml("<h2>" + requiredString + "</h2>"))
                   //  Toast.makeText(this@EditTemplate,requiredString,Toast.LENGTH_SHORT).show()

                   val p = s.split("DATE").size
                   etContent.setText(s.replace(requiredString," <DATE_"+p+">"))
                   etContent.setSelection(etContent.text.length)
                   Log.e(TAG,requiredString+" and Count"+p)
                }catch (e:IndexOutOfBoundsException){
                   e.printStackTrace()
               }
            }

        })
    }


    private fun loadData() {
        etTepTitle.setText(intent.getStringExtra("title"))
        etContent.setText(intent.getStringExtra("content"))
    }

    override fun onBackPressed() {

        if(!etTepTitle.text.toString().isEmpty()) {
            val i = Intent()
            i.putExtra(ConstantClass.TITLE_MAIL, etTepTitle.text.toString())
            i.putExtra(ConstantClass.CONTENT_MAIL, etContent.text.toString())
            setResult(Activity.RESULT_OK, i)
        }
        super.onBackPressed()
    }


}
