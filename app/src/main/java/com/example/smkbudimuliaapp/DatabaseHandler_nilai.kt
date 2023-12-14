package com.example.smkbudimuliaapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler_nilai(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "raport"
        private val TABLE_NILAI = "data_nilai"
        private val KEY_ID = "id"
        private val KEY_HARIAN = "harian"
        private val KEY_UTS = "uts"
        private val KEY_UAS = "uas"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_NILAI + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_HARIAN + " TEXT,"
                + KEY_UTS + " TEXT," + KEY_UAS + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NILAI)
        onCreate(db)
    }


    //method to insert data
    fun add_nilai(emp: EmpModelClass_nilai):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_HARIAN, emp.harian) // EmpModelClass Name
        contentValues.put(KEY_UTS,emp.uts ) // EmpModelClass Phone
        contentValues.put(KEY_UAS,emp.uas )
        // Inserting Row
        val success = db.insert(TABLE_NILAI, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun view_nilai():List<EmpModelClass_nilai>{
        val empList:ArrayList<EmpModelClass_nilai> = ArrayList<EmpModelClass_nilai>()
        val selectQuery = "SELECT  * FROM $TABLE_NILAI"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var harian: String
        var uts: String
        var uas: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                harian = cursor.getString(cursor.getColumnIndex("harian"))
                uts = cursor.getString(cursor.getColumnIndex("uts"))
                uas = cursor.getString(cursor.getColumnIndex("uas"))

                val emp= EmpModelClass_nilai(userId = userId, harian = harian, uts = uts, uas = uas)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    //method to update data
    fun update_nilai(emp: EmpModelClass_nilai):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_HARIAN, emp.harian) // EmpModelClass
        contentValues.put(KEY_UTS,emp.uts ) // EmpModelClass
        contentValues.put(KEY_UAS,emp.uas )

        // Updating Row
        val success = db.update(TABLE_NILAI, contentValues,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun delete_nilai(emp: EmpModelClass_nilai):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_NILAI,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}