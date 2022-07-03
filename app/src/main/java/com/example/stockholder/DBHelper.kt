package com.example.stockholder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase) {
        var sql: String = "CREATE TABLE if not exists stock_545 (" + /*추후 입력 받은 주식명으로 변경*/
                "_id integer primary key autoincrement," +
                "_stockName text, " +
                "_price real, " +
                "_number integer, " +
                "_realPrice real, " +
                "_state boolean);"

        Log.d("생성", "데이터베이스 생성")
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists stocks"
        db.execSQL(sql)
        onCreate(db)
    }
}