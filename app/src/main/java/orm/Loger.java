package orm;

import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import java.lang.reflect.Field;


public class Loger {
    private final static boolean isWrite = Configure.IsWriteLog;

    public static synchronized void  LogE(String msg) {
        if (isWrite) {
            Log.e("____ORM____", msg);
        }

    }

    public static synchronized void LogI(String msg) {
        if (isWrite) {
            Log.i("____ORM____", msg);
        }

    }

    public static synchronized void printSql(Cursor cursor) {
        if (isWrite) {
            try {
                Field mQuery = cursor.getClass().getDeclaredField("mQuery");
                mQuery.setAccessible(true);
                SQLiteQuery v = (SQLiteQuery) mQuery.get(cursor);
                Loger.LogI(v.toString());
            } catch (Exception ignored) {

            }
        }
    }
}
