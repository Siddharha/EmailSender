package `in`.creativelizard.emailsender.utill

import android.content.Context
import android.content.SharedPreferences



/**
 * Created by siddhartha on 1/2/18.
 */
class Pref(context: Context) {
    private val PREF_FILE = "in.creativelizard.emailsender"
    private var _pref: SharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
    private var _editorPref: SharedPreferences.Editor = _pref.edit()

    fun getSession(key: String): String {
        return _pref.getString(key, "")
    }

    fun getIntegerSession(key: String): Int {
        return _pref.getInt(key, Integer.MIN_VALUE)
    }

    fun getFloatSession(key: String): Float {
        return _pref.getFloat(key, Float.MIN_VALUE)
    }

    fun getBooleanSession(key: String): Boolean {
        return _pref.getBoolean(key, java.lang.Boolean.FALSE)
    }

    fun setSession(key: String?, value: String?) {
        if (key != null && value != null) {
            _editorPref.putString(key, value)
            _editorPref.commit()
        }
    }

    fun setSession(key: String?, value: Int) {
        if (key != null) {
            _editorPref.putInt(key, value)
            _editorPref.commit()
        }
    }
}