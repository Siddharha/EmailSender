package `in`.creativelizard.emailsender.db

import `in`.creativelizard.emailsender.beans.AllTypeItem
import `in`.creativelizard.emailsender.utill.ConstantClass
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.util.AsyncListUtil
import android.util.Log
import android.widget.Toast


/**
 * Created by siddhartha on 11/1/18.
 */
class DatabaseHandler (private val context: Context) :
        SQLiteOpenHelper(context, DatabaseConstant.DATABASE_NAME, null, DatabaseConstant.DATABASE_VERSION) {
    val db:SQLiteDatabase = this.writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_EMAIL_SENDER_TABLE = ("CREATE TABLE " + DatabaseConstant.TABLE_EMAIL_SENDER + "("
                + DatabaseConstant.COLUMN_ID + " INTEGER PRIMARY KEY autoincrement," + DatabaseConstant.COLUMN_SENDER_TITLE + " TEXT,"
                + DatabaseConstant.COLUMN_SENDER_DESCRIPTION + " TEXT" + ")")
        db?.execSQL(CREATE_EMAIL_SENDER_TABLE)

        Toast.makeText(context,"Secessfully!",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + DatabaseConstant.TABLE_EMAIL_SENDER)
        onCreate(db)
    }

    fun insurtTemplateData(title:String, description:String): Long {

        val values = ContentValues()

        values.put(DatabaseConstant.COLUMN_SENDER_TITLE,title)
        values.put(DatabaseConstant.COLUMN_SENDER_DESCRIPTION,description)
        val x = db.insert(DatabaseConstant.TABLE_EMAIL_SENDER,null,values)
        Log.e("response","Done: "+x)
        return x
    }

    fun getAllTemplateData():ArrayList<AllTypeItem>{

        val tempList = ArrayList<AllTypeItem>()
        val selectQuery = "SELECT  * FROM ${DatabaseConstant.TABLE_EMAIL_SENDER} DESC"
        val db:SQLiteDatabase = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()){
            do {
                val data = AllTypeItem()
                data.id = cursor.getInt(0)
                data.title = cursor.getString(1)
                data.content = cursor.getString(2)
                tempList.add(data)
            }while (cursor.moveToNext())

        }
        db.close()
        return tempList
    }

    fun deleteTamplate(id:Int): Boolean {
        return db.delete(DatabaseConstant.TABLE_EMAIL_SENDER, DatabaseConstant.COLUMN_ID + "=" + id, null) > 0

    }

}
