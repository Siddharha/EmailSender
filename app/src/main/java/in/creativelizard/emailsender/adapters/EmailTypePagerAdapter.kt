package `in`.creativelizard.emailsender.adapters

import `in`.creativelizard.emailsender.fragments.AllTypeFragment
import `in`.creativelizard.emailsender.fragments.LeaveTypeFragment
import `in`.creativelizard.emailsender.fragments.OtherTypeFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by siddhartha on 21/12/17.
 */
class EmailTypePagerAdapter(fm:FragmentManager, private val pageCount:Array<String>):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
         when (position){
            0 -> return AllTypeFragment()
            1 -> return LeaveTypeFragment()
            2 -> return OtherTypeFragment()
             else -> {
                 return AllTypeFragment()
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