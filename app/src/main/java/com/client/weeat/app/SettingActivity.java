package com.client.weeat.app;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.permission.PermissionUtils;
import com.client.weeat.permission.request.IRequestPermissions;
import com.client.weeat.permission.request.RequestPermissions;
import com.client.weeat.permission.requestresult.IRequestPermissionsResult;
import com.client.weeat.permission.requestresult.RequestPermissionsResultSetApp;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.Constants;
import com.client.weeat.util.FileProviderUtils;
import com.client.weeat.util.SystemProgramUtils;
import com.longsh.optionframelibrary.OptionBottomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.R.id.my_setting_txlehs;
import static com.client.weeat.app.MyApplication.context;
import static com.client.weeat.util.Constants.BASIC_URL;

public class SettingActivity extends AppCompatActivity {

    @Bind(R.id.tv_setting_school)
    TextView mTvSettingSchool;
    @Bind(R.id.tv_setting_id)
    TextView mTvSettingId;
    @Bind(R.id.my_setting_id)
    LinearLayout mMySettingId;
    @Bind(R.id.tv_setting_phone)
    TextView mTvSettingPhone;
    @Bind(R.id.my_setting_phone)
    LinearLayout mMySettingPhone;
    @Bind(R.id.tv_setting_sex)
    TextView mTvSettingSex;
    @Bind(R.id.my_setting_sex)
    LinearLayout mMySettingSex;
    @Bind(R.id.rl_setting_icon)
    RelativeLayout mRlSettingIcon;
    @Bind(R.id.my_setting_school)
    LinearLayout mMySettingSchool;
    @Bind(R.id.tv_setting_name)
    TextView mTvSettingName;
    @Bind(R.id.my_setting_name)
    LinearLayout mMySettingName;
    @Bind(my_setting_txlehs)
    ImageView mMySettingTxlehs;
    @Bind(R.id.sign_up)
    Button mSignUp;
    private UserBeanDate.ResultBean.UserInfoBean userInfoBean;
    private String user_info_json;
    private String userID;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private String username;
    private String usersex;
    private JSONObject json;
    private SharedPreferences sp;
    IRequestPermissions requestPermissions = RequestPermissions.getInstance();//动态权限请求
    IRequestPermissionsResult requestPermissionsResult = RequestPermissionsResultSetApp.getInstance();//动态权限请求结果处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);
        ButterKnife.bind(this);
        initDate();
        mRlSettingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> stringList = new ArrayList<String>();
                stringList.add("拍照");
                stringList.add("从相册中选择");
                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(SettingActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                if (ContextCompat.checkSelfPermission(SettingActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(SettingActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                                } else {
                                    SystemProgramUtils.paizhao(SettingActivity.this, new File("/mnt/sdcard/tupian.jpg"));
                                }
                              /*  if(!requestPermissions()){
                                    return;
                                }
                                SystemProgramUtils.paizhao(SettingActivity.this, new File("/mnt/sdcard/tupian.jpg"));*/
                                break;
                            case 1:
                                if (ContextCompat.checkSelfPermission(SettingActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(SettingActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                                } else {
                                    SystemProgramUtils.zhaopian(SettingActivity.this);
                                }
               /*                 if(!requestPermissions()){
                                    return;
                                }
                                SystemProgramUtils.zhaopian(SettingActivity.this);
                                //Toast.makeText(SettingActivity.this,"从相册选择",Toast.LENGTH_LONG).show();*/
                                break;
                        }
                        optionBottomDialog.dismiss();
                    }
                });
            }
        });
        //性别弹出框
        mMySettingSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this); //定义一个AlertDialog
                final String[] strarr = {"男","女"};
                builder.setItems(strarr, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        mTvSettingSex.setText(strarr[arg1]);
                    }
                });
                builder.show();
            }
        });
        mMySettingName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("请输入");    //设置对话框标题
                //builder.setIcon(android.R.drawable.btn_star);   //设置对话框标题前的图标
                builder.setIcon(R.drawable.editdlg);   //设置对话框标题前的图标
                final EditText edit = new EditText(SettingActivity.this);
                builder.setView(edit);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       /* Toast.makeText(context, "你输入的是: " + edit.getText().toString(), Toast.LENGTH_SHORT).show();*/
                        mTvSettingName.setText(edit.getText().toString());
                    }
                });
