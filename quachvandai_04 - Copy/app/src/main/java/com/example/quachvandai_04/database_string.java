package com.example.quachvandai_04;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database_string  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hoadonkhachsan4.sqlite";
    private static final int DATABASE_VERSION = 1;
    public database_string(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE hoadon_1 (ma INTEGER PRIMARY KEY AUTOINCREMENT,ten TEXT,sophong INT,dongia INT,songayluutru INT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<String> getTableList() {
        List<String> tableList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String tableName = cursor.getString(0);
                    tableList.add(tableName);
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
        }
        return tableList;
    }
    public Cursor getDataFromTable(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = null;  // Lựa chọn các cột để truy vấn, null để truy vấn tất cả các cột
        String selection = null;  // Điều kiện WHERE, null để không áp dụng điều kiện
        String[] selectionArgs = null;  // Giá trị tham số của điều kiện WHERE, null nếu không có tham số
        String sortOrder = null;  // Sắp xếp kết quả, null nếu không có sắp xếp
        return db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

}
   /* database_string dbHelper = new database_string(MainActivity.this);
    List<String> tableList = dbHelper.getTableList();

// Kiểm tra danh sách bảng
        for (String tableName : tableList) {
                Log.d("Table +1 ", tableName);
                }*/