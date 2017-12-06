package com.xingyi.caiyuan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by HeTingwei on 2017/7/30.
 */

public class HelpDB {

    //基本没有用，在table表中，插入数据Map<列名，数据>
    public static void HelpInsert(String table, Map<String, String> map, Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "caiyuan.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        Iterator entries = map.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry entry = (Map.Entry) entries.next();

            String key = (String) entry.getKey();

            String value = (String) entry.getValue();
            values.put(key, value);
        }
        db.insert(table, null, values);
        values.clear();
        db.close();
        dbHelper.close();
    }

    //删除一条记录后更新表，使id依旧连续增加，不出现缺少此记录的id的现象
    //比如：db.delete("Book", "pages = ?", new String[] { "5" });会删除第5项，并将后面的项的id均减1
    //删除表table中，满足条件condition的id的那一条记录.idName是记录id列的列名
    public static void  HelpDeleteItemId(String table, int id, String idName, Context context) {
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(context,"caiyuan.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(table,idName+"=?",new String[]{id+""});
        Cursor cursor=db.query(table,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int primaryKey=cursor.getInt(cursor.getColumnIndex(idName));
                if(primaryKey>id){
                    ContentValues values=new ContentValues();
                    primaryKey--;
                    values.put(idName,primaryKey);
                    primaryKey++;
                    db.update(table,values,idName+"=?",new String[]{""+primaryKey});
                }
            }while (cursor.moveToNext());
        }
    }

    //返回查询的游标cursor
    public static Cursor HelpQuery(String table, Context context){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "caiyuan.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.query(table,null,null,null,null,null,null,null);
    }


}
