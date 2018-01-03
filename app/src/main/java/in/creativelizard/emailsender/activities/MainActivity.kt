package `in`.creativelizard.emailsender.activities

import `in`.creativelizard.emailsender.adapters.EmailTypePagerAdapter
import `in`.creativelizard.emailsender.R
import `in`.creativelizard.emailsender.utill.ConstantClass
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tabsCount = arrayOf("All Type", "Leave Type", "Other Type")
    val CONTENT_ADDED =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadPager()
    }

    private fun loadPager() {
        content_pager_frame.adapter = EmailTypePagerAdapter(supportFragmentManager, tabsCount)
        typeTab.setupWithViewPager(content_pager_frame)
    }

     fun fabAddClk(v: View?){
         startActivityForResult(Intent(this,EditTemplate::class.java),CONTENT_ADDED)
   }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            saveToDB(data?.getStringExtra(ConstantClass.TITLE_MAIL), data?.getStringExtra(ConstantClass.CONTENT_MAIL))
        }else{
            Toast.makeText(this@MainActivity,"Template not saved!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveToDB(stringExtra: String?, stringExtra1: String?) {
        Toast.makeText(this@MainActivity,"DB not Implemented yet!!",Toast.LENGTH_SHORT).show()
    }

}


