package `in`.creativelizard.emailsender.fragments

import `in`.creativelizard.emailsender.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by siddhartha on 21/12/17.
 */
class OtherTypeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview= inflater?.inflate(R.layout.other_type,container,false)
        return rootview
    }
}