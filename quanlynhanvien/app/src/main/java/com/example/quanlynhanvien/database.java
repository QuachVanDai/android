package com.example.quanlynhanvien;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.example.quanlynhanvien.R;

public class database {
    public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
        try {
            // duwongf dẫn đến thư mục data sqlite
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                // mở database sqlite trong thư mục asset
                InputStream e = activity.getAssets().open(databaseName);
                // tạo 1 folder chứa dường dẫn database
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                // tạo file  myOutput
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);//dọc dữ từ database luu vào file myOutput
                }
                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
