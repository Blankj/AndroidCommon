package com.blankj.androidcommon;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankj.common.ui.dialog.BaseAlertDialog;
import com.blankj.common.ui.dialog.BaseDialog;
import com.blankj.utilcode.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.init(this);
//        NormalDialog normalDialog = new NormalDialog.Builder(this)
//                .setHeight(0.23f)  //屏幕高度*0.23
//                .setWidth(0.65f)  //屏幕宽度*0.65
//                .setTitleVisible(true)
//                .setTitleText("温馨提示")
//                .setTitleTextColor(R.color.colorPrimary)
//                .setContentText("是否关闭对话框？")
//                .setContentTextColor(R.color.colorPrimaryDark)
//                .setSingleMode(true)
//                .setSingleButtonText("关闭")
//                .setSingleButtonTextColor(R.color.colorAccent)
//                .setCanceledOnTouchOutside(true)
//                .build();
//        normalDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseAlertDialog baseAlertDialog = new BaseAlertDialog();
                baseAlertDialog.setOnCreateBuildListener(new BaseAlertDialog.OnCreateBuilderListener() {
                    @Override
                    public void onCreateBuilder(BaseDialog.Builder builder) {
                        builder.setPositiveButton("ok", new BaseDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        }).setNegativeButton("cancel", new BaseDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                baseAlertDialog.show();
            }
        }, 1000);


    }
}
