package com.meizu.myapplication;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by zkq on 2017/1/19.
 */

public class MyApplication extends Application {
    public PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化patch管理类
        patchManager = new PatchManager(this);
        //初始化patch管理类版本
        patchManager.init("1.0");
        //加载已经添加到PatchManager中的patch
        patchManager.loadPatch();
    }
}
