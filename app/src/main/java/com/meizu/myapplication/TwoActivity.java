package com.meizu.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class TwoActivity extends AppCompatActivity {

    Button btnAdd;
    String path;
    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btnAdd = (Button) findViewById(R.id.btn_add);
        path = getCacheDir().getPath();
//        path = getBaseContext().getExternalFilesDir().getPath();
//        path = Environment.getExternalStorageDirectory().getPath();
//        path = Environment.getDownloadCacheDirectory().getPath();
//        path = Environment.getRootDirectory().getPath();
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/fixdemo/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        Log.e("zkq","缓存地址=="+path);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TwoActivity.this,"新版本，热修复成功",Toast.LENGTH_LONG).show();
                myApplication = (MyApplication) getApplication();
                try {
//                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/fixdemo/a.apatch"
                    myApplication.patchManager.addPatch("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                download("http://openfile.meizu.com/group1/M00/00/DB/CnQOjVhjY76AS3Y-AAG_jmvziEY278.png680x680.jpg","pic1");
            }
        }).start();

    }

    /**
     * 下载文件到本地
     *
     * @param urlString
     *          被下载的文件地址
     * @param filename
     *          本地文件名
     * @throws Exception
     *           各种异常
     */
    public void download(String urlString,String filename){
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/fixdemo/";
        int size =0;
        long perStartTime = System.currentTimeMillis();
        try{
            if (!new File(str).exists()) {
                new File(str).mkdir();
            }
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5000);
            // 输入流
            InputStream is = con.getInputStream();
            size = con.getContentLength();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            str = str + filename;
            OutputStream os = new FileOutputStream(str);

            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zkq","连接超时，图片链接为=="+urlString);

        }
        Log.e("zkq","下载完成");
    }

}
