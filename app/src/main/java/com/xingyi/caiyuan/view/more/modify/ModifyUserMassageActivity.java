package com.xingyi.caiyuan.view.more.modify;

import android.content.Context;
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.http.PatchThread;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Htw on 2017/4/18.
 * 修改用户个人信息界面
 * 也是实际上被调用的唯一可以修改用户信息的界面
 */

public class ModifyUserMassageActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE_IMAGE = 2;
    private  static final int REQUEST_CODE_CROP_IMAGE = 3;
    private Uri iconUri;
    private Uri cropImageUri;


    //文本框7个
    private EditText editName, editBirth, editPhone, editEmail, editAddr, editJob, editIntro;
    //文本框，获取输入字符串
     String textName, textBirth, textPhone, textEmail, textAddr, textJob, textIntro;
    //选择性别
    private RadioGroup radioGroup;
    //选择的性别结果
    private String sex;

    private String city,province;//两级联动的结果

    ImageView faceImg;//头像


    //之下的变量用于实现两级联动
    HashMap<String, String> provinceHash = new HashMap<>();
    String[] provinceString = new String[34];

    HashMap<String, String> cityHash = new HashMap<>();
    String[] cityString;

    String file;

    String cityNo = null;// 最重要的参数，选中的城市的cityNo

    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    Spinner provinceSpinner;
    Spinner citySpinner;
    //之上的变量用于两级联动

    //头像上传
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://头像上传成功
                    analysisJson(msg.obj.toString());
                    break;
                case -1://头像上传失败
                    Toast.makeText(ModifyUserMassageActivity.this, "头像上传失败,请检查网络",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modify_user);
        init();
    }

    private void init() {
        TextView titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("编辑个人信息");
        faceImg = (ImageView) findViewById(R.id.face_img);
        editName = (EditText) findViewById(R.id.editName);
        editBirth = (EditText) findViewById(R.id.editBirth);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAddr = (EditText) findViewById(R.id.editAddr);
        editJob = (EditText) findViewById(R.id.editJob);
        editIntro = (EditText) findViewById(R.id.editIntro);
        initData();

        RadioButton radioMan= (RadioButton) findViewById(R.id.radioMan);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                sex = radioButton.getText().toString();
            }
        });
        radioGroup.check(radioMan.getId());

        //两级联动的变量初始化
        provinceSpinner = (Spinner) findViewById(R.id.spinnerprovince);
        citySpinner = (Spinner) findViewById(R.id.spinnercity);

        file = readFile(); // 读取txt文件
        getProvinces(file); // 得到省的列表

        provinceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, provinceString);
        provinceAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉风格
        provinceSpinner.setAdapter(provinceAdapter); // 将adapter 添加到spinner中
        provinceSpinner.setOnItemSelectedListener(new ProvinceSelectedListener(ModifyUserMassageActivity.this));// 添加监听
        provinceSpinner.setVisibility(View.VISIBLE);// 设置默认值

    }

    //将用户原数据显示到editText
    private void initData(){
        SharedPreferences pre=getSharedPreferences("userData",MODE_PRIVATE);
        editName.setText(pre.getString("name",""));
        editBirth.setText(pre.getString("birthday",""));
        editPhone.setText(pre.getString("phone",""));
        editEmail.setText(pre.getString("email",""));
        editAddr.setText(pre.getString("address",""));
        editJob.setText(pre.getString("job",""));
        editIntro.setText(pre.getString("introduction",""));
    }

    //提交修改信息
    public void commitClick(View v) {
        textName = editName.getText().toString();
        textBirth = editBirth.getText().toString();
        textPhone = editPhone.getText().toString();
        textEmail = editEmail.getText().toString();
        textAddr = editAddr.getText().toString();
        textJob = editJob.getText().toString();
        textIntro = editIntro.getText().toString();

        if(!judgeInput()){
            //输入有问题
            return;
        }
        Toast.makeText(this, textName + textBirth + textPhone + textEmail +
                textAddr + textJob + textIntro + sex, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, province+city+"", Toast.LENGTH_SHORT).show();
    }

    //判断输入是否符合要求
    private boolean judgeInput(){
        if(textName.equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textName.length()>20){
            Toast.makeText(this, "用户名不能超过20字符(｡•́︿•̀｡)", Toast.LENGTH_LONG).show();
            return false;
        }

        if(textBirth.equals("")){
            Toast.makeText(this, "生日不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textPhone.equals("")){
            Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textEmail.equals("")){
            Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textAddr.equals("")){
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textJob.equals("")){
            Toast.makeText(this, "职业不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(textIntro.equals("")){
            Toast.makeText(this, "简介不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(!judgeEmail(textEmail)){
            Toast.makeText(this, "邮箱格式错误，请检查", Toast.LENGTH_SHORT).show();
            return false;
        }



        return  true;
    }

    //判断邮箱格式正确性
    boolean judgeEmail(String str){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return  isMatched;
    }



    //上传头像的http请求
    private void requestAvatar() {
        String url;
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/caiyuan/avatar";
        String fileName = "avatar.jpg";
        int id;//用户id
        SharedPreferences pre = getSharedPreferences("userData", MODE_PRIVATE);
        id = pre.getInt("id", -1);
        if (id == -1) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        url = " https://www.hellyuestc.cn/users/" + id + "?field=avatarUrl";


        PatchThread patchThread = new PatchThread(url, filePath, fileName, handler, 1, this);
        patchThread.setAccept("application/json");
        patchThread.setContentType("multipart/form-data");
        patchThread.start();
    }

    //解析返回json数据
    private boolean analysisJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String error = jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
            return false;

        } catch (JSONException e) {
            e.printStackTrace();
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                String avatarUrl = jsonObject.getJSONObject("data").getString("avatarUrl");
                Toast.makeText(this, avatarUrl + "", Toast.LENGTH_SHORT).show();
                return true;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        return false;
    }

    /*************************************************************************************************************************************/
    //之下代码，修改头像调用相册

    //改头像
    public void headClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
    }

    //裁剪头像
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
        File cropFile = new File(path + "/caiyuan/avatar/avatar.p");

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
                            faceImg.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
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

    //之上代码，修改头像调用相册
/* ************************************************************************************************************************************************/
    //本类中，之下代码,全部用于两级联动

    public String readFile() {

        /*
         * 读取文件中数据的方法
         */

        InputStream myFile = null;
        myFile = getResources().openRawResource(R.raw.ub_city);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(myFile, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("debug", e.toString());
        }
        String temp;
        String str = "";
        try {
            while ((temp = br.readLine()) != null) {
                str = str + temp;
                // Log.i("zhiyinqing", "断点3"+temp);
            }
            br.close();
            myFile.close();
            return str;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public void getProvinces(String jsonData) {

        /*
         * 从json字符串中得到省的信息
         */

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < 34; i++) {
                // 获取了34个省市区信息
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String guid = jsonObject.getString("guid");
                String cityName = jsonObject.getString("cityName");
                // Log.i("zhiyinqing", i+guid+cityName);
                provinceHash.put(cityName, guid);
                provinceString[i] = cityName;

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String[] getCitys(String guid, String jsonData) {
        /*
         * 此方法用于查找一个省下的所有城市
         */
        // 初始化hashmap
        cityHash.clear();
        // 暂时存放城市的数组
        String[] cityBuffer = new String[21];
        int j = 0;

        // 解析数据
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(jsonData);
            int length = jsonArray.length();
            int i = 33;
            int continuous = 0;// 这个变量用于判断是否连续几次没有找到，如果是，则该省信息全部找到了
            boolean isFind = false;

            while (i < length) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String fGuid = jsonObject.getString("fGuid");
                String cityName = jsonObject.getString("cityName");
                String cityNo = jsonObject.getString("cityNo");
                if (fGuid.equals(guid)) {
                    isFind = true;
                    cityHash.put(cityName, cityNo);
                    cityBuffer[j] = cityName;
                    j++;
                    // Log.i("zhiyinqing", cityName);
                } else {
                    if (isFind == true) {
                        continuous += 1;

                        if (continuous > 5) {
                            Log.i("zhiyinqing", "城市数:" + j);
                            break;
                        }
                    }
                }
                i++;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] citys = new String[j];
        for (int i = 0; i < j; i++) {
            citys[i] = cityBuffer[i];
        }
        return citys;
    }

    class ProvinceSelectedListener implements AdapterView.OnItemSelectedListener {

        Context context;

        // 省被选择的监听器

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {

            String provinceName = provinceString[arg2];
            province=provinceName;
            String guid = provinceHash.get(provinceName);
            cityString = getCitys(guid, file);

            // 省被选中后，初始化城市Spinner
            cityAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, cityString);
            cityAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉风格
            citySpinner.setAdapter(cityAdapter); // 将adapter 添加到spinner中
            citySpinner.setOnItemSelectedListener(new CitySelectedListener());// 添加监听
            citySpinner.setVisibility(View.VISIBLE);// 设置默认

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

        public ProvinceSelectedListener(Context context) {
            this.context = context;
        }

    }

    class CitySelectedListener implements AdapterView.OnItemSelectedListener {

        // 城市被点击的监听事件

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            String cityName = cityString[arg2];
            city=cityName;
            if (cityName.equals("") || cityName == null) {
                cityName = cityString[0];
                cityNo = cityHash.get(cityName);

            } else {
                cityNo = cityHash.get(cityName);
                Log.i("zhiyinqing", "cityNo" + cityNo);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    }

}
