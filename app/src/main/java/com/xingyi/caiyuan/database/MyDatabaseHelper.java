package com.xingyi.caiyuan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Htw on 2017/7/7.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    Context context;

    //发送问题表
    final static String CREATE_SEND_QUESTION = "create table send_question(" +
            "id integer," +
            "userId integer," +
            "userName text," +
            "topicId integer," +
            "topicName text," +
            "title text," +
            "content text," +
            "isPublish integer," +
            "scanCount integer," +
            "answerCount integer," +
            "gmtCreate text," +
            "gmtModified text)";
    //问题的草稿表
    //注意草稿要比直接发多一条，这一条每次存储的时候都要访问sharepreference  data 中
    // 的draftQuestionCount键值对，记录草稿数量
    final static String CREATE_DRAFT_QUESTION = "create table draft_question(" +
            "id integer," +
            "userId integer," +
            "userName text," +
            "topicId integer," +
            "topicName text," +
            "title text," +
            "content text," +
            "isPublish integer," +
            "scanCount integer," +
            "answerCount integer," +
            "gmtCreate text," +
            "gmtModified text，" +
            "draftQuestionId integer," +
            "position integer)";//记录存储的第几条草稿


    final static String CREATE_BEST_NEW = "create table new(" +
            "face text," +
            "topic text, " +
            "title text, " +
            "content text, " +
            "look_count integer, " +
            "comment_count integer," +
            "time text)";

    final static String CREATE_TABLE_BEST_HOT = "create table hot(" +
            "id text" +
            "userId)";


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase
            .CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override

    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_TEST);
        db.execSQL(CREATE_BEST_NEW);
        ContentValues values = new ContentValues();
        for (int i = 0; i < 15; i++) {

            values.put("title", "title is " + i);
            db.insert("new", null, values);
            values.clear();
        }

        Toast.makeText(context, "数据库创建成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
