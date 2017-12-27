package `in`.creativelizard.emailsender.activities

import `in`.creativelizard.emailsender.R
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
}
