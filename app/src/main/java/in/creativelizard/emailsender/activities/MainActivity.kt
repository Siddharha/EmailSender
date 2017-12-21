package `in`.creativelizard.emailsender.activities

import `in`.creativelizard.emailsender.adapters.EmailTypePagerAdapter
import `in`.creativelizard.emailsender.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tabsCount = arrayOf("All Type", "Leave Type", "Other Type")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        loadPager()
        //debaugTest()
    }

    private fun initialize() {

    }

    private fun debaugTest() {
        Toast.makeText(this,tabsCount.get(0),Toast.LENGTH_LONG).show()
    }

    private fun loadPager() {
        content_pager_frame.adapter = EmailTypePagerAdapter(supportFragmentManager, tabsCount)
        typeTab.setupWithViewPager(content_pager_frame)
    }

}


