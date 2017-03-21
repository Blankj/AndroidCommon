package com.blankj.common.ui.dialog;

import android.content.DialogInterface;

import com.blankj.common.R;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/20
 *     desc  :
 * </pre>
 */
public class BaseAlertDialog extends BaseDialog {


    @Override
    protected void build(Builder builder) {
        builder.setTitle("wode");

    }

    @Override
    protected int bindContentView() {
        return R.layout.dialog_alert;
    }

}
