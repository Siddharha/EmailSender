package `in`.creativelizard.emailsender

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by siddhartha on 21/12/17.
 */
class EmailTypePagerAdapter(fm:FragmentManager, private val pageCount:Array<String>):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
         when (position){
            0 -> return AllType()
            1 -> return LeaveType()
            2 -> return OtherType()
             else -> {
                 return AllType()
             }
        }
    }

    override fun getCount(): Int {
        return pageCount.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageCount[position]
    }

}