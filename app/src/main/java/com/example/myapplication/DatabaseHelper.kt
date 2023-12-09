package com.example.myapplication

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
// DatabaseHelper.kt 用來包含複製資料庫、開啟資料庫，以及對 Listen_1 和 Listen_2 資料表進行查詢的方法。
class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Quiz.db"     // 資料庫檔案名稱
        private const val DATABASE_VERSION = 1          // 資料庫版本
    }

    private var database: SQLiteDatabase? = null        // 宣告資料庫變數

    init {
        if (!checkDatabase()) {
            copyDatabase()  // 若資料庫不存在，複製資料庫
        }
        openDatabase()  // 開啟資料庫
    }

    // 檢查資料庫是否存在
    private fun checkDatabase(): Boolean {
        // 取得應用程式內部指定資料庫檔案的路徑
        val dbFile: File = context.getDatabasePath(DATABASE_NAME)
        // 檢查檔案是否存在，並回傳檔案是否存在的布林值
        return dbFile.exists()
    }

    // 複製資料庫到手機中
    // 簡單來說，這邊是用來把路徑\app\src\main\assets，assets資料夾當中的Quiz.db檔讀進來
    private fun copyDatabase() {
        // 從資產目錄 (assets) 中取得資料庫檔案的輸入串流
        val inputStream: InputStream = context.assets.open(DATABASE_NAME)
        // 取得應用程式內部資料庫檔案的路徑
        val outputFile: File = context.getDatabasePath(DATABASE_NAME)
        val outputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(1024)
        var length: Int
        // 逐段讀取資料並寫入至內部資料庫檔案
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        // 清除緩衝區，關閉串流
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    // 開啟資料庫
    private fun openDatabase() {
        // 取得資料庫檔案的路徑
        val dbPath = context.getDatabasePath(DATABASE_NAME).path
        // 使用 SQLiteDatabase.openDatabase() 開啟資料庫連線，以唯讀模式
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
    }

    override fun close() {
        database?.close()   // 關閉資料庫連線
        super.close()
    }

    // 這邊就可以寫對 Listen_1 資料表的操作
    fun getListen1Data(): Cursor? {
        val tableName = "Listen_1"
        val columnNo = "No"
        val minNoValue = 10
        //這邊是要下sql指令，(舉例用的)
        val sqlQuery = "SELECT * FROM $tableName WHERE $columnNo > $minNoValue"
        return database?.rawQuery(sqlQuery, null)
    }

    // 這邊就可以寫對 Listen_2 資料表的操作
    fun getListen2Data(): Cursor? {
        return database?.query("Listen_2", null, null, null, null, null, "No")
    }

    // 目前還沒需要建立其他資料庫
    override fun onCreate(db: SQLiteDatabase?) {}

    // 更新資料庫的指令
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}
