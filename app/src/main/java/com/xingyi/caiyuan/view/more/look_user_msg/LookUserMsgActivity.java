package com.xingyi.caiyuan.view.more.look_user_msg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.http.PatchThread;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.login.LoginActivity;
import com.xingyi.caiyuan.view.more.modify.ModifyUserMassageActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Htw on 2017/5/29.
 * 查看用户信息界面活动
 */

public class LookUserMsgActivity extends BaseActivity {

    //为裁剪服务
    public static final int REQUEST_CODE_CHOOSE_IMAGE = 2;
    public static final int REQUEST_CODE_CROP_IMAGE = 3;
    private Uri iconUri;
    private Uri cropImageUri;

    //布局
    ImageButton btHead, btModify;
    TextView textBirthday, textAddress, textJob, textName, textIntroduce,
             textPhone, textEmail, textTitle;
    ImageView imgSex, imgExpertMark;

    //为上传照片服务

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://头像上传成功
                    analysisJson(msg.obj.toString());
                    break;
                case -1://头像上传失败
                    Toast.makeText(LookUserMsgActivity.this, "头像上传失败,请检查网络", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_look_message_layout);
        init();
    }

    private void init() {
        btHead = (ImageButton) findViewById(R.id.head_bt);
        btModify = (ImageButton) findViewById(R.id.modify_bt);
        textAddress = (TextView) findViewById(R.id.address);
        textPhone = (TextView) findViewById(R.id.phone);
        textEmail = (TextView) findViewById(R.id.email);
        textJob = (TextView) findViewById(R.id.job);
        textBirthday = (TextView) findViewById(R.id.birthday);
        textIntroduce = (TextView) findViewById(R.id.introduce);
        textName = (TextView) findViewById(R.id.name);
        textTitle = (TextView) findViewById(R.id.title_text);
        imgSex = (ImageView) findViewById(R.id.sex);
        imgExpertMark = (ImageView) findViewById(R.id.expertMark);
        textTitle.setText("我的信息");
    }

    public void modifyClick(View v) {
        Intent intent = new Intent(this, ModifyUserMassageActivity.class);
        startActivity(intent);
    }

    //改头像按钮监听事件
    public void headClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
    }

    //裁减图片操作
    private void startCropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 使图片处于可裁剪状态
        intent.putExtra("crop", "true");
        // 裁剪框的比例（根据需要显示的图片比例进行设置）
        if (Build.MANUFACTURER.contains("HUAWEI")) {
            //华为默认是圆形裁剪框，这里让它无法成圆形
            intent.putExtra("aspectX", 9999);
            intent.putExtra("aspectY", 9998);
        } else {
            //其他手机一般默认为方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
        }


        // 让裁剪框支持缩放
        intent.putExtra("scale", true);
        String path = Environment.getExternalStorageDirectory().getPath();
        File caiyuanFile = new File(path + "/caiyuan");
        if (!caiyuanFile.exists()) {
            caiyuanFile.mkdir();
        }
        File avatarFile = new File(path + "/caiyuan/avatar");
        if (!avatarFile.exists()) {
            avatarFile.mkdir();
        }
        // 传递原图路径
        File cropFile = new File(path + "/caiyuan/avatar/avatar.jpg");


        try {
            if (cropFile.exists()) {
                cropFile.delete();
            }
            cropFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cropImageUri = Uri.fromFile(cropFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);

        // 设置图片的输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // return-data=true传递的为缩略图，小米手机默认传递大图，所以会导致onActivityResult调用失败
        intent.putExtra("return-data", false);

        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    //裁剪图片返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null) {
            switch (requestCode) {
                // 将选择的图片进行裁剪
                case REQUEST_CODE_CHOOSE_IMAGE:
                    if (intent.getData() != null) {
                        iconUri = intent.getData();
                        startCropImage(iconUri);
                    }
                    break;
                case REQUEST_CODE_CROP_IMAGE:
                    if (resultCode == RESULT_OK) {
                        try {
                            //requestAvatar();开始上传图片

                            Bitmap bitmap = BitmapFactory.decodeStream
                                    (getContentResolver()
                                            .openInputStream(cropImageUri));
                            btHead.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;

            }
        }
    }
    //上传头像的http请求
    private void  requestAvatar(){
        String url;
        String filePath=Environment.getExternalStorageDirectory().getPath()+"/caiyuan/avatar";
        String fileName="avatar.jpg";
        int id;//用户id
        SharedPreferences pre=getSharedPreferences("userData",MODE_PRIVATE);
        id=pre.getInt("id",-1);
        if(id==-1){
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        url= " https://www.hellyuestc.cn/users/"+id+"?field=avatarUrl";


        PatchThread patchThread=new PatchThread(url,filePath,fileName,handler,1,this);
        patchThread.setAccept("application/json");
        patchThread.setContentType("multipart/form-data");
        patchThread.start();
    }

    //解析返回json数据
    private boolean analysisJson(String jsonString){
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            String error=jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(this, error+"", Toast.LENGTH_SHORT).show();
            return false;

        } catch (JSONException e) {
            e.printStackTrace();
            try{
                JSONObject jsonObject=new JSONObject(jsonString);
                String avatarUrl=jsonObject.getJSONObject("data").getString("avatarUrl");
                Toast.makeText(this, avatarUrl+"", Toast.LENGTH_SHORT).show();
                return true;
            }catch (JSONException e2){
                e2.printStackTrace();
            }
        }

        return  false;
    }


}
