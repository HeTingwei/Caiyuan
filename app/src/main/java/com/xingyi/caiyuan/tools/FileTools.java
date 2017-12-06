package com.xingyi.caiyuan.tools;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HeTingwei on 2017/7/31.
 */

/*
*
* 本类是文件访问的工具，方法中，所有输入的文件名参数，
* 都不需要再添加绝对路径，本类会自动加上
* */
public class FileTools {

    public static String absolutePath= Environment.getExternalStorageDirectory().getPath()+"/";

    //读文件，返回读出的字符串

    public static String readFile(String path) {
        path=absolutePath+path;
        File file = new File(path);
        FileInputStream inStream;
        int len;
        //创建一个字节数组输出流
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        //初始化 流对象
        try {
            inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return "";
        }
        try {
            while ((len = inStream.read(buffer)) != -1) {
                ostream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inStream.close();
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String(ostream.toByteArray());
        return str;
    }

    //写文件，将字符串写入文件
    public static void writeFile(String path, String msg) {
        path=absolutePath+path;
        FileOutputStream outStream;
        File file =new File(path);
        try {
            outStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return;
        }
        try {
            outStream.write(msg.getBytes());
        } catch (Exception e) {
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //创建avatar文件夹
    public static void  avatarFoldler(){

    }

   //读sharePreference

    //写sharepreference


}
