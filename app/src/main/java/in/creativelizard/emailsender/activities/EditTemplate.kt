package `in`.creativelizard.emailsender.activities

import `in`.creativelizard.emailsender.R
import `in`.creativelizard.emailsender.utill.ConstantClass
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_template.*

class EditTemplate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_template)
        loadData()
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
