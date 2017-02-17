package com.meizu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MyApplication myApplication;
    TextView tvShow;
    Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myApplication = (MyApplication) getApplication();
//        try {
//            myApplication.patchManager.addPatch("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnShow = (Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"现在是最新版本",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,TwoActivity.class));
            }
        });
    }
}
