package com.blankj.common.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/24
 *     desc  : Activity基类
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity
        implements View.OnClickListener {

    /**
     * 是否全屏
     */
    protected boolean isFullScreen      = false;
    /**
     * 是否沉浸状态栏
     */
    protected boolean isSteepStatusBar  = false;
    /**
     * 当前Activity渲染的视图View
     */
    protected View contentView;
    /**
     * 上次点击时间
     */
    protected long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle bundle = getIntent().getExtras();
            initData(bundle);
            contentView = LayoutInflater.from(this).inflate(bindLayout(), null);
            if (isFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSteepStatusBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
            }
            setContentView(contentView);
            initView(contentView);
            doBusiness(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     *
     * @param bundle 从上个Activity传递过来的bundle
     */
    abstract void initData(Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局Id
     */
    abstract int bindLayout();

    /**
     * 初始化view
     */
    abstract void initView(final View view);

    /**
     * 业务操作
     *
     * @param context 上下文
     */
    abstract void doBusiness(Context context);

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    abstract void onWidgetClick(View view);

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (!isFastClick()) onWidgetClick(view);
    }

    /**
     * 页面跳转
     *
     * @param clz 要跳转的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz    要跳转的Activity类
     * @param bundle bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 页面跳转并有请求值
     *
     * @param clz         要跳转的Activity类
     * @param requestCode 请求值
     */
    public void startActivityForResult(Class<?> clz, int requestCode) {
        startActivityForResult(clz, null, requestCode);
    }

    /**
     * 携带数据的页面跳转并有请求值
     *
     * @param clz         要跳转的Activity类
     * @param bundle      bundle
     * @param requestCode 请求值
     */
    public void startActivityForResult(Class<?> clz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 设置是否全屏
     *
     * @param isFullScreen 是否全屏
     */
    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * 设置是否沉浸状态栏
     *
     * @param isSteepStatusBar 是否沉浸状态栏
     */
    public void setSteepStatusBar(boolean isSteepStatusBar) {
        this.isSteepStatusBar = isSteepStatusBar;
    }
}