/*                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       *//* Toast.makeText(context, "你输入的是: " + edit.getText().toString(), Toast.LENGTH_SHORT).show();*//*
                      *//*  mTvSettingName.setText(edit.getText().toString());*//*
                    }
                });*/
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "你点了取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
                AlertDialog dialog = builder.create();  //创建对话框
                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                dialog.show();
            }
        });
    }


    /**
     * 请求权限
     */
    //请求权限
    private boolean requestPermissions() {
        //需要请求的权限
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        //开始请求权限
        return requestPermissions.requestPermissions(
                this,
                permissions,
                PermissionUtils.ResultCode1);
    }

    //用户授权操作结果（可能授权了，也可能未授权）
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //用户给APP授权的结果
        //判断grantResults是否已全部授权，如果是，执行相应操作，如果否，提醒开启权限
        if (requestPermissionsResult.doRequestPermissionsResult(this, permissions, grantResults)) {
            //请求的权限全部授权成功，此处可以做自己想做的事了
            //输出授权结果
            Toast.makeText(SettingActivity.this, "授权成功，请重新点击刚才的操作！", Toast.LENGTH_LONG).show();
        } else {
            //输出授权结果
            Toast.makeText(SettingActivity.this, "请给APP授权，否则功能无法正常使用！", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        Uri filtUri;
        File outputFile = new File("/mnt/sdcard/tupian_out.jpg");//裁切后输出的图片
        switch (requestCode) {
            case SystemProgramUtils.REQUEST_CODE_PAIZHAO:
                //拍照完成，进行图片裁切
                //Environment.getExternalStorageDirectory().getpath()
                File file = new File("/mnt/sdcard/tupian.jpg");
                filtUri = FileProviderUtils.uriFromFile(SettingActivity.this, file);
                SystemProgramUtils.Caiqie(SettingActivity.this, filtUri, outputFile);
                break;
            case SystemProgramUtils.REQUEST_CODE_ZHAOPIAN:
                //相册选择图片完毕，进行图片裁切
                if (data == null || data.getData() == null) {
                    return;
                }
                filtUri = data.getData();
                SystemProgramUtils.Caiqie(SettingActivity.this, filtUri, outputFile);
                break;
            case SystemProgramUtils.REQUEST_CODE_CAIQIE:
                //图片裁切完成，显示裁切后的图片
                try {
                    Uri uri = Uri.fromFile(outputFile);
                    Log.e("裁剪后的文件路径", uri.getEncodedPath());
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    mMySettingTxlehs.setImageBitmap(bitmap);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }


    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

/*    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();

        //上传头像
        sendImgByStr(imageString,userID);
    }

    private void sendImgByStr(String imageStr, String imagename) {
        String url = BASIC_URL+"/servlet/UpImageServlet";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("imagestr", imageStr)
                .addParams("imagename", imagename)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                        Toast.makeText(SettingActivity.this, "数据传输出现了问题，请重试==",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"请求数据为  "+response.toString());
                        //数据接收成功后，解析数据，创建适配器
                        //processDate(response);
                        // OldJSON = response.toString();
                    }
                });

    }*/

    public void uploadFile(View view) {
        //读取存储在本地的裁剪图片
        final File file = new File("/mnt/sdcard/tupian_out.jpg");
        //File file = new File(Environment.getExternalStorageDirectory(), "1.jpg");
        if (!file.exists()) {
            Toast.makeText(SettingActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
/*        username = mTvSettingName.getText().toString().trim();
        usersex = mTvSettingSex.getText().toString().trim();*/
/*        if(username!=null&&usersex!=null){
            json = new JSONObject();
            try {
                json.put("id", URLEncoder.encode(userID, "utf-8"));
                json.put("sex", URLEncoder.encode(usersex, "utf-8"));
                json.put("name", URLEncoder.encode(username, "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
        try {
            username = URLEncoder.encode(mTvSettingName.getText().toString().trim(), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            usersex = URLEncoder.encode(mTvSettingSex.getText().toString().trim(), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Log.e("新名称，性别",username+" "+usersex);
/*       Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("usersex", usersex);
                params.put("userid", userID);*/
        String url = BASIC_URL+"/servlet/FileUploadServlet";
        String name = DateFormat.format("yyyyMMdd_hhmmss",
                Calendar.getInstance(Locale.CHINA))+".jpg";
        OkHttpUtils.post()
                .addFile("mFile", name, file)
                .url(url)
                .addHeader("id", userID)
                .addHeader("name", username)
                .addHeader("sex", usersex)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                        Toast.makeText(SettingActivity.this, "数据传输出现了问题，请重试==",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                        intent.putExtra("fragmentID", 4);
                        SettingActivity.this.startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "请求数据为  " + response.toString());
                        //这里更新本地的user信息以及跳转
                        JSONObject jsonObject = JSON.parseObject(response);
                        String msg = jsonObject.getString("msg");
                        if (msg.equals("success")) {

                            //有bug,图片缓存存留问题
                            /*file.delete();*/
                            sp = SettingActivity.this.getSharedPreferences("user_info", SettingActivity.this.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user_info_json", response.toString());
                            editor.commit();//必须提交，否则保存不成功
                            Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                            intent.putExtra("fragmentID", 4);
                            SettingActivity.this.startActivity(intent);
                            finish();

                        }
                    }
                });
        //file.delete();   //操作完成删除暂时文件
    }


    private void initDate() {
        //用户基本信息
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json, UserBeanDate.class);
            userInfoBean = userBeanDate.getResult().getUser_info();
            userID = userInfoBean.getUserID().toString();  //获取用户信息
            if (userID != null)
                mTvSettingId.setText(userID);
            String school = userInfoBean.getSchool();
            if (school != null)
                mTvSettingSchool.setText(school);
            String sex = userInfoBean.getUserSex();
            if (sex != null)
                mTvSettingSex.setText(sex);
            else{
                //默认为女
                mTvSettingSex.setText("女");
            }
            String tel = userInfoBean.getUserTel();
            if (tel != null) {
                mTvSettingPhone.setText(tel);
            }
            String name = userInfoBean.getUserName();
            if(name!=null){
                mTvSettingName.setText(name);
            }else{
                mTvSettingName.setText("用户"+userID);
            }
            if(userInfoBean.getUserIcon()!=null){
                Glide.with(this).load(Constants.BASIC_URL_USERICON + userInfoBean.getUserIcon())
                        .into(mMySettingTxlehs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
